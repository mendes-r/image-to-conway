package image.to.conway.constant;

public enum FileType {
    JPG("jpg"),
    PNG("png");

    private String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
