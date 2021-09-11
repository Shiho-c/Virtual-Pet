package org.example;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class HorizontalGroup extends Group {
    private final Helper Helper;
    private final int width;
    private final int height;
    private final String imageUrl;
    private int totalWidth;
    private final AnchorPane Window;
    public HorizontalGroup(AnchorPane Window, String imageUrl, int width, int height) {
        this.Helper = new Helper();
        this.width = width;
        this.height = height;
        this.imageUrl = imageUrl;
        this.Window = Window;
        this.totalWidth = 0;

    }

    public void setup() {
        Image foodImage = new Image(imageUrl, this.width, this.height, true, true ,true);
        for(int i = 0; i < 5; i ++) {
            ImageView heart = new ImageView(foodImage);
            heart.setTranslateX(totalWidth);
            this.getChildren().add(heart);
            this.totalWidth += this.width;
        }
        setHideButton();
    }
    public Group GetGroup() {
        return this;
    }

    public void moveX(int coordinate) {
        this.setTranslateX(coordinate);
    }

    public void moveY(int coordinate) {
        this.setTranslateY(coordinate);
    }

    public int getWidth() {
        return this.totalWidth;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHideButton() {
        Group group = this;
        AnchorPane Window = this.Window;
        int totalWidth = this.totalWidth;
        this.setOnMouseClicked(event -> {
                Button hide = new Button("Hide");
                Helper.hideNodeOnClicked(hide, group);
                hide.setTranslateX(totalWidth);
                hide.setTranslateY(group.getTranslateY());
                Window.getChildren().add(hide);
        });
    }
}
