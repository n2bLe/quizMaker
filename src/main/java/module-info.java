module com.nobble.quizmaker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.nobble.quizmaker to javafx.fxml;
    exports com.nobble.quizmaker;
}