package be.hogent.nthiers;

import be.hogent.thiersn.People5Service;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class People5GrpcService implements People5Service<GrpcPerson5> {
    @GrpcClient("production")
    People5Grpc people5Grpc;

    @Override
    public Collection<Integer> clearData() {
        return people5Grpc.clearData(ClearDataInput.newBuilder().build())
                .await().indefinitely().getSupportedAmountsList();
    }

    public List<GrpcPerson5> people5(int amount) {
        return people5Grpc.getPeople(Amount.newBuilder().setAmount(amount).build())
                .await().indefinitely().getPeopleList();
    }

    public List<GrpcPerson5> people5Stream(int amount) {
        return people5Grpc.getPeopleStream(Amount.newBuilder().setAmount(amount).build()).collect()
                .asList().await().indefinitely();
    }

    public Multi<GrpcPerson5> people5Multi(int amount) {
        return people5Grpc.getPeopleStream(Amount.newBuilder().setAmount(amount).build());
    }
}
