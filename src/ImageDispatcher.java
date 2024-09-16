import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;


public class ImageDispatcher {
    public int width;
    public int height;
    public int[] pixels;
    public final char[] desityScale = "¶@ØÆMåBNÊßÔR#8Q&mÃ0À$GXZA5ñk2S%±3Fz¢yÝCJf1t7ªLc¿+?(r/¤²!*;\"^:,'.`".toCharArray();
    private final int koef = (16777216 / desityScale.length);

    public int getRed(int color) {
        return color >> 16;
    }         // получить красную составляющую цвета

    public int getGreen(int color) {
        return (color >> 8) & 0xFF;
    } // получить зеленую составляющую цвета

    public int getBlue(int color) {
        return color & 0xFF;
    }        // получить синюю   составляющую цвета

    public ImageDispatcher(BufferedImage image) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = grabPixels(image);
    }

    private int[] grabPixels(BufferedImage bi) {
        int[] pict = new int[height * width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                pict[i * width + j] = bi.getRGB(j, i) & 0xFFFFFF; // 0xFFFFFF: записываем только 3 младших байта RGB
        return pict;
    }

    public void convertToASCII(String filename) {
        try (FileWriter recorder = new FileWriter("filename")) {
            int indexDesity;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int intens = (getRed(pixels[i * width + j]) + getGreen(pixels[i * width + j]) + getBlue(pixels[i * width + j])) / 3;
                    pixels[i * width + j] = intens + (intens << 8) + (intens << 16);
                    indexDesity = (pixels[i * width + j] / (koef));
                    indexDesity = indexDesity == 65 ? 64 : indexDesity;
                    recorder.write(desityScale[indexDesity] + " ");
                }
                recorder.write('\n');
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
