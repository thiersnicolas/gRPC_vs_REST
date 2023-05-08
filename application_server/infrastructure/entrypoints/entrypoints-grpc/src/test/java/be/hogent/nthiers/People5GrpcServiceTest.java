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
public class People5GrpcServiceTest {
    @Inject
    GrpcPeopleCache peopleCache;
    @GrpcClient
    People5Grpc subject;

    @Test
    public void testPeople5Endpoint() {
        var reply = subject
                .getPeople(Amount.newBuilder().setAmount(2).build()).collect().asList().await().atMost(Duration.ofSeconds(5));
        assertArrayEquals(reply.toArray(), peopleCache.getPeople5(2).collect().asList().await().indefinitely().toArray());
    }

    @Test
    public void testPeople5Endpoint_SameListOnEveryCall() {
        var people = peopleCache.getPeople5(2).collect().asList().await().indefinitely();

        var reply1 = subject
                .getPeople(Amount.newBuilder().setAmount(5).build()).collect().asList().await().atMost(Duration.ofSeconds(5));
        assertIterableEquals(reply1, people);

        var reply2 = subject
                .getPeople(Amount.newBuilder().setAmount(5).build()).collect().asList().await().atMost(Duration.ofSeconds(5));
        assertIterableEquals(reply2, people);

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
        peopleCache.clearData();
        peopleCache.getPeople5(5);
        peopleCache.getPeople5(10);

        var supportedAmounts2 = subject
                .getSupportedAmounts(SupportedAmountsInput.newBuilder().build()).await().atMost(Duration.ofSeconds(5)).getSupportedAmountsList();
        assertArrayEquals(supportedAmounts2.toArray(), new Integer[]{5, 10});
    }

}
