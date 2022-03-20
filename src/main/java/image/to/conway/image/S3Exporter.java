package image.to.conway.image;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

@Component("s3-exporter")
@NoArgsConstructor
public class S3Exporter {

    public String exportImage(BufferedImage image) {
        // TODO
        return null;
    }

}
