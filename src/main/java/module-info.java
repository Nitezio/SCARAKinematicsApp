module org.example.scarakinematicsapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.scarakinematicsapp to javafx.fxml;
    exports org.example.scarakinematicsapp;
}