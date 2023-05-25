package be.hogent.nthiers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.vertx.http.Uncompressed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@Path("/people5")
public class People5Resource {
    @Inject
    PeopleInMemoryRepository peopleInMemoryRepository;
    @Inject
    ObjectMapper objectMapper;

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
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] people5compressed(@PathParam("amount") Integer amount) {
        return gzip(peopleInMemoryRepository.getPeople5(amount));
    }

    public <T> byte[] gzip(final List<T> o) {
        try {
            if (o == null) {
                return null;
            }
            try (final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                 final GZIPOutputStream gzip = new GZIPOutputStream(baos)) {
                gzip.write(objectMapper.writeValueAsBytes(o));
                gzip.close();
                return baos.toByteArray();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
