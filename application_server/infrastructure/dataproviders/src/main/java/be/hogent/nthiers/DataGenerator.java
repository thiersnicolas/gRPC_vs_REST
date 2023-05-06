package be.hogent.nthiers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.instancio.Model;

import java.nio.file.Paths;

import static org.instancio.Select.field;

public class DataGenerator {
    ObjectMapper objectMapper;
    Model<Person> personModel;

    public DataGenerator(ObjectMapper objectMapper, Model<Person> personModel) {
        this.objectMapper = objectMapper;
        this.personModel = personModel;
    }

    public static void main(String... args) {
        Model<Person> personModel = Instancio.of(Person.class)
                .generate(field(Person::getFirstname), gen -> gen.text().pattern("#C#c#c#c#c#c#c"))
                .generate(field(Person::getLastname), gen -> gen.text().pattern("#C#c#c#c#c"))
                .generate(field(Person::getBirthYear), gen -> gen.ints().min(1900).max(2013))
                .generate(field(Person::getBirtMonth), gen -> gen.ints().min(1).max(12))
                .generate(field(Person::getBirtDay), gen -> gen.ints().min(1).max(28))
                .toModel();

        new DataGenerator(new ObjectMapper(), personModel)
                .writePeopleToFile(1)
                .writePeopleToFile(2)
                .writePeopleToFile(4)
                .writePeopleToFile(8)
                .writePeopleToFile(16)
                .writePeopleToFile(32)
                .writePeopleToFile(64)
                .writePeopleToFile(128)
                .writePeopleToFile(256)
                .writePeopleToFile(512)
                .writePeopleToFile(1024)
                .writePeopleToFile(2048)
                .writePeopleToFile(4096)
                .writePeopleToFile(8192)
                .writePeopleToFile(16384)
                .writePeopleToFile(32768);


    }

    private DataGenerator writePeopleToFile(int amount) {
        try {
            objectMapper.writeValue(
                    Paths.get(String.format("application_server/infrastructure/dataproviders/src/main/resources/%speople_%sb.json", amount, (int) amount * 86.5D)).toFile(),
                    Instancio.ofList(personModel).size(amount).create()
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return this;
    }

}
