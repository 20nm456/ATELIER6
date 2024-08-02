module org.example.arobase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.example.arobase to javafx.fxml;
    exports org.example.arobase;
}