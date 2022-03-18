package image.to.conway.api;

import image.to.conway.service.GameService;
import image.to.conway.service.ImageService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequiredArgsConstructor
public class GameController implements IGameController {

    @Autowired
    private final ImageService imageService;
    @Autowired
    private final GameService gameService;


    /**
     * Uploads image.
     *
     * @param url    image location
     * @param width  in pixels
     * @param height in pixels
     */
    @PostMapping
    @Override
    public Response uploadImage(String url, int width, int height) {
        return imageService.uploadImage(url, width, height).map(s -> Response.ok(s).build()).orElse(Response.status(400).build());
    }

    @GetMapping
    @Override
    public Response iterate() {
        return gameService.iterate().map(s -> Response.ok(s).build()).orElse(Response.status(400).build());
    }
}
