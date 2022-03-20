package image.to.conway.image;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Tag("IntegrationTests")
class FolderExporterIT {

    @Autowired
    FolderExporter exporter;

    @Test
    void exportImage() {

    }
}
