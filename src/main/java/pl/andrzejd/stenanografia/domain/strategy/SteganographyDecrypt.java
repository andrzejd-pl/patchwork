package pl.andrzejd.stenanografia.domain.strategy;

import java.awt.image.BufferedImage;

public interface SteganographyDecrypt {
    float readInformation(BufferedImage image, long seed, int n);
    float readInformation(BufferedImage image, long seed, int n, float brightness);
}
