package be.hogent.nthiers;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class HelloGrpcServiceTest {
    @Inject
    GrpcPeopleCache peopleCache;

    @GrpcClient
    People5Grpc subject;

    @Test
    public void testPeople5Endpoint_InMemoryEmpty() {
        var supportedAmounts = subject.clearData(ClearDataInput.newBuilder().build()).await().atMost(Duration.ofSeconds(5)).getSupportedAmountsList();
        assertIterableEquals(supportedAmounts, peopleCache.getPeople5SupportedAmounts());

        var people = subject
                .getPeople(Amount.newBuilder().setAmount(2).build()).collect().asList().await().atMost(Duration.ofSeconds(5));
        assertTrue(people.isEmpty());
    }

    @Test
    public void testPeople5Endpoint_InMemoryNotEmpty() {
        var supportedAmounts = subject.loadData(LoadDataInput.newBuilder().build()).await().atMost(Duration.ofSeconds(5));
        assertIterableEquals(supportedAmounts.getSupportedAmountsList(), peopleCache.getPeople5SupportedAmounts());

        var reply = subject
                .getPeople(Amount.newBuilder().setAmount(2).build()).collect().asList().await().atMost(Duration.ofSeconds(5));
        assertArrayEquals(reply.toArray(), peopleCache.getPeople5(2).collect().asList().await().indefinitely().toArray());
    }

    @Test
    public void testPeople5Endpoint_InMemoryNotEmpty_unsupportedAmount() {
        var supportedAmounts = subject.loadData(LoadDataInput.newBuilder().build()).await().atMost(Duration.ofSeconds(5));
        assertIterableEquals(supportedAmounts.getSupportedAmountsList(), peopleCache.getPeople5SupportedAmounts());

        var reply = subject
                .getPeople(Amount.newBuilder().setAmount(5).build()).collect().asList().await().atMost(Duration.ofSeconds(5));
        assertTrue(reply.isEmpty());
    }

    @Test
    public void testPeople5SupportedAmountsEndpoint_InMemoryEmpty() {
        var supportedAmounts = subject.clearData(ClearDataInput.newBuilder().build()).await().atMost(Duration.ofSeconds(5));
        assertIterableEquals(supportedAmounts.getSupportedAmountsList(), peopleCache.getPeople5SupportedAmounts());

        var reply = subject
                .getPeople(Amount.newBuilder().setAmount(2).build()).collect().asList().await().atMost(Duration.ofSeconds(5));
        assertTrue(reply.isEmpty());
    }

    @Test
    public void testPeople5SupportedAmountsEndpoint_InMemoryNotEmpty() {
        var supportedAmounts = subject.loadData(LoadDataInput.newBuilder().build()).await().atMost(Duration.ofSeconds(5));
        assertIterableEquals(supportedAmounts.getSupportedAmountsList(), peopleCache.getPeople5SupportedAmounts());

        var supportedAmounts2 = subject
                .getSupportedAmounts(SupportedAmountsInput.newBuilder().build()).await().atMost(Duration.ofSeconds(5)).getSupportedAmountsList();
        assertArrayEquals(supportedAmounts2.toArray(), peopleCache.getPeople5SupportedAmounts().toArray());
    }

}
