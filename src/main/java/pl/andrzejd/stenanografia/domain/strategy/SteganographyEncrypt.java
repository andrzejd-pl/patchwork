package pl.andrzejd.stenanografia.domain.strategy;

import java.awt.image.BufferedImage;

public interface SteganographyEncrypt {
    float hideInformation(BufferedImage image, long seed, int n);
    float hideInformation(BufferedImage image, long seed, int n, float brightness);
}
