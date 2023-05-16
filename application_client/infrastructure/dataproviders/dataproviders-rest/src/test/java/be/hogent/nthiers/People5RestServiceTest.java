package be.hogent.nthiers;

import be.hogent.thiersn.TestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@QuarkusTest
public class People5RestServiceTest {
    @RestClient
    People5RestService people5RestService;
    @Inject
    ObjectMapper objectMapper;


    @Test
    public void test_REST_SingularCalls() throws IOException {
        var testHelper = new TestHelper<>(people5RestService, objectMapper);

        var amounts = List.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288);
        testHelper.testPerformance_singularRequests("test_REST_SingularCalls", "REST", amounts);
    }

}
