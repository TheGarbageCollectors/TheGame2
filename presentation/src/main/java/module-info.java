module trashmaster.presentation {
    requires javafx.controls;
    requires javafx.fxml;

    opens trashmaster.presentation to javafx.fxml;
    exports trashmaster.presentation;
}