module com.jmc.widget {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.fasterxml.jackson.databind;


    opens com.jmc.widget to javafx.fxml;
    exports com.jmc.widget;
}