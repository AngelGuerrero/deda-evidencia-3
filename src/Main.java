import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable{

    private static BTree btree;

    @FXML private Button randomBtn;
    @FXML private Button deleteBtn;
    @FXML private Button showPreorderBtn;
    @FXML private Button showInorderBtn;
    @FXML private Button showPostorderBtn;
    @FXML private Button exitBtn;

    private Alert alert;


    public static void main(String[] args) {
        btree = new BTree();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("Main.fxml"));

        Scene scene = new Scene(root.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Métodos de árbol binario");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Alertas para la aplicación
        alert = new Alert(Alert.AlertType.NONE);

        // Pone a la escucha los botones de la ventana
        randomBtn.setOnAction(e -> addRandom());

        deleteBtn.setOnAction(e -> delMinor());

        showPreorderBtn.setOnAction(e -> preorder());
        showInorderBtn.setOnAction(e -> inorder());
        showPostorderBtn.setOnAction(e -> postOrder());

        exitBtn.setOnAction(e -> closeApp());
    }


    private void addRandom() {
        Random random = new Random(System.currentTimeMillis());

        int numrand = random.nextInt(100);

        btree.add(numrand);

        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setContentText("El elemento: " + numrand + " se ha agregado al árbol.");
        alert.setTitle("Elemento agregado.");
        alert.show();
    }


    /**
     * Elimina el elemento menor del árbol binario.
     */
    private void delMinor() {
        btree.removeMinor();
    }


    /**
     * Muestra los datos en preorden
     */
    private void preorder() {
        System.out.println("\nElementos en PreOrder: ");
        btree.preOrder();
        System.out.println();
    }


    /**
     * Muestra los datos en inorden.
     */
    private void inorder() {
        System.out.println("\nElementos en InOrder: ");
        btree.inOrder();
        System.out.println();
    }


    /**
     * Muestra los datos en postorden.
     */
    private void postOrder() {
        System.out.println("\nElementos PostOrder: ");
        btree.postOrder();
        System.out.println();
    }


    /**
     * Cierra la aplicación.
     */
    private void closeApp() {
        Platform.exit();
    }
}
