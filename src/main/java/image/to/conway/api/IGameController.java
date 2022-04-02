package image.to.conway.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGameController {

    ResponseEntity<String> uploadImage(String url, float widthRatio, float heightRatio);
    ResponseEntity<List<String>> getIterations(String url, int iterations);

}
