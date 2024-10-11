module org.example.supermarketmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.example.supermarketmanagementsystem to javafx.fxml;
    exports org.example.supermarketmanagementsystem;
}