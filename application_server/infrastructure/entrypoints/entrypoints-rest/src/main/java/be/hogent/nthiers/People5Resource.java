package be.hogent.nthiers;

import io.quarkus.vertx.http.Compressed;
import io.quarkus.vertx.http.Uncompressed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Encoded;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.GZIP;

import java.util.Collection;
import java.util.List;

@Path("/people5")
public class People5Resource {
    @Inject
    PeopleInMemoryRepository peopleInMemoryRepository;

    @Path("/cleardata")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Integer> cleardata() {
        peopleInMemoryRepository.clearData();
        return peopleInMemoryRepository.getPeople5SupportedAmounts();
    }

    @Path("/{amount}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Uncompressed
    public List<Person5> people5(@PathParam("amount") Integer amount) {
        return peopleInMemoryRepository.getPeople5(amount);
    }

    @Path("/{amount}/compressed")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Compressed
    public List<Person5> people5compressed(@PathParam("amount") Integer amount) {
        return peopleInMemoryRepository.getPeople5(amount);
    }
}
