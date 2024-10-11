package org.example.supermarketmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private AnchorPane admin_form;

    @FXML
    private Hyperlink admin_hyperlink;

    @FXML
    private Button admin_loginBtn;

    @FXML
    private PasswordField admin_password;

    @FXML
    private TextField admin_username;

    @FXML
    private TextField employee_ID;

    @FXML
    private AnchorPane employee_form;

    @FXML
    private Hyperlink employee_hyperlink;

    @FXML
    private Button employee_loginBtn;


    @FXML
    private PasswordField employee_password;

    @FXML
    void login() throws SQLException {
    /*String employee_username = employee_ID.getText();
    String employee_password = this.employee_password.getText();
        Connection connection = DatabaseConnector.connectDb();
        String sql ="SELECT * FROM employee WHERE username=? AND password =?";
        PreparedStatement preparedStatement =  connection.prepareStatement(sql);
        preparedStatement.setString(1,employee_username);
        preparedStatement.setString(2,employee_password);
        ResultSet resultSet= preparedStatement.executeQuery();
        int loginTest = 0;
        while (resultSet.next()){
            loginTest++;

        }
      if (loginTest == 0){
          System.out.println("rien de renseigne");
      }
      else {
          System.out.println("bien renseigne");
      }*/
    }


    @FXML
    private AnchorPane main_form;

    public void close() {
        System.exit(0);
    }

    //OUTILS SOUVENT UTILISES POUR LA CONNEXION
    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;

    public void switchForm(ActionEvent event) {
        if (event.getSource() == admin_hyperlink) {
            admin_form.setVisible(false);
            employee_form.setVisible(true);
        } else if (event.getSource() == employee_hyperlink) {
            admin_form.setVisible(true);
            employee_form.setVisible(false);
        }
    }

    public void employeeLogin() {
        String employeeData = "SELECT * FROM employee WHERE employeeID = ? AND password=?";
        connect = Database.connectDb();
        try {

            Alert alert;

            if (employee_ID.getText().isEmpty() || employee_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setHeaderText("Wrong Username/Password");
                alert.showAndWait();
            } else {
                //un objet de prepareStatement
                prepare = connect.prepareStatement(employeeData);
                prepare.setString(1, employee_ID.getText());
                prepare.setString(2, employee_password.getText());
                //un objet de resultSet
                result = prepare.executeQuery();
                if (result.next()) {

                    getData.employeeId = employee_ID.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Succeffuly Login");
                    alert.showAndWait();

                    employee_loginBtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("employeeDashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setHeaderText("Wrong Username/Password");
                    alert.showAndWait();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adminLogin() {
        String adminData = "SELECT * FROM  admin WHERE username=? AND password =?";
        connect = Database.connectDb();
        try {
            getData.username = admin_username.getText();
            Alert alert;
            if (admin_username.getText().isEmpty() ||admin_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error  message");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plait remplissez les deux champs!");
                alert.showAndWait();
            }
            prepare = connect.prepareStatement(adminData);
            prepare.setString(1, admin_username.getText());
            prepare.setString(2, admin_password.getText());
            result = prepare.executeQuery();

            if (result.next()) {
                admin_loginBtn.getScene().getWindow().hide();
                // ce code a ete utilise pour pourvoir recuperer cette fenetre au besoin
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information message");
                alert.setHeaderText(null);
                alert.setContentText("Connection etablie");
                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("adminDashboard.fxml"));
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Faux identifiant/Mot de passe");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ces methodes je les ai moi ajouté meme pour que l'employee puisse se connecter avec la touche clavier ENTER
    @FXML
    private void ENTER_employee(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String employeeData = "SELECT * FROM employee WHERE employeeID = ? AND password=?";
            connect = Database.connectDb();
            try {

                Alert alert;

                if (employee_ID.getText().isEmpty() || employee_password.getText().isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText("boris");
                    alert.setHeaderText("Wrong Username/Password");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(employeeData);
                    prepare.setString(1, employee_ID.getText());
                    prepare.setString(2, employee_password.getText());

                    result = prepare.executeQuery();
                    if (result.next()) {

                        getData.employeeId = employee_ID.getText();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information message");
                        alert.setHeaderText("boris");
                        alert.setHeaderText("Succeffuly Login");
                        alert.showAndWait();

                        employee_loginBtn.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("employeeDashboard.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();

                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error message");
                        alert.setHeaderText("boris");
                        alert.setHeaderText("Wrong Username/Password");
                        alert.showAndWait();

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void ENTER_admin(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String adminData = "SELECT * FROM  admin WHERE username=? AND password =?";
            connect = Database.connectDb();
            try {
                getData.username = admin_username.getText();
                Alert alert;
                if (admin_username.getText().isEmpty()||admin_password.getText().isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("S'il vous plait remplissez les deux champs!");
                    alert.showAndWait();
                }
                prepare = connect.prepareStatement(adminData);
                prepare.setString(1, admin_username.getText());
                prepare.setString(2, admin_password.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    admin_loginBtn.getScene().getWindow().hide();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Connexion etablie!");
                    alert.showAndWait();

                    Parent root = FXMLLoader.load(getClass().getResource("adminDashboard.fxml"));
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Faux identifiants/Mot de passe!");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
