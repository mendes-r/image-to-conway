package image.to.conway.constant;

public enum RGB {

    BLACK(0),
    WHITE(255);

    private final int code;

    RGB(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
