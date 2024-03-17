module org.example.finalclock {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.finalclock to javafx.fxml;
    exports org.example.finalclock;
}