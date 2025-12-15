package ru.nsu.javagame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class SpriteManager {
    private Image sprite;

    private ArrayList<Image> anim;
    private int animI = -1;
    private int animDelay = 1;
    private int delayCounter = 0;

    private boolean play = false;
    private boolean cycle = false;

    public int width = 200;
    public int height = 100;

    public double x = 0;
    public double y = 0;

    public void setSprite(Image img) {
        sprite = img;
    }

    public void startAnimation(ArrayList<Image> animation, boolean animCycle, int delay) {
        if (animation.isEmpty())
            return;

        anim = animation;
        animI = 0;
        animDelay = delay;
        play = true;
        cycle = animCycle;
    }

    public void stopAnimation() {
        play = false;
    }

    public void resumeAnimation() {
        if (animI == -1)
            return;

        play = true;
    }

    public Image getSprite() {
        if (!play)
            return sprite;

        if (++delayCounter >= animDelay) {
            Image result = anim.get(animI++);

            if (animI >= anim.size()) {
                animI = 0;
                play = cycle;
            }

            delayCounter = 0;
            return result;
        }
        return anim.get(animI);
    }

    ImageView getImageView() {
        ImageView imageView = new ImageView(getSprite());
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }
}
