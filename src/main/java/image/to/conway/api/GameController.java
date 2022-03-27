package image.to.conway.api;

import image.to.conway.service.GameService;
import image.to.conway.service.ImageService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
public class GameController implements IGameController {

    @Autowired
    private final ImageService imageService;
    @Autowired
    private final GameService gameService;
    @Autowired
    private final Logger logger;


    /**
     * Uploads image.
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
        logger.info("Starting image upload from " + url);
        return imageService.uploadImage(url, widthRatio, heightRatio).map(s -> ResponseEntity.ok().body(s)).orElse(ResponseEntity.badRequest().build());
    }

    /**
     *
     * @param url image location
     * @param iterations number of iterations
     * @return Http response with the images of the iterations
     */
    @GetMapping("/iterate")
    @Override
    public ResponseEntity<List<String>> iterate(@RequestParam String url, @RequestParam int iterations) {
        logger.info("Starting iterations: " + iterations + " number of times, beginning with image from " + url);
        return gameService.iterate(url, iterations).map(s -> ResponseEntity.ok().body(s)).orElse(ResponseEntity.badRequest().build());
    }
}
