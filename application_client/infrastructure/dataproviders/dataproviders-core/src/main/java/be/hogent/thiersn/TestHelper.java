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
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.String.format;

public class TestHelper<T> {
    private static String FILE_WRITE_LOCATION = "src/test/resources";
    People5Service<T> people5Service;
    ObjectMapper objectMapper;
    Function<Integer, Object> call;

    List<String> logs;

    public TestHelper(People5Service<T> people5Service, ObjectMapper objectMapper, Function<Integer, Object> call) {
        this.people5Service = people5Service;
        this.objectMapper = objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.call = call;
        this.logs = new LinkedList<>();
    }

    public void testPerformance_singularRequests(String testName, String apiType, List<Integer> amounts) throws IOException {
        logs.clear();
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": starting %s for %s", testName, apiType));

        warmupServer(() -> amounts.forEach(this::callForAmountWithoutLog));

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests starting\n");

        amounts.forEach(this::callForAmount);

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests finished\n");
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": finished %s for %s", testName, apiType));
        printLogs(testName);
    }

    public void testPerformance_multipleRequests(String testName, String apiType, List<Integer> amounts, int batchSize) throws IOException {
        logs.clear();
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": starting %s for %s", testName, apiType));

        warmupServer(() -> callForAmountWithoutLog(batchSize));

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests starting\n");

        amounts.forEach(i -> {
            System.out.printf("starting collecting %d people with %d calls of %d people per call%n", i, i / batchSize == 0 ? 1 : i / batchSize, batchSize);
            LocalDateTime start = LocalDateTime.now();
            logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) +
                    format(": starting collecting %d people with %d calls of %d people per call \n",
                            i, i.floatValue() / batchSize % 1 == 0 ? i / batchSize : i / batchSize + 1, batchSize));
            for (int j = i; j > 0; j -= batchSize) {
                callForAmountWithoutLog(batchSize);
            }
            var duration = start.until(LocalDateTime.now(), ChronoUnit.MILLIS);
            logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) +
                    format(": collected %d people with %d calls of %d people per call ", i,
                            i.floatValue() / batchSize % 1 == 0 ? i / batchSize : i / batchSize + 1,
                    batchSize));
            logs.add(String.format("this took %d millies\n", duration));
        });

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests finished");
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": finished %s for %s", testName, apiType));
        printLogs(testName);


    }

    private void callForAmount(int amount) {
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": calling for %d people5 ", amount));
        var result = call.apply(amount);
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": response received %d people5 ", amount));
        try {
            logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": response for %d people5, JSON to file size: %d bytes",
                    amount, getPeopleWith5FieldsSize(amount, result)));
        } catch (Exception e) {
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": unable to get response size due to: " + e);
        }
        logs.add("\n");
    }

    private void warmupServer(CallbackFunction warmupCall) {
        people5Service.clearData();
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": data cleared");

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": warming up server");

        warmupCall.justDo();
        warmupCall.justDo();
        warmupCall.justDo();
        warmupCall.justDo();

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": server warmed up");
    }

    private void callForAmountWithoutLog(int amount) {
        var result = call.apply(amount);
    }

    private void printLogs(String testName) throws IOException {
        Path path = Paths.get(
                format(FILE_WRITE_LOCATION + "/%s.txt",
                        testName.replace("(", "").replace(")", ""))
        );
        Files.write(path, logs);
    }

    private long getPeopleWith5FieldsSize(int amount, Object response) {
        var path = Paths.get(format(FILE_WRITE_LOCATION + "/%s" + "people5" + ".json", amount));
        var file = path.toFile();
        try {
            objectMapper.writeValue(file, response);
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            file.delete();
        }
    }

    @FunctionalInterface
    private interface CallbackFunction {
        void justDo();
    }
}
