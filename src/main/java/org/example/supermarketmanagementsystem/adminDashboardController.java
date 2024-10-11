package org.example.supermarketmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminDashboardController implements Initializable {
    @FXML
    private TextField addProducts_brandName;

    @FXML
    private Button addProducts_btn;

    @FXML
    private TableColumn<productData, String> addProducts_col_brandName;

    @FXML
    private TableColumn<productData, String> addProducts_col_price;

    @FXML
    private TableColumn<productData, String> addProducts_col_productID;

    @FXML
    private TableColumn<productData, String> addProducts_col_productName;

    @FXML
    private TableColumn<productData, String> addProducts_col_status;

    @FXML
    private AnchorPane addProducts_form;

    @FXML
    private TextField addProducts_price;

    @FXML
    private TextField addProducts_productID;

    @FXML
    private TextField addProducts_productName;

    @FXML
    private TextField addProducts_search;

    @FXML
    private ComboBox<?> addProducts_status;
    // @FXML
    //private TableView<employeeData> employee_tableView;
    @FXML
    private TableView<productData> addProducts_tableView;

    @FXML
    private Label dashboard_activeEmployees;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AreaChart<?, ?> dashboard_chart;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_incomeToday;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Button employee_btn;

    @FXML
    private TableView<employeeData> employee_tableView;

    @FXML
    private TableColumn<employeeData, String> employees_col_date;

    @FXML
    private TableColumn<employeeData, String> employees_col_employeeID;

    @FXML
    private TableColumn<employeeData, String> employees_col_firstName;

    @FXML
    private TableColumn<employeeData, String> employees_col_gender;

    @FXML
    private TableColumn<employeeData, String> employees_col_lastName;

    @FXML
    private TableColumn<employeeData, String> employees_col_password;

    @FXML
    private TextField employees_employeeID;

    @FXML
    private TextField employees_firstName;

    @FXML
    private AnchorPane employees_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private ComboBox<?> employees_gender;

    @FXML
    private TextField employees_lastName;

    @FXML
    private TextField employees_password;

    @FXML
    private Button logout;

    @FXML
    private Label username;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;


    //METHODES POUR LES PRODUITS
    public void addProductsAdd() {
        String insertProduct = "INSERT INTO product "
                + "(product_id,brand,product_name,status,price)"
                + "VALUES(?,?,?,?,?)";
        connect = Database.connectDb();
        try {
            Alert alert;
            if (addProducts_productID.getText().isEmpty()
                    || addProducts_brandName.getText().isEmpty()
                    || addProducts_productName.getText().isEmpty()
                    || addProducts_status.getSelectionModel().getSelectedItem() == null
                    || addProducts_price.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plait remplissez les champs vides");
                alert.showAndWait();

            } else {
                String check = "SELECT * FROM product WHERE product_id = '" + addProducts_productID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(check);
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product ID: " + addProducts_productID.getText() + " Existe deja mon Ami");
                    alert.showAndWait();
                    return;
                }
                prepare = connect.prepareStatement(insertProduct);
                prepare.setString(1, addProducts_productID.getText());
                prepare.setString(2, addProducts_brandName.getText());
                prepare.setString(3, addProducts_productName.getText());
                prepare.setString(4, (String) addProducts_status.getSelectionModel().getSelectedItem());
                prepare.setString(5, addProducts_price.getText());

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("le produit a ete bien ajouté!");
                alert.showAndWait();

                addProductsShowData();
                addProductsClear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsUpdate() {
        String updateProduct = "UPDATE product SET brand = '"
                + addProducts_brandName.getText() + "', product_name = '"
                + addProducts_productName.getText() + "', status = '"
                + addProducts_status.getSelectionModel().getSelectedItem() + "', price = '"
                + addProducts_price.getText() + "' WHERE product_id = '"
                + addProducts_productID.getText() + "'";


        connect = Database.connectDb();
        try {
            Alert alert;
            if (addProducts_productID.getText().isEmpty()
                    || addProducts_brandName.getText().isEmpty()
                    || addProducts_productName.getText().isEmpty()
                    || addProducts_status.getSelectionModel().getSelectedItem() == null
                    || addProducts_price.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plait remplissez les champs vides");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Petit veux tu reellement modifier Product ID: " + addProducts_productID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateProduct);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Produit modifie avec succes!");
                    alert.showAndWait();

                    addProductsShowData();
                    addProductsClear();
                } else return;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsDelete() {
        String deleteProduct = "DELETE FROM product WHERE product_id = '"
                + addProducts_productID.getText() + "'";
        connect = Database.connectDb();

        try {
            Alert alert;
            if (addProducts_productID.getText().isEmpty()
                    || addProducts_brandName.getText().isEmpty()
                    || addProducts_productName.getText().isEmpty()
                    || addProducts_price.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plait remplissez les champs vides");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Petit, voulez vous vraiment supprimer Product ID: " + addProducts_productID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(deleteProduct);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Produit supprimé avec succes!");
                    alert.showAndWait();

                    addProductsShowData();
                    addProductsClear();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //METHODE AGISSANT SUR LES EMPLOYES

    public void employeesSave() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String insertEmployee = "INSERT INTO employee"
                + "(employeeID,password,firstName,lastName,gender,date)"
                + "VALUES(?,?,?,?,?,?)";
        connect = Database.connectDb();

        try {
            Alert alert;
            if (employees_employeeID.getText().isEmpty()
                    || employees_password.getText().isEmpty()
                    || employees_firstName.getText().isEmpty()
                    || employees_lastName.getText().isEmpty()
                    || employees_gender.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Remplissez tout les champs vides svp");
                alert.showAndWait();
            } else {

                String checkExist = "SELECT employeeID FROM employee WHERE employeeID = '"
                        + employees_employeeID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkExist);
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID:" + employees_employeeID.getText() + "Existe deja");
                    alert.showAndWait();

                }

                prepare = connect.prepareStatement(insertEmployee);
                prepare.setString(1, employees_employeeID.getText());
                prepare.setString(2, employees_password.getText());
                prepare.setString(3, employees_firstName.getText());
                prepare.setString(4, employees_lastName.getText());
                prepare.setString(5, (String) employees_gender.getSelectionModel().getSelectedItem());
                prepare.setString(6, String.valueOf(sqlDate));

                prepare.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Enregistré avec succes!");
                alert.showAndWait();

                employeeShowListData();
                employeesReset();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void employeesUpdate() {

        String updateEmployee = "UPDATE employee SET password = '"
                + employees_password.getText() + "', firstName= '"
                + employees_firstName.getText() + "', lastName= '"
                + employees_lastName.getText() + "', gender = '"
                + employees_gender.getSelectionModel().getSelectedItem()
                + "'WHERE employeeID = '" + employees_employeeID.getText() + "'";

        connect = Database.connectDb();

        try {
            Alert alert;
            if (employees_employeeID.getText().isEmpty()
                    || employees_password.getText().isEmpty()
                    || employees_firstName.getText().isEmpty()
                    || employees_lastName.getText().isEmpty()
                    || employees_gender.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Remplissez tout les champs vides svp");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Petit veux tu reellement modifier Employee ID: " + employees_employeeID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateEmployee);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Modifié avec succes!");
                    alert.showAndWait();
                    employeeShowListData();
                    employeesReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // @FXML dans le cas ou cest private
    @FXML
    private void employeeDelete() {
        String deleteEmployee = "DELETE FROM employee WHERE employeeID = '"
                + employees_employeeID.getText() + "'";

        connect = Database.connectDb();
        try {
            Alert alert;
            if (employees_employeeID.getText().isEmpty()
                    || employees_password.getText().isEmpty()
                    || employees_firstName.getText().isEmpty()
                    || employees_lastName.getText().isEmpty()
                    || employees_gender.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Remplissez tout les champs vides svp");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Petit veux tu reellement supprimer Employee ID: " + employees_employeeID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteEmployee);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Supprimé avec succes!");
                    alert.showAndWait();
                    employeeShowListData();
                    employeesReset();
                } else return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<employeeData> employeeListData() {
        ObservableList<employeeData> emData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee";
        connect = Database.connectDb();
        try {
            employeeData employeeD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                employeeD = new employeeData(result.getString("employeeID")
                        , result.getString("password")
                        , result.getString("firstName")
                        , result.getString("lastName")
                        , result.getString("gender")
                        , result.getDate("date"));
                emData.add(employeeD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return emData;
    }

    private ObservableList<employeeData> employeeList;

    public void employeeShowListData() {
        employeeList = employeeListData();
        employees_col_employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employees_col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        employees_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        employees_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        employees_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        employees_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        employee_tableView.setItems(employeeList);
    }

    public void employeesSelect() {
        employeeData employeeD = employee_tableView.getSelectionModel().getSelectedItem();//utilise pour recuperer les donnes selectionnes dans la tableView
        //rien comprend
        int num = employee_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        employees_employeeID.setText(employeeD.getEmployeeId());
        employees_password.setText(employeeD.getPassword());
        employees_firstName.setText(employeeD.getFirstName());
        employees_lastName.setText(employeeD.getLastName());

    }


    public void employeesReset() {
        employees_employeeID.setText("");
        employees_password.setText("");
        employees_firstName.setText("");
        employees_lastName.setText("");
        employees_gender.getSelectionModel().clearSelection();

    }


    private String[] genderList = {"Masculin", "Feminin", "LGBT+"};

    public void employeeGender() {
        List<String> genderL = new ArrayList<>();
        for (String data : genderList) {
            genderL.add(data);
        }
        ObservableList listG = FXCollections.observableArrayList(genderL);
        employees_gender.setItems(listG);

    }

    //POURQUOI ICI NE MARCHE PAS?
    public void addProductsSearch() {
        FilteredList<productData> filter = new FilteredList<>(addProductsList, e -> true);
        addProducts_search.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateProductData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateProductData.getProductID().toLowerCase().contains(searchKey)) return true;
                else if (predicateProductData.getBrand().toLowerCase().contains(searchKey)) return true;
                else if (predicateProductData.getStatus().toLowerCase().contains(searchKey)) return true;
                else if (predicateProductData.getProductName().toLowerCase().contains(searchKey)) return true;
                else if (predicateProductData.getPrice().toString().contains(searchKey)) return true;

                else return false;
            });
        });
        SortedList<productData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addProducts_tableView.comparatorProperty());
        addProducts_tableView.setItems(sortList);
    }

    public void addProductsClear() {
        addProducts_productID.setText("");
        addProducts_brandName.setText("");
        addProducts_productName.setText("");
        addProducts_status.getSelectionModel().clearSelection();
        addProducts_price.setText("");
    }

    private final String[] statusList = {"Disponible", "Indisponible"};

    //ici aussi
    @FXML
    private void addProductsStatusList() {
        List<String> listS = new ArrayList<>();
        for (String data : statusList) {
            listS.add(data);

        }
        ObservableList statusData = FXCollections.observableArrayList(listS);
        addProducts_status.setItems(statusData);
    }

    //RIEN COMPRIS ICI
    //Il recupere les produits existants dans la table
    public ObservableList<productData> addProductsListData() {
        ObservableList<productData> prodList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product";
        connect = Database.connectDb();

        try {
            productData prod;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                prod = new productData(
                        result.getString("product_id"),
                        result.getString("brand"),
                        result.getString("product_name"),
                        result.getString("status"),
                        result.getDouble("price"));

                prodList.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return prodList;
    }

    private ObservableList<productData> addProductsList;

    //Cette methode permet de show les products dans ma table scene Builder;
    public void addProductsShowData() {
        addProductsList = addProductsListData();
        addProducts_col_productID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        addProducts_col_brandName.setCellValueFactory(new PropertyValueFactory<>("brand"));
        addProducts_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addProducts_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        addProducts_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        //pourquoi il a fait ca?
        /*@FXML
        private TableColumn<productData, String> addProducts_col_brandName;*/
        addProducts_tableView.setItems(addProductsList);
    }

    public void addProductsSelect() {
        productData prod = addProducts_tableView.getSelectionModel().getSelectedItem();
        int num = addProducts_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }

        addProducts_productID.setText(prod.getProductID());
        addProducts_brandName.setText(prod.getBrand());
        addProducts_productName.setText(prod.getProductName());
        addProducts_price.setText(String.valueOf(prod.getPrice()));

    }

    public void dashboardDisplayActiveEmployees() {
        String sql = "SELECT COUNT(employee_id) FROM employee";
        connect = Database.connectDb();
        int countE = 0;
        try {
            statement = connect.createStatement();//Difference utilisation de createStatement et prepareStatement
            result = statement.executeQuery(sql);
            if (result.next()) {
                countE = result.getInt("COUNT(employee_id)");
            }
            dashboard_activeEmployees.setText(String.valueOf(countE));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardDisplayIncomeToday() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "SELECT SUM(total) FROM customer_receipt WHERE date = '" + sqlDate + "'";


        double sumT = 0;
        connect = Database.connectDb();
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);
            if (result.next()) {
                sumT = result.getDouble("SUM(total)");
                dashboard_incomeToday.setText((sumT) + " F");
                //dashboard_incomeToday.setText( "20000 F");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void dashboardTotalIncome() {

        String sql = "SELECT SUM(total) FROM customer_receipt";
        double sumTI = 0;
        connect = Database.connectDb();
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);
            if (result.next()) {
                sumTI = result.getDouble("SUM(total)");
                //dashboard_incomeToday.setText(String.valueOf(sumTI) + " F");

            }
            dashboard_totalIncome.setText(String.valueOf(sumTI) + " F");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardChart() {
        dashboard_chart.getData().clear();

        String sql = "SELECT date,SUM(total) FROM customer_receipt GROUP BY  date ORDER BY TIMESTAMP(date) ASC LIMIT 9 ";
        //cette requete fait quoi exactement
        connect = Database.connectDb();
        try {
            XYChart.Series chart = new XYChart.Series();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            dashboard_chart.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void close() {
        System.exit(0);
    }

    public void displayUsername() {
        username.setText(getData.username);
    }


    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            addProducts_form.setVisible(false);
            employees_form.setVisible(false);
            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to top right,#896b34,#b8a536)");
            addProducts_btn.setStyle("-fx-background-color:transparent");
            employee_btn.setStyle("-fx-background-color:transparent");
            dashboardDisplayActiveEmployees();
            dashboardDisplayIncomeToday();
            dashboardTotalIncome();
            dashboardChart();
        } else if (event.getSource() == addProducts_btn) {
            dashboard_form.setVisible(false);
            addProducts_form.setVisible(true);
            employees_form.setVisible(false);
            addProducts_btn.setStyle("-fx-background-color:linear-gradient(to top right,#896b34,#b8a536)");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            employee_btn.setStyle("-fx-background-color:transparent");
            addProductsShowData();
            addProductsStatusList();
            addProductsSearch();
        } else if (event.getSource() == employee_btn) {
            dashboard_form.setVisible(false);
            addProducts_form.setVisible(false);
            employees_form.setVisible(true);
            employee_btn.setStyle("-fx-background-color:linear-gradient(to top right,#896b34,#b8a536)");
            addProducts_btn.setStyle("-fx-background-color:transparent");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            employeeShowListData();
        }
    }

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation message");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr(e)de vouloir vous déconnecter?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else return;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductsShowData();
        displayUsername();
        dashboardDisplayActiveEmployees();
        employeeGender();
        addProductsStatusList();
        employeeShowListData();

        dashboardDisplayIncomeToday();
        dashboardTotalIncome();//apres rearranger les methodes
        dashboardChart();
    }
}
