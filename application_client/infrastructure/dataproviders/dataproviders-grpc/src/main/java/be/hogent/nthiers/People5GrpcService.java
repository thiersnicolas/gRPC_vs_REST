package be.hogent.nthiers;

import be.hogent.thiersn.People5Service;
import io.quarkus.grpc.GrpcClient;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.List;

@ApplicationScoped
public class People5GrpcService implements People5Service<GrpcPerson5> {
    @GrpcClient("people5Grpc")
    People5Grpc people5Grpc;

    @Override
    public Collection<Integer> clearData() {
        return people5Grpc.clearData(ClearDataInput.newBuilder().build())
                .await().indefinitely().getSupportedAmountsList();
    }

    @Override
    public List<GrpcPerson5> people5(int amount) {
        return people5Grpc.getPeople(Amount.newBuilder().setAmount(amount).build())
                .await().indefinitely().getPeopleList();
    }

    @Override
    public Collection<Integer> supportedAmounts() {
        return people5Grpc.getSupportedAmounts(SupportedAmountsInput.newBuilder().build())
                .await().indefinitely().getSupportedAmountsList();
    }
}
