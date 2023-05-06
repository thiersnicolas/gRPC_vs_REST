package be.hogent.nthiers;

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/people")
public class PeopleResource {
    @Inject
    PeopleInMemoryRepository peopleInMemoryRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> people() {
        return peopleInMemoryRepository.getPeople();
    }
}
