package be.hogent.thiersn;

import be.hogent.nthiers.GrpcPerson5;
import be.hogent.nthiers.People5GrpcService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@QuarkusTest
public class People5GrpcServiceTest {
    @Inject
    People5GrpcService people5GrpcService;
    @Inject
    ObjectMapper objectMapper;

    @Test
    public void test_gRPC_SingularCalls() throws IOException {
        var testHelper = new TestHelper<>(() -> people5GrpcService.clearData(), objectMapper, people5GrpcService::people5);

        var amounts = List.of(1024, 2048, 4096, 8192,9216, 10240, 11264, 12288, 13312, 14336, 15360,
                16384, 20480, 24576, 28672, 32768, 36864, 40960, 45056, 49152, 53248, 57344, 61440, 65536, 131072,
                262144, 524288);
        testHelper.testPerformance_singularRequests("testGrpc_SingularCalls_Uni_23-05-2023_2", amounts);
    }

    @Test
    public void test_gRPC_stream_SingularCalls() throws IOException {
        var testHelper = new TestHelper<>(() -> people5GrpcService.clearData(), objectMapper,
                amount -> people5GrpcService.people5Stream(amount).collect().asList().await().indefinitely());

        var amounts1 = List.of(1024, 2048, 4096, 8192,9216, 10240, 11264, 12288, 13312, 14336, 15360,
                16384, 20480, 24576, 28672, 32768, 36864, 40960, 45056, 49152, 53248, 57344, 61440, 65536, 131072,
                262144, 524288);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_23-05-2023_2", amounts1);
    }

    @Test
    public void test_gRPC_MultipleBatchedCalls_DifferentBatchSizes() throws IOException {
        var testHelper = new TestHelper<>(() -> people5GrpcService.clearData(), objectMapper, people5GrpcService::people5);

        var amounts = List.of(4194304);

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024", amounts, 1024);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize2048", amounts, 2048);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize2048 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize4096", amounts, 4096);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize4096 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize8192", amounts, 8192);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize8192 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize9216", amounts, 9216);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize9216 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize10240", amounts, 10240);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize10240 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize11264", amounts, 11264);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize11264 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize12288", amounts, 12288);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize12288 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize13312", amounts, 13312);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize13312 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize14336", amounts, 14336);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize14336 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize15360", amounts, 15360);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize15360 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize16384", amounts, 16384);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize16384 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize20480", amounts, 20480);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize20480 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize24576", amounts, 24576);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize24576 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize28672", amounts, 28672);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize28672 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize32768", amounts, 32768);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize32768 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize36864", amounts, 36864);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize36864 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize40960", amounts, 40960);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize40960 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize45056", amounts, 45056);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize45056 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize49152", amounts, 49152);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize49152 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize53248", amounts, 53248);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize53248 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize57344", amounts, 57344);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize57344 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize61440", amounts, 61440);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize61440 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize65536", amounts, 65536);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize65536 done");
        
        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize131072", amounts, 131072);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize131072 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize262144", amounts, 262144);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize262144 done");

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize524288", amounts, 524288);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize524288 done");
    }

    @Test
    public void test_gRPC_MultipleBatchedCalls_DifferentBatchSizes_big() throws IOException {
        var testHelper = new TestHelper<>(() -> people5GrpcService.clearData(), objectMapper, people5GrpcService::people5);

        var amounts = List.of(8388608);

        testHelper.testPerformance_multipleRequests("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_big_batchSize524288", amounts, 524288);

        System.out.println("test_gRPC_Uni_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024 done");
    }

    @Test
    public void test_gRPC_stream_SingularCalls_batch() throws IOException {
        int batchSize = 12288;
        var testHelper = new TestHelper<>(() -> people5GrpcService.clearData(), objectMapper,
                amount -> {
                    List<GrpcPerson5> people = new LinkedList<>();
                    AtomicInteger count = new AtomicInteger();
                    AtomicBoolean done = new AtomicBoolean(false);
                    people5GrpcService.people5Stream(amount)
                            .subscribe().with(
                                    person -> {
                                        //count.incrementAndGet();
                                        //people.add(person);
                                        /**if (people.size() == batchSize) {
                                            people.clear();
                                            System.out.println("batch received, count: " + count.get());
                                        }**/
                                    },
                                    failure -> System.out.println("Something went wrong " + failure),
                                    () -> {
                                        //System.out.println("amount of elements received: " + count.get());
                                        done.set(true);
                                    });
                    while (!done.get()) {

                    }
                    System.out.println("done");
                    return count;
                }
        );
        var amount = 8388608;
        var amounts2 = List.of(amount);
        testHelper.testPerformance_singularRequests("test_gRPC_SingularCalls_Multi_noCountNoList" + amount, amounts2);
    }
}
