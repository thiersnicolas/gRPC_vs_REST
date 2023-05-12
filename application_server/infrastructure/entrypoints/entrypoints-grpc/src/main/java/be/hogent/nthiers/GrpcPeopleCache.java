package be.hogent.nthiers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class GrpcPeopleCache {
    @Inject
    public PeopleInMemoryRepository peopleInMemoryRepository;

    private Map<Integer, List<GrpcPerson5>> grpcPeople5 = new HashMap<>();

    public void clearData() {
        grpcPeople5.clear();
    }

    public List<GrpcPerson5> getPeople5(Integer amount) {
        return grpcPeople5.computeIfAbsent(amount,
                a -> peopleInMemoryRepository.getPeople5(a).stream()
                        .map(this::mapDomainToGrpc)
                        .toList()
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
