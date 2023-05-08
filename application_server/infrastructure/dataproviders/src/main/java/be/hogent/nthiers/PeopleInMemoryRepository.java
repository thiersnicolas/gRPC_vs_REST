package be.hogent.nthiers;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class PeopleInMemoryRepository {
    private Map<Integer, List<Person5>> people5 = Collections.emptyMap();

    public void loadData() {
        people5 = DataGenerator.getPeopleWith5FieldsByAmountFromFiles();
    }

    public void clearData() {
        people5 = Collections.emptyMap();
    }

    public List<Person5> getPeople5(Integer amount) {
        return people5.getOrDefault(amount, Collections.emptyList());
    }

    public Collection<Integer> getPeople5SupportedAmounts() {
        return people5.keySet();
    }
}
