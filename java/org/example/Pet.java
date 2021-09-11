package org.example;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.io.File;
import java.util.Objects;

public class Pet {
    private final String spritesFolder;
    private Group pet;
    private final AnchorPane Window;
    private final Helper Helper;
    private final Timeline timeLine;
    private HorizontalGroup hearts;
    private HorizontalGroup meat;

    public Pet(String spritesFolder, AnchorPane Window) {
        this.spritesFolder = Objects.requireNonNull(getClass().getResource(spritesFolder)).getPath();
        this.pet = new Group();
        this.Window = Window;
        this.Helper = new Helper();
        this.timeLine = new Timeline();
    }

    public void setup() {
        CreateHearts();
        CreateFoods();
        Window.getChildren().add(pet);
        SetDrag();
        CreateAnimation();


    }

    private void CreateAnimation() {
        String idleFolder = this.spritesFolder + "\\Idle";
        String danceFolder = this.spritesFolder + "\\Dance";

        pet.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.SECONDARY) {
                Button hide = new Button("Dance");
                hide.setTranslateX(pet.getTranslateX());
                hide.setTranslateY(pet.getTranslateY());
                Window.getChildren().add(hide);
                hide.setOnMouseClicked(event1 -> {
                    hide.setVisible(false);
                    Helper.playAnimation(danceFolder, pet, timeLine);
                });}});

        timeLine.setCycleCount(Timeline.INDEFINITE);
        File[] idleAnimation = new File(idleFolder).listFiles();
        int duration = 0;
        for (int i = 0; i < Objects.requireNonNull(idleAnimation).length - 1; i++) {
            Image image = new Image("file:///" + idleFolder + "\\" + idleAnimation[i].getName(), 200, 172, true, true);
            ImageView imageView = new ImageView(image);

            timeLine.getKeyFrames().add(new KeyFrame(
                    Duration.millis(duration),
                    (ActionEvent event) -> {
                        pet.getChildren().setAll(imageView);
                    }));
            duration+= 100;
        }
        timeLine.play();
    }

    private void CreateHearts() {
        this.hearts = new HorizontalGroup(Window, Objects.requireNonNull(getClass().getResource("heart.png")).toString(), 50, 50);
        this.hearts.setup();
        Window.getChildren().add(this.hearts.GetGroup());

    }

    private void CreateFoods() {
        this.meat = new HorizontalGroup(Window, Objects.requireNonNull(getClass().getResource("meat.png")).toString(), 50, 50);
        this.meat.setup();
        this.meat.moveY(this.hearts.getHeight());
        Window.getChildren().add(this.meat.GetGroup());
    }

    private void SetDrag() {
        pet.setOnMouseDragged(event -> {
            pet.setTranslateX(event.getSceneX() - 100);
            pet.setTranslateY(event.getSceneY() - 100);
        });
    }

}
