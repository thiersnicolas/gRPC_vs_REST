package be.hogent.nthiers;

import org.instancio.Instancio;
import org.instancio.Model;

import java.util.List;

import static org.instancio.Select.field;

public class DataGenerator {
    public static List<Person5> getPeopleWith5Fields(int amount) {
        Model<Person5> person5Model =
                Instancio.of(Person5.class)
                        .generate(field(Person5::getFirstname), gen -> gen.text().pattern("#C#c#c#c#c#c#c"))
                        .generate(field(Person5::getLastname), gen -> gen.text().pattern("#C#c#c#c#c"))
                        .generate(field(Person5::getBirthYear), gen -> gen.ints().min(1900).max(2013))
                        .generate(field(Person5::getBirtMonth), gen -> gen.ints().min(1).max(12))
                        .generate(field(Person5::getBirtDay), gen -> gen.ints().min(1).max(28))
                        .toModel();
        return Instancio.ofList(person5Model).size(amount).create();
    }
}
