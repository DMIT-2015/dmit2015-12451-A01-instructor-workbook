package dmit2015.resource;

import dmit2015.entity.Job;
import dmit2015.entity.Region;
import dmit2015.repository.HumanResourcesRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/hr")
@Produces(MediaType.APPLICATION_JSON)
public class HumanResourcesResource {

    @Inject
    private transient HumanResourcesRepository hrRepository;

    @GET
    @Path("/jobs")
    public List<Job> findAllJobs() {
        return hrRepository.findAlJobs();
    }

    @GET
    @Path("/Regions")
    public List<Region> findAllRegions() {
        return hrRepository.findAllRegions();
    }
}
