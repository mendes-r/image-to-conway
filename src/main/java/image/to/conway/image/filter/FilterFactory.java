package image.to.conway.image.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FilterFactory {

    @Value("${app.bilinear.threshold}")
    private short threshold;

    public ImageFilter getBinaryFilter() {
        return new BinaryFilter(threshold);
    }
}
