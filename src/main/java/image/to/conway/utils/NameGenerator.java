package image.to.conway.utils;

import java.time.Instant;

public final class NameGenerator {

    // TODO optimize name attribution
    private static long count = 0;

    private NameGenerator() {}

    public static String getAFileName(String fileType) {
        count++;
        return Instant.now().getEpochSecond() + "-" + count + "." + fileType;
    }


}
