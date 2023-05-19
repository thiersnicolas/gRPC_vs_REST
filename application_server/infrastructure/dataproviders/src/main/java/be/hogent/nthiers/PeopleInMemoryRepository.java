package be.hogent.nthiers;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class PeopleInMemoryRepository {
    public final DataGenerator dataGenerator;
    private final Map<Integer, List<Person5>> people5;

    public PeopleInMemoryRepository() {
        dataGenerator = new DataGenerator();
        people5 = new HashMap<>();
    }

    public void clearData() {
        people5.clear();
    }

    public List<Person5> getPeople5(Integer amount) {
        return people5.computeIfAbsent(amount, dataGenerator::getPeopleWith5Fields);
    }

    public Collection<Integer> getPeople5SupportedAmounts() {
        return people5.keySet();
    }
}
