import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedImage picture = ImageIO.read(new File(args[0]));
        ImageDispatcher dispatcher = new ImageDispatcher(picture); // загружаем файл изображения
        dispatcher.convertToASCII("text.txt");

    }
}

