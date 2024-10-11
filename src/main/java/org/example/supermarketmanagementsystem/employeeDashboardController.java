package org.example.supermarketmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class employeeDashboardController implements Initializable {

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField purchase_brand;

    @FXML
    private TableColumn<customerData,String > purchase_col_brand;//on fait ca pour quoi meme?

    @FXML
    private TableColumn<customerData,String> purchase_col_price;

    @FXML
    private TableColumn<customerData,String> purchase_col_productName;

    @FXML
    private TableColumn<customerData,String> purchase_col_quantity;

    @FXML
    private Label purchase_employeeId;

    @FXML
    private ComboBox<?> purchase_productName;

    @FXML
    private Spinner<Integer> purchase_quantity;
    @FXML
    private TableView<customerData> purchase_tableView;

    @FXML
    private Label purchase_total;
    private Connection conn;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

   public void purchaseAdd(){
       purchaseCustomerID();
       purchaseSpinnerValue();
       purchaseGetPrice();
       String insertProd="INSERT INTO customer"+"(customer_id,brand,productName,quantity,price,date)"
               +"VALUES(?,?,?,?,?,?)";
       conn = Database.connectDb();
       try {
           Alert alert;
           Date date = new Date();
           java.sql.Date sqlDate = new java.sql.Date(date.getTime());

           if (purchase_brand.getText().isEmpty()
                ||purchase_productName.getSelectionModel().getSelectedItem() == null
                ||qty==0){

               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText(null);
               alert.setContentText("monsieur  choisissez d'abord au moins un produit");
               alert.showAndWait();
           }else {
           prepare = conn.prepareStatement(insertProd);
           prepare.setString(1, String.valueOf(customerId));
           prepare.setString(2,purchase_brand.getText());
           prepare.setString(3, (String) purchase_productName.getSelectionModel().getSelectedItem());
           prepare.setString(4, String.valueOf(qty));
           totalPrice = (qty * price);
           prepare.setString(5, String.valueOf(price));
           prepare.setString(6, String.valueOf(sqlDate));
           prepare.executeUpdate(); //POURQUOI?
               purchaseShowlistdata();
               purchaseDisplayTotal();
           }
       }catch (Exception e){
           e.printStackTrace();
       }
   }
   private double price  = 0;
   private double totalPrice =0;
   public void purchaseGetPrice(){
       String gPrice = "SELECT price FROM product WHERE product_name='"
               +purchase_productName.getSelectionModel().getSelectedItem()+"'";

       conn = Database.connectDb();
       try {
           statement = conn.createStatement();
           result = statement.executeQuery(gPrice);
           if (result.next()){
               price = result.getDouble("price");
           }
       }catch (Exception e ){
           e.printStackTrace();
       }
   }
@FXML
   private void purchaseReset(){
       purchaseCustomerID();
       String resetData = "DELETE FROM customer WHERE customer_id ='"+customerId+"'";
    //String resetData = "DELETE FROM customer WHERE customer_id =1";
       conn = Database.connectDb();
       try {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Messagge de confirmation");
           alert.setHeaderText(null);
           alert.setContentText("Are you sure you want to reset? The product List including to reset");
           Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)){
                statement = conn.createStatement();
                statement.executeUpdate(resetData);

                purchase_brand.setText("");
                purchase_productName.getSelectionModel().clearSelection();
                purchaseSpinner();
                purchase_total.setText("FCFA");

            }else return;

       }catch (Exception e ){
           e.printStackTrace();
       }
   }
   private double totalP=0;
    public void purchaseDisplayTotal(){
       purchaseCustomerID();
       String sql = "SELECT SUM(price) FROM customer WHERE customer_id ='"+customerId+"'";

       conn = Database.connectDb();
       try {

           statement = conn.createStatement();
           result = statement.executeQuery(sql);
           if (result.next()){
               totalP= result.getDouble("SUM(price)");
           }
           purchase_total.setText((totalP)+" FCFA");
       }catch (Exception e){
           e.printStackTrace();
       }
    }
    public void purchaseSearchBrand(){
        String searchB= "SELECT * FROM product WHERE brand ='"+purchase_brand.getText()+"' AND status ='Disponible' ";
        conn =Database.connectDb();

        try {
            System.out.println("search");
            prepare = conn.prepareStatement(searchB);
            result = prepare.executeQuery();//dans quel cas on met une requete Sql en parametre ici?
            ObservableList listProduct =FXCollections.observableArrayList();
            if (!result.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(purchase_brand.getText()+" pas trouvé!");
                alert.showAndWait();

            }else {
                while (result.next()){
                    listProduct.add(result.getString("product_name"));
                }
            }
            purchase_productName.setItems(listProduct);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private SpinnerValueFactory spinner;
    public  void purchaseSpinner(){ // MIN , MAX ,DISPLAY NUM
spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,20,0);
purchase_quantity.setValueFactory(spinner);
    }
    private int qty;
    public void purchaseSpinnerValue(){
        qty= purchase_quantity.getValue();

    }
    public ObservableList<customerData> purchaseListData(){
        purchaseCustomerID();

        ObservableList<customerData>customerList = FXCollections.observableArrayList();
        String sql ="SELECT * FROM customer WHERE customer_id = '"+customerId+"'";
        conn = Database.connectDb();
        try {
            customerData custD;
            prepare = conn.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                custD = new customerData(result.getInt("customer_id")
                        , result.getString("brand")
                        , result.getString("productName")
                        , result.getInt("quantity")
                        , result.getDouble("price")
                        , result.getDate("date"));
                customerList.add(custD);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return customerList;
    }
    private  ObservableList<customerData> purchaseList;
    public void purchaseShowlistdata(){
        purchaseList = purchaseListData();
        purchase_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        purchase_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        /*@FXML
        private TableColumn<customerData,String> purchase_col_productName;*/
        //Pourqoui?
        purchase_tableView.setItems(purchaseList);
    }
    public  void purchaseReceipt(){

    }
    public void purchasePay(){
        String sql = "INSERT INTO customer_receipt (customer_id,total,date) "
                +"VALUES(?,?,?)";
        conn = Database.connectDb();
        try {
            Date date = new Date();
            java.sql.Date  sqlDate= new java.sql.Date(date.getTime());
            Alert alert;
            if (purchase_tableView.getItems().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Choisissez au prealabre un produit!");
                alert.showAndWait();

            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Est tu sur?");
                Optional<ButtonType>option= alert.showAndWait();
                if (option.get().equals(ButtonType.OK)){
                    prepare = conn.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, String.valueOf(totalP));
                    prepare.setString(3, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Reussi!");
                    alert.showAndWait();
                }else return;
            }
        }catch (Exception e){
        }
    }
     //what's going on here?
    private int customerId;
    public void purchaseCustomerID(){
        String cID = "SELECT customer_id FROM customer";
        conn = Database.connectDb();
        try {
            prepare = conn.prepareStatement(cID);
             result = prepare.executeQuery();
             while (result.next()){
                 customerId = result.getInt("customer_id");
             }
             int checkNum = 0;
             String checkCustomerId="SELECT customer_id FROM customer_receipt";
             statement = conn.createStatement();
             result = statement.executeQuery(checkCustomerId);//why?
             while (result.next()){
                 checkNum = result.getInt("customer_id");
             }
             if (customerId==0){
                 customerId +=1;
             } else if (checkNum == customerId) {
                 customerId+=1;
             }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void logout() throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation message");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr(e)de vouloir vous déconnecter?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                //A DEMANDER A MONSIEUR
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
    public void displayEmployeeId(){
        purchase_employeeId.setText(getData.employeeId);
    }
    public void close(){
        System.exit(0);
    }
    public void minimize(){
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayEmployeeId();
        purchaseShowlistdata();
        purchaseSpinner();
        purchaseSpinnerValue();
        purchaseDisplayTotal();
    }
}
