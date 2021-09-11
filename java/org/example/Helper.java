package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;
public class Helper {
    public HashMap<String, Double> calculateImageSize(Group imageGroup) {
        System.out.println("Group size: " + imageGroup.getChildren().size());
        HashMap<String, Double> totalSize = new HashMap<>();

        double width = 0;
        double height;
        for(int i = 0; i < imageGroup.getChildren().size(); i ++) {
            ImageView currentImage = (ImageView) imageGroup.getChildren().get(i);
            width += currentImage.getFitWidth();
        }
        ImageView firstImage = (ImageView) imageGroup.getChildren().get(0);
        height = firstImage.getFitHeight();
        totalSize.put("width", width);
        totalSize.put("height", height);
        System.out.println("total" + totalSize);
        return totalSize;
    }

    public void hideNodeOnClicked(Node node, Node target) {
        node.setOnMouseClicked(event -> {
                target.setVisible(false);
                node.setVisible(false);
        });
    }

    public void playAnimation(String path, Group pet, Timeline timeLine) {
        pet.getChildren().removeAll();
        File[] idleAnimation = new File(path).listFiles();
        int duration = 0;
        for (int i = 0; i < idleAnimation.length - 1; i++) {
            Image image = new Image("file:///" + path + "\\" + idleAnimation[i].getName(), 200, 172, true, true);
            System.out.println(image.getUrl());
            ImageView imageView = new ImageView(image);

            timeLine.getKeyFrames().add(new KeyFrame(
                    Duration.millis(duration),
                    (ActionEvent event) -> {
                        pet.getChildren().setAll(imageView);
                        //pet.getChildren().add(hearts);
                    }));
            duration+= 100;
        }
        timeLine.play();

    }
}
