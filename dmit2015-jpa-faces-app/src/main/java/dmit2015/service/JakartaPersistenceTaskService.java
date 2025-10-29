package dmit2015.service;

import dmit2015.model.Task;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.SecurityContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Named("jakartaPersistenceTaskService")
@ApplicationScoped
public class JakartaPersistenceTaskService implements TaskService {

    @Inject
    private SecurityContext _securityContext;

    // Assign a unitName if there are more than one persistence unit defined in persistence.xml
    @PersistenceContext (unitName="postgresql-jpa-pu")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Task createTask(@Valid Task task) {
        // If the primary key is not an identity column then write code below here to
        // 1) Generate a new primary key value
        // 2) Set the primary key value for the new entity
        task.setId(UUID.randomUUID().toString());
        String username = _securityContext.getCallerPrincipal().getName();
        if (username.equalsIgnoreCase("anonymous")) {
            throw new RuntimeException("Access denied. Anonymous users are not allowed.");
        }
        task.setUsername(username);
        entityManager.persist(task);
        return task;
    }

    @Override
    public Optional<Task> getTaskById(String id) {
        try {
            Task querySingleResult = entityManager.find(Task.class, id);
            if (querySingleResult != null) {
                return Optional.of(querySingleResult);
            }
        } catch (Exception ex) {
            // id value not found
            throw new RuntimeException(ex);
        }
        return Optional.empty();
    }

    @Override
    public List<Task> getAllTasks() {
        if (!(_securityContext.isCallerInRole("Sales")
            || _securityContext.isCallerInRole("Shipping"))) {
            throw new RuntimeException("Access denied. Role not allowed.");
        }
        if (_securityContext.isCallerInRole("Sales")) {
            String username = _securityContext.getCallerPrincipal().getName();
            return entityManager.createQuery("SELECT o FROM Task o where o.username = :usernameValue", Task.class)
                    .setParameter("usernameValue", username)
                    .getResultList();
        }
        return entityManager.createQuery("SELECT o FROM Task o ", Task.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Task updateTask(@Valid Task task) {

        Optional<Task> optionalTask = getTaskById(task.getId());
        if (optionalTask.isEmpty()) {
            String errorMessage = String.format("The id %s does not exists in the system.", task.getId());
            throw new RuntimeException(errorMessage);
        } else {
            var existingTask = optionalTask.orElseThrow();

            existingTask.setDescription(task.getDescription());
            existingTask.setPriority(task.getPriority());
            existingTask.setDone(task.isDone());

            task = entityManager.merge(existingTask);
        }
        return task;
    }

    @Override
    @Transactional
    public void deleteTaskById(String id) {
        Optional<Task> optionalTask = getTaskById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.orElseThrow();
            // Write code to throw a RuntimeException if this entity contains child records
            entityManager.remove(task);
        } else {
            throw new RuntimeException("Could not find Task with id: " + id);
        }
    }

    @Override
    public void deleteAllTasks() {
        entityManager.createQuery("DELETE FROM Task").executeUpdate();
    }

}