module ru.nsu.javagame {
    requires kotlin.stdlib;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens ru.nsu.javagame to javafx.fxml;
    exports ru.nsu.javagame;
}