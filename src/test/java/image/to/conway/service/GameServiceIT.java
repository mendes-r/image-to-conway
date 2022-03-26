package image.to.conway.service;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag("IntegrationTests")
class GameServiceIT {

    @Autowired
    GameService service;
    String firstUrl = "src/test/resources/imagetests/03.jpg";

    @Test
    void playGame_success() {
        // arrange
        int iterations = 10;

        // act
        Optional<List<String>> optional = service.iterate(firstUrl, iterations);

        // assert
        assertTrue(optional.isPresent());
        List<String> urls = optional.get();
        assertEquals(iterations, urls.size());
        assertTrue(filesExist(urls));
    }

    private boolean filesExist(List<String> urls) {
        boolean flag = true;
        for (int i = 0; i < urls.size() && flag; i++) {
            flag = fileExist(urls.get(i));
        }
        return flag;
    }

    private boolean fileExist(String url) {
        File file = new File(url);
        return file.exists();
    }
}
