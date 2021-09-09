package org.example;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.ArrayList;
public class Pet {
    private File spritesFolder;
    private Group pet;
    private ArrayList<String> spritesName;
    private AnchorPane Window;
    private Image heartImage = new Image(getClass().getResource("heart.png").toString(), 50, 50, true, true ,true);
    private Group hearts = new Group();

    public Pet(String spritesFolder, AnchorPane Window) {
        this.spritesFolder = new File(getClass().getResource(spritesFolder).getPath());
        this.pet = new Group();
        this.Window = Window;
        spritesName = new ArrayList<>();
    }

    public void setup() {
        double counter = pet.getTranslateX();
        for(int i = 0; i < 2; i ++) {
            ImageView heart = new ImageView(heartImage);
            heart.setTranslateY(pet.getTranslateY() - 10);
            heart.setTranslateX(counter);
            hearts.getChildren().add(heart);
            counter += 50;
        }
        Window.getChildren().add(pet);
        SetDrag();
        CreateAnimation();
    }

    private void CreateAnimation() {
        Timeline t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);
        File[] files = this.spritesFolder.listFiles();
        int duration = 0;
        for (int i = 0; i < files.length - 1; i++) {
            Image image = new Image("file:///" + this.spritesFolder.getPath() + "\\" + files[i].getName(), 200, 172, true, true);
            ImageView imageView = new ImageView(image);

            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(duration),
                    (ActionEvent event) -> {
                        pet.getChildren().setAll(imageView);
                        pet.getChildren().add(hearts);
                    }));
            duration+= 100;
        }
        t.play();
    }

    private void SetDrag() {
        pet.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                pet.setTranslateX(event.getSceneX() - 100);
                pet.setTranslateY(event.getSceneY() - 100);
            }
        });
    }

}
