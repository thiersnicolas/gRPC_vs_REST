package be.hogent.nthiers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.instancio.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.instancio.Select.field;

public class DataGenerator {
    public static final List<Integer> NUMBER_LIST = List.of(
            1,
            2,
            4,
            8,
            16,
            32,
            64,
            128,
            256,
            512,
            1024,
            2048,
            4096,
            8192,
            16384,
            32768,
            65536,
            131072,
            262144,
            524288
    );
    private static final String FILE_WRITE_LOCATION = "application_server/infrastructure/dataproviders/src/main/resources/";
    private static final String FILE_READ_LOCATION = "../../dataproviders/src/main/resources/";
    private static final String PEOPLE_5_FIELDS = "people5Fields";
    ObjectMapper objectMapper;
    Model<Person5> person5Model;

    public DataGenerator(ObjectMapper objectMapper, Model<Person5> person5Model) {
        this.objectMapper = objectMapper;
        this.person5Model = person5Model;
    }

    public static Map<Integer, List<Person5>> getPeopleWith5FieldsByAmountFromFiles() {
        var objectMapper = new ObjectMapper();
        var directory = Paths.get(FILE_READ_LOCATION).toFile();
        if (directory.exists() && directory.isDirectory() && directory.listFiles() != null) {
            return Arrays.stream(Objects.requireNonNull(directory.listFiles((file, name) -> name.contains(PEOPLE_5_FIELDS))))
                    .collect(Collectors.toMap(file -> Integer.parseInt(file.getName().split(PEOPLE_5_FIELDS)[0]),
                                    file -> {
                                        try {
                                            return objectMapper.readValue(file, new TypeReference<>() {
                                            });
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                            )
                    );
        } else return Collections.emptyMap();
    }

    public static void main(String... args) {
        Model<Person5> personModel = Instancio.of(Person5.class)
                .generate(field(Person5::getFirstname), gen -> gen.text().pattern("#C#c#c#c#c#c#c"))
                .generate(field(Person5::getLastname), gen -> gen.text().pattern("#C#c#c#c#c"))
                .generate(field(Person5::getBirthYear), gen -> gen.ints().min(1900).max(2013))
                .generate(field(Person5::getBirtMonth), gen -> gen.ints().min(1).max(12))
                .generate(field(Person5::getBirtDay), gen -> gen.ints().min(1).max(28))
                .toModel();
        DataGenerator dataGenerator = new DataGenerator(new ObjectMapper(), personModel);

        NUMBER_LIST.forEach(dataGenerator::writePeopleWith5FieldsToFile);
    }

    private void writePeopleWith5FieldsToFile(int amount) {
        try {
            var path = Paths.get(String.format(FILE_WRITE_LOCATION + "%s" + PEOPLE_5_FIELDS + ".json", amount));
            var file = path.toFile();
            objectMapper.writeValue(
                    file,
                    Instancio.ofList(person5Model).size(amount).create()
            );
            var totalSpace = Files.size(path);
            System.out.println(totalSpace);

            file.renameTo(
                    Paths.get(String.format(FILE_WRITE_LOCATION + "%s" + PEOPLE_5_FIELDS + "_%sbytes.json", amount, totalSpace))
                            .toFile()
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
