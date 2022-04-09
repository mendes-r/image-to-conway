package image.to.conway.api;

import image.to.conway.service.GameService;
import image.to.conway.service.ImageService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GameController implements IGameController {

    private final ImageService imageService;
    private final GameService gameService;

    /**
     * Uploads image, resize it and turns it into a binary image.
     *
     * @param url         image location
     * @param widthRatio  ratio
     * @param heightRatio ratio
     * @return Http response with uploaded image
     */
    @PostMapping("/upload")
    @Override
    public ResponseEntity<String> uploadImage(@RequestParam String url, @RequestParam float widthRatio, @RequestParam float heightRatio) {
        MDC.put("src-url", url);
        log.info("Starting upload.");
        return imageService.uploadImage(url, widthRatio, heightRatio).map(s -> ResponseEntity.ok().body(s)).orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Get url of all the images of each iteration.
     *
     * @param url        image location
     * @param iterations number of iterations
     * @return Http response with the urls of the images of each iteration
     */
    @GetMapping("/iterate")
    @Override
    public ResponseEntity<List<String>> getIterations(@RequestParam String url, @RequestParam int iterations) {
        MDC.put("src-url", url);
        log.info("Starting iterations: {} number of times.", iterations);
        return gameService.getIterations(url, iterations).map(s -> ResponseEntity.ok().body(s)).orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Get url of a gif file with all iterations.
     *
     * @param url        image location
     * @param iterations number of iterations
     * @return Http response with the url of the gif file with the iterations
     */
    @GetMapping("/gif")
    @Override
    public ResponseEntity<String> getGif(@RequestParam String url, @RequestParam int iterations) {
        MDC.put("src-url", url);
        log.info("Starting iterations: {} number of times for gif generation", iterations);
        return gameService.getGif(url, iterations).map(s -> ResponseEntity.ok().body(s)).orElse(ResponseEntity.badRequest().build());
    }
}
