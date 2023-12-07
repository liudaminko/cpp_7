module com.liuda.cpp_lab_7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.liuda.cpp_lab_7 to javafx.fxml;
    opens com.liuda.cpp_lab_7.controller to javafx.fxml;
    opens com.liuda.cpp_lab_7.model to javafx.fxml;
    exports com.liuda.cpp_lab_7;
    exports com.liuda.cpp_lab_7.controller;
    exports com.liuda.cpp_lab_7.model;
}