package be.hogent.thiersn;

import be.hogent.nthiers.People5GrpcService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@QuarkusTest
public class People5GrpcServiceTest {
    @Inject
    People5GrpcService people5GrpcService;
    @Inject
    ObjectMapper objectMapper;

    @Test
    public void test_gRPC_SingularCalls() throws IOException {
        var testHelper = new TestHelper<>(people5GrpcService, objectMapper, people5GrpcService::people5);

        var amounts = List.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288);
        testHelper.testPerformance_singularRequests("testGrpc_SingularCalls_Uni", "gRPC", amounts);
    }

    @Test
    public void test_gRPC_MultipleBatchedCalls_DifferentBatchSizes() throws IOException {
        var testHelper = new TestHelper<>(people5GrpcService, objectMapper, people5GrpcService::people5);

        var amounts = List.of(4194304);

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024",
                "gRPC_UNI", amounts, 1024);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize2048",
                "gRPC_UNI", amounts, 2048);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize2048 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize4096",
                "gRPC_UNI", amounts, 4096);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize4096 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize8192",
                "gRPC_UNI", amounts, 8192);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize8192 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize9216",
                "gRPC_UNI", amounts, 9216);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize9216 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize10240",
                "gRPC_UNI", amounts, 10240);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize10240 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize11264",
                "gRPC_UNI", amounts, 11264);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize11264 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize12288",
                "gRPC_UNI", amounts, 12288);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize12288 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize13312",
                "gRPC_UNI", amounts, 13312);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize13312 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize14336",
                "gRPC_UNI", amounts, 14336);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize14336 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize15360",
                "gRPC_UNI", amounts, 15360);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize15360 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize16384",
                "gRPC_UNI", amounts, 16384);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize16384 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize20480",
                "gRPC_UNI", amounts, 20480);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize20480 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize24576",
                "gRPC_UNI", amounts, 24576);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize24576 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize28672",
                "gRPC_UNI", amounts, 28672);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize28672 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize32768",
                "gRPC_UNI", amounts, 32768);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize32768 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize36864",
                "gRPC_UNI", amounts, 36864);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize36864 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize40960",
                "gRPC_UNI", amounts, 40960);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize40960 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize45056",
                "gRPC_UNI", amounts, 45056);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize45056 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize49152",
                "gRPC_UNI", amounts, 49152);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize49152 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize53248",
                "gRPC_UNI", amounts, 53248);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize53248 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize57344",
                "gRPC_UNI", amounts, 57344);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize57344 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize61440",
                "gRPC_UNI", amounts, 61440);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize61440 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize65536",
                "gRPC_UNI", amounts, 65536);

        System.out.println("test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_batchSize65536 done");
    }

    @Test
    public void test_gRPC_stream_SingularCalls() throws IOException {
        var testHelper = new TestHelper<>(people5GrpcService, objectMapper, people5GrpcService::people5Stream);

        var amounts1 = List.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512);
        var amounts2 = List.of(1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288);
        var amounts3 = List.of(1048576);
        var amounts4 = List.of(1572864);
        var amounts5 = List.of(2097152);
        var amounts6 = List.of(8388608);
        var amounts7 = List.of(16777216);
        var amounts8 = List.of(2097152);
        var amounts9 = List.of(2621440);
        var amounts10 = List.of(3145728);
        var amounts11 = List.of(3670016);
        var amounts12 = List.of(4194304);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_1", "gRPC_MULTI", amounts1);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_2", "gRPC_MULTI", amounts2);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_3", "gRPC_MULTI", amounts3);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_4", "gRPC_MULTI", amounts4);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_5", "gRPC_MULTI", amounts5);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_6", "gRPC_MULTI", amounts6);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_7", "gRPC_MULTI", amounts7);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_8", "gRPC_MULTI", amounts8);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_9", "gRPC_MULTI", amounts9);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_10", "gRPC_MULTI", amounts10);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_11", "gRPC_MULTI", amounts11);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_12", "gRPC_MULTI", amounts12);
    }

    @Test
    public void test_gRPC_stream_SingularCalls_2() throws IOException {
        var testHelper = new TestHelper<>(people5GrpcService, objectMapper, people5GrpcService::people5Stream);

        var amounts1 = List.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512);
        var amounts2 = List.of(1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_1", "gRPC_MULTI", amounts1);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_2", "gRPC_MULTI", amounts2);
    }

    @Test
    public void test_gRPC_stream_SingularCalls_3() throws IOException {
        var testHelper = new TestHelper<>(people5GrpcService, objectMapper, people5GrpcService::people5Stream);

        var amounts2 = List.of(4194304);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_4", "gRPC_MULTI", amounts2);
    }
}
