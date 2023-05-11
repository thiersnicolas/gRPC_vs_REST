package be.hogent.nthiers;

import jakarta.enterprise.context.ApplicationScoped;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

@ApplicationScoped
public class People5RestClient {
    HttpClient httpClient;

    public People5RestClient() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(15))
                .build();
    }

    public Collection<Integer> clearData() {
        return Collections.emptyList();
    }
}
