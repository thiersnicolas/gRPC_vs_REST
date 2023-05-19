package be.hogent.nthiers;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataGenerator {
    public List<Person5> getPeopleWith5Fields(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(i -> create())
                .collect(Collectors.toList());
    }

    public Person5 create() {
        return new Person5(
                RandomStringUtils.random(7, true, false),
                RandomStringUtils.random(5, true, false),
                new RandomDataGenerator().nextInt(1900, 2022),
                new RandomDataGenerator().nextInt(1, 12),
                new RandomDataGenerator().nextInt(1, 28)
        );
    }

}
