package be.hogent.nthiers;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

@GrpcService
public class People5GrpcService implements People5Grpc {
    @Inject
    GrpcPeopleCache peopleCache;

    @Override
    public Uni<SupportedAmounts> clearData(ClearDataInput request) {
        peopleCache.clearData();
        return Uni.createFrom().item(() -> SupportedAmounts.newBuilder()
                .addAllSupportedAmounts(peopleCache.getPeople5SupportedAmounts())
                .build()
        );
    }

    @Override
    public Multi<GrpcPerson5> getPeopleStream(Amount request) {
        return Multi.createFrom().items(() -> peopleCache.getPeople5(request.getAmount()).stream());
    }

    @Override
    public Uni<GrpcPeople5> getPeople(Amount request) {
        return Uni.createFrom().item(() -> GrpcPeople5.newBuilder()
                .addAllPeople(peopleCache.getPeople5(request.getAmount()))
                .build());
    }
}
