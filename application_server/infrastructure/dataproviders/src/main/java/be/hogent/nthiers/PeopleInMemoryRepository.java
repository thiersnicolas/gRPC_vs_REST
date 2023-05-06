package be.hogent.nthiers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import java.util.List;

@ApplicationScoped
public class PeopleInMemoryRepository {
    List<Person> people;

    public List<Person> getPeople() {
        return people;
    }
}
