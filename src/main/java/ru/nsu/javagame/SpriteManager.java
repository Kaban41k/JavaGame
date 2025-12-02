package ru.nsu.javagame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class SpriteManager {
    private Image sprite;

    private ArrayList<Image> anim;
    private int animI = -1;

    private boolean play = false;
    private boolean cycle = false;

    public int width = 150;
    public int height = 150;

    public int x = 0;
    public int y = 0;

    public void setSprite(Image img) {
        sprite = img;
    }

    public void startAnimation(ArrayList<Image> animation, boolean animCycle) {
        if (anim.isEmpty())
            return;

        anim = animation;
        animI = 0;
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

        Image result = anim.get(animI++);

        if (animI >= anim.size()) {
            animI = 0;
            play = cycle;
        }

        return result;
    }

    ImageView getImageView() {
        ImageView imageView = new ImageView(getSprite());
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }
}
