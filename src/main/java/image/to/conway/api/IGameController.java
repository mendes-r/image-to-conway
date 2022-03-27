package image.to.conway.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGameController {

    ResponseEntity<String> uploadImage(String url, int width, int height);
    ResponseEntity<List<String>> iterate(String url, int iterations);

}
