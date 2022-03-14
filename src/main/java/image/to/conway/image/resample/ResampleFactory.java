package image.to.conway.image.resample;

import org.springframework.stereotype.Component;

@Component
public class ResampleFactory {

    public ImageResample getBilinearResize() {
        return new BilinearResample();
    }
}
