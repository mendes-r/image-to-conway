package image.to.conway.api;

import image.to.conway.service.GameService;
import image.to.conway.service.ImageService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.slf4j.Logger;

@RestController
@RequiredArgsConstructor
public class GameController implements IGameController {

    private final ImageService imageService;
    private final GameService gameService;
    private final Logger logger;


    /**
     * Uploads image, resize it and turns it into a binary image.
     *
     * @param url    image location
     * @param widthRatio  ratio
     * @param heightRatio ratio
     *@return Http response with uploaded image
     */
    @PostMapping("/upload")
    @Override
    public ResponseEntity<String> uploadImage(@RequestParam String url, @RequestParam float widthRatio, @RequestParam float heightRatio) {
        // TODO should the image be uploaded or just start the game without saving the original image?
        logger.info("Starting image upload from {}", url);
        return imageService.uploadImage(url, widthRatio, heightRatio).map(s -> ResponseEntity.ok().body(s)).orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Get url of all the images of each iteration.
     *
     * @param url image location
     * @param iterations number of iterations
     * @return Http response with the urls of the images of each iteration
     */
    @GetMapping("/iterate")
    @Override
    public ResponseEntity<List<String>> getIterations(@RequestParam String url, @RequestParam int iterations) {
        logger.info("Starting iterations: {} number of times, beginning with image from {}", iterations, url);
        return gameService.getIterations(url, iterations).map(s -> ResponseEntity.ok().body(s)).orElse(ResponseEntity.badRequest().build());
    }
}
