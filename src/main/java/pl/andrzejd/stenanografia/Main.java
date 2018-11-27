package pl.andrzejd.stenanografia;

import pl.andrzejd.stenanografia.domain.algorithm.PatchworksDecrypt;
import pl.andrzejd.stenanografia.domain.algorithm.PatchworksEncrypt;
import pl.andrzejd.stenanografia.domain.strategy.SteganographyDecrypt;
import pl.andrzejd.stenanografia.domain.strategy.SteganographyEncrypt;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("oldImage.jpg"));
        File output = new File("newImage.jpg");
        float brightness = 0.2f;

        SteganographyEncrypt encrypt = new PatchworksEncrypt();
        float encryptedSum = encrypt.hideInformation(image, 4457, 100, brightness);
        ImageIO.write(image, "jpg", output);

        image = ImageIO.read(output);
        output = new File("decryptedImage.jpg");
        SteganographyDecrypt decrypt = new PatchworksDecrypt();
        float decryptedSum = decrypt.readInformation(image, 4457, 100, brightness);

        ImageIO.write(image, "jpg", output);
    }
}
