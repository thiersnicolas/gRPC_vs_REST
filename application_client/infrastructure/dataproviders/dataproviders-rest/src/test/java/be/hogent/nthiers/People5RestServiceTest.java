package be.hogent.nthiers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@QuarkusTest
public class People5RestServiceTest {
    private static String FILE_WRITE_LOCATION = "src/test/resources";
    @RestClient
    People5RestService people5RestService;
    @Inject
    ObjectMapper objectMapper;

    List<String> logs;

    @BeforeEach
    public void beforeEach() {
        this.logs = new LinkedList<>();
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Test
    public void testPerformance_REST_singularRequests() {
        people5RestService.clearData();
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": data cleared");

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": warming up server");

        IntStream.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288)
                .forEach(this::callRESTForAmount);
        IntStream.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288)
                .forEach(this::callRESTForAmount);

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": server warmed up");

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests starting");
        IntStream.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288)
                .forEach(this::callRESTForAmount);
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests finished");
    }

    @Test
    public void simpleTestRest() {
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": start simpleTestRest");
        callRESTForAmount(1);
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": end simpleTestRest");
    }

    private void callRESTForAmount(int amount) {
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": calling people5/" + amount);
        var result = people5RestService.people5(amount);
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": response received people5/" + amount);
        try {
            logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": response people5/" + amount +
                    " JSON to file size: " + getPeopleWith5FieldsSize(amount, result) + " bytes");
        } catch (IOException e) {
            logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": unable to get response size due to: " + e);
        }
    }

    @AfterEach
    public void printLogs(TestInfo testInfo) throws IOException {
        Path path = Paths.get(
                String.format(FILE_WRITE_LOCATION + "/%s.txt",
                        testInfo.getDisplayName().replace("(", "").replace(")", ""))
        );
        Files.write(path, logs);
    }

    private long getPeopleWith5FieldsSize(int amount, List<?> people) throws IOException {
        var path = Paths.get(String.format(FILE_WRITE_LOCATION + "/%s" + "people5" + ".json", amount));
        var file = path.toFile();
        objectMapper.writeValue(file, people);
        var totalSpace = Files.size(path);
        file.delete();
        return totalSpace;
    }

}
