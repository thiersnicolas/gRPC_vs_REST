package be.hogent.nthiers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/people")
public class PeopleResource {
    PeopleInMemoryRepository peopleInMemoryRepository;

    public PeopleResource(PeopleInMemoryRepository peopleInMemoryRepository) {
        this.peopleInMemoryRepository = peopleInMemoryRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> people() {
        return peopleInMemoryRepository.getPeople();
    }
}
