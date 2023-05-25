package be.hogent.nthiers;

import be.hogent.thiersn.People5Service;
import io.quarkus.vertx.http.Compressed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Collection;
import java.util.List;

@Path("/people5")
@RegisterRestClient(configKey = "people5")
public interface People5RestService extends People5Service<Person5Dto> {

    @POST
    @Path("/cleardata")
    Collection<Integer> clearData();

    @GET
    @Path("/{amount}")
    List<Person5Dto> people5(@PathParam("amount") int amount);

    @GET
    @Path("/{amount}/compressed")
    @Compressed
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    byte[] people5Compressed(@PathParam("amount") int amount);

}
