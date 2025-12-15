module ru.nsu.javagame {
    requires kotlin.stdlib;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;

    opens ru.nsu.javagame to javafx.fxml;
    exports ru.nsu.javagame;
}