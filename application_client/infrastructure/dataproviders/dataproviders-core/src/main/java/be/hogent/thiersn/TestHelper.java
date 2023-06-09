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
    CallbackFunction clearCache;
    ObjectMapper objectMapper;
    Function<Integer, Object> call;

    List<String> logs;

    public TestHelper(CallbackFunction clearCache, ObjectMapper objectMapper, Function<Integer, Object> call) {
        this.clearCache = clearCache;
        this.objectMapper = objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.call = call;
        this.logs = new LinkedList<>();
    }

    public void testPerformance_singularRequests(String testName, List<Integer> amounts) throws IOException {
        logs.clear();
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": starting %s", testName));

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + " starting warmup");
        warmupServer(() -> amounts.forEach(this::callForAmountWithoutLog));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + " warmup complete");

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests starting\n");

        amounts.forEach(this::callForAmount);

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests finished\n");
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": finished %s", testName));
        printLogs(testName);
    }

    public void testPerformance_singularRequests_withoutWarmup(String testName, List<Integer> amounts) throws IOException {
        logs.clear();
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": starting %s", testName));

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests starting\n");

        amounts.forEach(this::callForAmount);

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": performance tests finished\n");
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": finished %s", testName));
        printLogs(testName);
    }

    public void testPerformance_multipleRequests(String testName, List<Integer> amounts, int batchSize) throws IOException {
        logs.clear();
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": starting %s", testName));

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
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": finished %s", testName));
        printLogs(testName);


    }

    private void callForAmount(int amount) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + " calling for amount " + amount);
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": calling for %d people5 ", amount));
        LocalDateTime start = LocalDateTime.now();
        var result = call.apply(amount);
        var duration = start.until(LocalDateTime.now(), ChronoUnit.MILLIS);
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": response received %d people5 ", amount));
        logs.add(String.format("this took %d millies\n", duration));
        try {
            logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + format(": response for %d people5, JSON to file size: %d bytes",
                    amount, getPeopleWith5FieldsSize(amount, result)));
        } catch (Exception e) {
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": unable to get response size due to: " + e);
        }
        logs.add("\n");
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + " call finished for amount " + amount);
    }

    private void warmupServer(CallbackFunction warmupCall) {
        clearCache.justDo();
        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": data cleared");

        logs.add(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + ": warming up server");

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
    public interface CallbackFunction {
        void justDo();
    }
}
