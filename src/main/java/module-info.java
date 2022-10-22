module com.example.shingles {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires eu.hansolo.tilesfx;

    opens com.example.shingles to javafx.fxml;
    exports com.example.shingles;
}