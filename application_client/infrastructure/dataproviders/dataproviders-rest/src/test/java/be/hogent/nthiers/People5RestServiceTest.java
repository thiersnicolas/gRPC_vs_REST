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
        var testHelper = new TestHelper<>(people5RestService, objectMapper, people5RestService::people5);

        //var amounts = List.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288);

        var amounts = List.of(1024, 2048, 4096, 8192,9216, 10240, 11264, 12288, 13312, 14336, 15360,
                16384, 20480, 24576, 28672, 32768, 36864, 40960, 45056, 49152, 53248, 57344, 61440, 65536, 131072,
                262144, 524288);

        testHelper.testPerformance_singularRequests("test_REST_SingularCalls", "REST", amounts);
    }

    @Test
    public void test_RESTgZIP_SingularCalls() throws IOException {
        var testHelper = new TestHelper<>(people5RestService, objectMapper, people5RestService::people5);

        var amounts = List.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288);
        testHelper.testPerformance_singularRequests("test_RESTgZIP_SingularCalls", "REST", amounts);
    }
    @Test
    public void test_REST_MultipleBatchedCalls_DifferentBatchSizes() throws IOException {
        var testHelper = new TestHelper<>(people5RestService, objectMapper, people5RestService::people5);

        var amounts = List.of(4194304);

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024",
                "REST", amounts, 1024);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize2048",
                "REST", amounts, 2048);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize2048 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize4096",
                "REST", amounts, 4096);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize4096 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize8192",
                "REST", amounts, 8192);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize8192 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize9216",
                "REST", amounts, 9216);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize9216 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize10240",
                "REST", amounts, 10240);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize10240 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize11264",
                "REST", amounts, 11264);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize11264 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize12288",
                "REST", amounts, 12288);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize12288 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize13312",
                "REST", amounts, 13312);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize13312 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize14336",
                "REST", amounts, 14336);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize14336 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize15360",
                "REST", amounts, 15360);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize15360 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize16384",
                "REST", amounts, 16384);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize16384 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize20480",
                "REST", amounts, 20480);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize20480 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize24576",
                "REST", amounts, 24576);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize24576 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize28672",
                "REST", amounts, 28672);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize28672 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize32768",
                "REST", amounts, 32768);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize32768 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize36864",
                "REST", amounts, 36864);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize36864 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize40960",
                "REST", amounts, 40960);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize40960 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize45056",
                "REST", amounts, 45056);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize45056 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize49152",
                "REST", amounts, 49152);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize49152 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize53248",
                "REST", amounts, 53248);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize53248 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize57344",
                "REST", amounts, 57344);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize57344 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize61440",
                "REST", amounts, 61440);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize61440 done");

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize65536",
                "REST", amounts, 65536);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize65536 done");
    }

    @Test
    public void test_REST_MultipleBatchedCalls_DifferentBatchSizes_justOne() throws IOException {
        var testHelper = new TestHelper<>(people5RestService, objectMapper, people5RestService::people5);

        var amounts = List.of(4194304);

        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize524288",
                "REST", amounts, 524288);

        System.out.println("test_REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize524288 done");
    }

    @Test
    public void test_REST_MultipleBatchedCalls() throws IOException {
        var testHelper = new TestHelper<>(people5RestService, objectMapper, people5RestService::people5);

        var amounts = List.of(1024, 2048, 4096, 8192,9216, 10240, 11264, 12288, 13312, 14336, 15360,
                16384, 20480, 24576, 28672, 32768, 36864, 40960, 45056, 49152, 53248, 57344, 61440, 65536, 131072,
                262144, 524288, 1048576, 1572864, 2097152, 2621440, 3145728, 3670016, 4194304);
        testHelper.testPerformance_multipleRequests("test_REST_MultipleBatchedCalls_BatchSize_12288",
                "REST", amounts, 12288);
    }

}
