package sample;

import MainWindow.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.CustomerDAO;
import spring.Info;


import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MenuController extends Thread {
    @FXML
    private Label label1;
    @FXML
    private Button buttlog;
    @FXML
    private Label errorlabel;
    @FXML
    private TextField txtnick;
    @FXML
    private PasswordField txtpasswd;
    private MainWindow mainWindow;
    @FXML
    Button buttreg;
    @FXML
    Button pass;

    @FXML
    void initialize() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        buttlog.setOnAction(actionEvent -> {
            try {
                errorlabel.setText("");
                if (!txtnick.getText().isEmpty() && !txtpasswd.getText().isEmpty()) {
                    CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");
                    Info dd = new Info(customerDAO.findByCustomerId(txtnick.getText(), txtpasswd.getText()));

                    if (dd.getEmail().equals("erro")) {

                        errorlabel.setText("Nieprawidłowy nick lub hasło");
                    } else if (dd.getEmail().equals("err")) {

                        errorlabel.setText("Brak połączenia z internetem");

                    } else if (dd.getblocked()) {
                        errorlabel.setText("Twoje konto jest zablokowane");


                    } else {
                        try {
                            mainWindow = new MainWindow(dd);
                            Stage stage = (Stage) txtpasswd.getScene().getWindow();

                            mainWindow.start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                } else {
                    errorlabel.setText("Uzupełnij wszystkie pola");
                }
            } catch (Exception e) {
            }
        });

        buttreg.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("screen/register.fxml"));
                Stage stage = (Stage) txtpasswd.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();

            }
        });

        pass.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("screen/recover.fxml"));
                Stage stage = (Stage) txtpasswd.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();

            }
        });
    }
}