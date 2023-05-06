package be.hogent.nthiers;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PeopleInMemoryRepository {
    List<Person> people;

    List<Person> getPeople() {
        return people;
    }
}
