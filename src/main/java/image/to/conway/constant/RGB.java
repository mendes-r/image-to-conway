package image.to.conway.constant;

public enum RGB {

    BLACK(0),
    WHITE(255);

    int rgb;

    RGB(int rgb) {
        this.rgb = rgb;
    }

    public int getCode() {
        return rgb;
    }
}
