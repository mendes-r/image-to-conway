package image.to.conway.repository;

import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

@Component
public interface RepositoryApi {
    String saveImage(BufferedImage image);
    BufferedImage getImage(String key);
}
