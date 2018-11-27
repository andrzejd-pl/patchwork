package pl.andrzejd.stenanografia.domain.algorithm;

import pl.andrzejd.stenanografia.domain.strategy.SteganographyEncrypt;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PatchworksEncrypt implements SteganographyEncrypt {
    @Override
    public float hideInformation(BufferedImage image, long seed, int n) {
        return hideInformation(image, seed, n, new Random().nextFloat());
    }

    @Override
    public float hideInformation(BufferedImage image, long seed, int n, float brightness) {
        Random random = new Random(seed);
        float sum = 2 * brightness * n;

        for (int i = 0; i < n; i++) {
            int[] pixelRgb = new int[4];
            float[] hsbvals = new float[3];

            int x = Math.abs(random.nextInt()) % image.getHeight();
            int y = Math.abs(random.nextInt()) % image.getHeight();

            Color.RGBtoHSB(pixelRgb[0], pixelRgb[1], pixelRgb[2], hsbvals);
            Color c = new Color(Color.HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2] + brightness));
            float firstBrightness = hsbvals[2];

            image.getRaster().setPixel(x, y, new int[]{c.getRed(), c.getGreen(), c.getBlue(), pixelRgb[3]});

            x = Math.abs(random.nextInt()) % image.getHeight();
            y = Math.abs(random.nextInt()) % image.getHeight();

            Color.RGBtoHSB(pixelRgb[0], pixelRgb[1], pixelRgb[2], hsbvals);
            c = new Color(Color.HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2] - brightness));
            sum += (firstBrightness - hsbvals[2]);

            image.getRaster().setPixel(x, y, new int[]{c.getRed(), c.getGreen(), c.getBlue(), pixelRgb[3]});
        }

        return sum;
    }
}
