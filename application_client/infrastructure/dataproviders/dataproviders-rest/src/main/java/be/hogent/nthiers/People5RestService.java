package be.hogent.nthiers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Collection;
import java.util.List;

@Path("/people5")
@RegisterRestClient
public interface People5RestService {

    @POST
    Collection<Integer> clearData();

    @GET
    @Path("/{amount}")
    List<Person5Dto> people5(@PathParam("amount") int amount);

    @GET
    Collection<Integer> supportedAmounts();
}
