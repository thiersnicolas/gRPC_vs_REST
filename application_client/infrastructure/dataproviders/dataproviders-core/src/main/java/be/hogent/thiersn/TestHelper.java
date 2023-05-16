package be.hogent.thiersn;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class TestHelper<T> {
    private static String FILE_WRITE_LOCATION = "src/test/resources";
    People5Service<T> people5Service;
    ObjectMapper objectMapper;

    List<String> logs;

    public TestHelper(People5Service<T> people5Service, ObjectMapper objectMapper) {
        this.people5Service = people5Service;
        this.objectMapper = objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.logs = new LinkedList<>();
    }

    public void testPerformance_singularRequests(String testName, String apiType, List<Integer> amounts) throws IOException {
        people5Service.clearData();
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + String.format(": starting %s for %s", testName, apiType));
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": data cleared");

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": warming up server");

        amounts.forEach(this::callForAmount);
        amounts.forEach(this::callForAmount);

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": server warmed up");

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests starting");
        amounts.forEach(this::callForAmount);
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests finished");
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + String.format(": finished %s for %s", testName, apiType));
        printLogs(testName);
    }

    public void callForAmount(int amount) {
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": calling people5/" + amount);
        var result = people5Service.people5(amount);
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": response received people5/" + amount);
        try {
            logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": response people5/" + amount +
                    " JSON to file size: " + getPeopleWith5FieldsSize(amount, result) + " bytes");
        } catch (IOException e) {
            logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": unable to get response size due to: " + e);
        }
    }

    public void printLogs(String testName) throws IOException {
        Path path = Paths.get(
                String.format(FILE_WRITE_LOCATION + "/%s.txt",
                        testName.replace("(", "").replace(")", ""))
        );
        Files.write(path, logs);
    }

    private long getPeopleWith5FieldsSize(int amount, List<T> people) throws IOException {
        var path = Paths.get(String.format(FILE_WRITE_LOCATION + "/%s" + "people5" + ".json", amount));
        var file = path.toFile();
        objectMapper.writeValue(file, people);
        var totalSpace = Files.size(path);
        file.delete();
        return totalSpace;
    }
}
