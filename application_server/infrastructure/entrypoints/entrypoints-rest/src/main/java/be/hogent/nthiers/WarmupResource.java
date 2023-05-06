package be.hogent.nthiers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/warmup")
public class WarmupResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String warmup() {
        return "warming up";
    }
}
