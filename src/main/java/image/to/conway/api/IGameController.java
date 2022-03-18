package image.to.conway.api;

import javax.ws.rs.core.Response;

public interface IGameController {

    Response uploadImage(String url, int width, int height);
    Response iterate();

}
