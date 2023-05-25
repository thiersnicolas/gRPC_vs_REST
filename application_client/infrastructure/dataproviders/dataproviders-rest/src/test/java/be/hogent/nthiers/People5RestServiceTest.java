package be.hogent.nthiers;

import be.hogent.thiersn.TestHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

@QuarkusTest
public class People5RestServiceTest {
    @RestClient
    People5RestService people5RestService;
    @Inject
    ObjectMapper objectMapper;


    @Test
    public void test_REST_SingularCalls() throws IOException {
        var testHelper = new TestHelper<>(() -> people5RestService.clearData(), objectMapper, people5RestService::people5);

        //var amounts = List.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288);

        var amounts = List.of(1024, 2048, 4096, 8192, 9216, 10240, 11264, 12288, 13312, 14336, 15360,
                16384, 20480, 24576, 28672, 32768, 36864, 40960, 45056, 49152, 53248, 57344, 61440, 65536, 131072,
                262144, 524288);

        testHelper.testPerformance_singularRequests("REST_SingularCalls", amounts);
    }

    @Test
    public void test_REST_compressed_SingularCalls() throws IOException {
        var testHelper = new TestHelper<>(() -> people5RestService.clearData(), objectMapper, amount ->
                gzipDecompressBytes(people5RestService.people5Compressed(amount)));

        //var amounts = List.of(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288);

        var amounts = List.of(1024, 2048, 4096, 8192, 9216, 10240, 11264, 12288, 13312, 14336, 15360,
                16384, 20480, 24576, 28672, 32768, 36864, 40960, 45056, 49152, 53248, 57344, 61440, 65536, 131072,
                262144, 524288);

        testHelper.testPerformance_singularRequests("test_REST_compressed_SingularCalls", amounts);
    }

    @Test
    public void test_REST_MultipleBatchedCalls_DifferentBatchSizes() throws IOException {
        var testHelper = new TestHelper<>(() -> people5RestService.clearData(), objectMapper, people5RestService::people5);

        var amounts = List.of(4194304);

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024", amounts, 1024);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize2048", amounts, 2048);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize2048 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize4096", amounts, 4096);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize4096 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize8192", amounts, 8192);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize8192 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize9216", amounts, 9216);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize9216 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize10240", amounts, 10240);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize10240 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize11264", amounts, 11264);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize11264 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize12288", amounts, 12288);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize12288 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize13312", amounts, 13312);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize13312 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize14336", amounts, 14336);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize14336 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize15360", amounts, 15360);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize15360 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize16384", amounts, 16384);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize16384 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize20480", amounts, 20480);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize20480 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize24576", amounts, 24576);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize24576 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize28672", amounts, 28672);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize28672 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize32768", amounts, 32768);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize32768 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize36864", amounts, 36864);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize36864 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize40960", amounts, 40960);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize40960 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize45056", amounts, 45056);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize45056 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize49152", amounts, 49152);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize49152 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize53248", amounts, 53248);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize53248 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize57344", amounts, 57344);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize57344 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize61440", amounts, 61440);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize61440 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize65536", amounts, 65536);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize65536 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize131072", amounts, 131072);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize131072 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize262144", amounts, 262144);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize262144 done");

        testHelper.testPerformance_multipleRequests("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize524288", amounts, 524288);

        System.out.println("REST_MultipleBatchedCalls_DifferentBatchSizes_batchSize524288 done");
    }

    @Test
    public void test_REST_compressed_MultipleBatchedCalls_DifferentBatchSizes() throws IOException {
        var testHelper = new TestHelper<>(() -> people5RestService.clearData(), objectMapper, amount ->
                gzipDecompressBytes(people5RestService.people5Compressed(amount)));

        var amounts = List.of(4194304);

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024", amounts, 1024);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize1024 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize2048", amounts, 2048);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize2048 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize4096", amounts, 4096);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize4096 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize8192", amounts, 8192);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize8192 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize9216", amounts, 9216);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize9216 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize10240", amounts, 10240);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize10240 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize11264", amounts, 11264);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize11264 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize12288", amounts, 12288);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize12288 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize13312", amounts, 13312);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize13312 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize14336", amounts, 14336);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize14336 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize15360", amounts, 15360);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize15360 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize16384", amounts, 16384);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize16384 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize20480", amounts, 20480);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize20480 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize24576", amounts, 24576);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize24576 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize28672", amounts, 28672);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize28672 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize32768", amounts, 32768);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize32768 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize36864", amounts, 36864);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize36864 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize40960", amounts, 40960);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize40960 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize45056", amounts, 45056);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize45056 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize49152", amounts, 49152);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize49152 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize53248", amounts, 53248);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize53248 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize57344", amounts, 57344);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize57344 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize61440", amounts, 61440);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize61440 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize65536", amounts, 65536);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize65536 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize131072", amounts, 131072);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize131072 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize262144", amounts, 262144);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize262144 done");

        testHelper.testPerformance_multipleRequests("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize524288", amounts, 524288);

        System.out.println("REST_compressed_MultipleBatchedCalls_DifferentBatchSizes_batchSize524288 done");
    }

    public <T> List<T> gzipDecompressBytes(byte[] compressed) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            final int BUFFER_SIZE = 32;
            ByteArrayInputStream is = new ByteArrayInputStream(compressed);
            GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);
            byte[] data = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = gis.read(data)) != -1) {
                os.write(data, 0, bytesRead);
            }
            gis.close();
            return objectMapper.readValue(os.toByteArray(), new TypeReference<>() {
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
