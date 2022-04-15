package image.to.conway.image;

import image.to.conway.utils.NameGenerator;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.*;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Component
@NoArgsConstructor
public class GifMaker {

    @Value("${app.save.to.url}")
    private String saveToURL;
    @Value("${app.gif.loop}")
    private boolean loop;
    @Value("${app.gif.delay}")
    private int delay;

    /**
     * Creates a gif from a list of images.
     * <p>
     * source https://github.com/ha-shine/Giffer
     *
     * @param images list of images
     */
    public BufferedImage fromImages(List<BufferedImage> images) throws IOException {
        ImageWriter writer = getWriter();
        String url = saveToURL + NameGenerator.getAFileName("gif");
        try (ImageOutputStream imageOS = ImageIO.createImageOutputStream(new File(url))) {
            IIOMetadata metadata = getMetadata(writer);
            writer.setOutput(imageOS);
            writer.prepareWriteSequence(null);
            for (BufferedImage img : images) {
                IIOImage temp = new IIOImage(img, null, metadata);
                writer.writeToSequence(temp, null);
            }
            writer.endWriteSequence();
        }
        return null;
    }

    private ImageWriter getWriter() throws IIOException {
        Iterator<ImageWriter> itr = ImageIO.getImageWritersByFormatName("gif");
        if (itr.hasNext()) return itr.next();
        throw new IIOException("GIF writer doesn't exist on this JVM!");
    }

    private IIOMetadata getMetadata(ImageWriter writer)
            throws IIOInvalidTreeException {
        ImageTypeSpecifier imgType = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);
        IIOMetadata metadata = writer.getDefaultImageMetadata(imgType, null);
        String nativeFormat = metadata.getNativeMetadataFormatName();
        IIOMetadataNode nodeTree = (IIOMetadataNode) metadata.getAsTree(nativeFormat);

        // Set the delay time you can see the format specification on this page
        // https://docs.oracle.com/javase/7/docs/api/javax/imageio/metadata/doc-files/gif_metadata.html
        IIOMetadataNode graphicsNode = getNode("GraphicControlExtension", nodeTree);
        graphicsNode.setAttribute("delayTime", String.valueOf(delay));
        graphicsNode.setAttribute("disposalMethod", "none");
        graphicsNode.setAttribute("userInputFlag", "FALSE");

        if (loop) makeLoopy(nodeTree);
        metadata.setFromTree(nativeFormat, nodeTree);
        return metadata;
    }

    private void makeLoopy(IIOMetadataNode root) {
        IIOMetadataNode appExtensions = getNode("ApplicationExtensions", root);
        IIOMetadataNode appNode = getNode("ApplicationExtension", appExtensions);

        appNode.setAttribute("applicationID", "NETSCAPE");
        appNode.setAttribute("authenticationCode", "2.0");
        appNode.setUserObject(new byte[]{0x1, (byte) (0 & 0xFF), (byte) ((0 >> 8) & 0xFF)});

        appExtensions.appendChild(appNode);
        root.appendChild(appExtensions);
    }

    private IIOMetadataNode getNode(String nodeName, IIOMetadataNode root) {
        IIOMetadataNode node = null;

        for (int i = 0; i < root.getLength(); i++) {
            if (root.item(i).getNodeName().compareToIgnoreCase(nodeName) == 0) {
                node = (IIOMetadataNode) root.item(i);
                return node;
            }
        }

        node = new IIOMetadataNode(nodeName);
        root.appendChild(node);
        return node;
    }

}
