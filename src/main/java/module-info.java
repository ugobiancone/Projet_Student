module com.test.test1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.security.jgss;
    requires mysql.connector.java;
    requires java.desktop;

    opens com.test.test1 to javafx.fxml;
    exports com.test.test1;
}