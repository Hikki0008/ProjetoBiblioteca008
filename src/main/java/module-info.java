module org.biblioteca.biblioteca009 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens org.biblioteca.biblioteca009 to javafx.fxml;
    opens org.biblioteca.biblioteca009.Controllers to javafx.fxml;

    exports org.biblioteca.biblioteca009;
}
