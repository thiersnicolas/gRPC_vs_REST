package be.hogent.nthiers;

import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class GrpcPeopleCache {
    @Inject
    public PeopleInMemoryRepository peopleInMemoryRepository;

    private Map<Integer, List<GrpcPerson5>> grpcPeople5 = Collections.emptyMap();

    public void clearData() {
        grpcPeople5 = Collections.emptyMap();
    }

    public Multi<GrpcPerson5> getPeople5(Integer amount) {
        return Multi.createFrom()
                .items(grpcPeople5.computeIfAbsent(amount,
                                a -> peopleInMemoryRepository.getPeople5(a).stream()
                                        .map(this::mapDomainToGrpc)
                                        .toList()
                        ).stream()
                );
    }

    public Collection<Integer> getPeople5SupportedAmounts() {
        return grpcPeople5.keySet();
    }

    private GrpcPerson5 mapDomainToGrpc(Person5 person5) {
        return GrpcPerson5.newBuilder()
                .setFirstname(person5.getFirstname())
                .setLastname(person5.getLastname())
                .setBirthYear(person5.getBirthYear())
                .setBirtMonth(person5.getBirtMonth())
                .setBirtDay(person5.getBirthYear())
                .build();
    }
}
