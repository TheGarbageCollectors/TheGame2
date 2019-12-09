module trashmaster.presentation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens trashmaster.presentation to javafx.fxml;
    exports trashmaster.presentation;
}