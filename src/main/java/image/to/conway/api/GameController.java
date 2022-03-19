package image.to.conway.api;

import image.to.conway.service.GameService;
import image.to.conway.service.ImageService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RestController
@Path("/game")
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
    @POST
    @Path("/upload")
    @Override
    public Response uploadImage(String url, int width, int height) {
        return imageService.uploadImage(url, width, height).map(s -> Response.ok(s).build()).orElse(Response.status(400).build());
    }

    @GET
    @Override
    public Response iterate(@PathParam("url") String url) {
        return gameService.iterate(url).map(s -> Response.ok(s).build()).orElse(Response.status(400).build());
    }
}
