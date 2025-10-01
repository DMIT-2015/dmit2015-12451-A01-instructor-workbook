package dmit2015.faces;

import dmit2015.model.Task;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Session-scoped backing bean: per-user state that persists for the HTTP session.
 * Implements Serializable for passivation.
 */
@Named("taskManagerBeanSession")
@SessionScoped // Lives for the user's session; store only per-user state
public class TaskManagerBeanSession implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(TaskManagerBeanSession.class.getName());

    @Getter
    @Setter
    private Task currentTask = new Task();

    @Getter
    private List<Task> tasks = new ArrayList<>();

    public void onAddTask() {
        // add currentTask to our list of Task
        tasks.add(currentTask);
        // Send FacesMessages to FacesContext
        Messages.addGlobalInfo("Task added: {0}", currentTask);
        // Clear the form
        currentTask = new Task();
    }

    public void onRemoveTask(Task task) {
        tasks.remove(task);
        Messages.addGlobalInfo("Task removed: {0}", task);
    }
}
