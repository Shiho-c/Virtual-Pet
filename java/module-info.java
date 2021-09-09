module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    opens org.example to javafx.fxml;
    exports org.example;
}