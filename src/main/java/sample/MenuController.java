package sample;

import MainWindow.MainWindow;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.CustomerDAO;
import spring.Info;


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
    void initialize() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        buttlog.setOnAction(actionEvent -> {
            try{
            errorlabel.setText("");
            if (!txtnick.getText().isEmpty() && !txtpasswd.getText().isEmpty()) {
                CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");
                Info dd = new Info(customerDAO.findByCustomerId(txtnick.getText(), txtpasswd.getText()));
                if(dd.getblocked()){
                    errorlabel.setText("Your account is blocked");
                }else {
                    switch (dd.getEmail()) {
                        case "err":
                            errorlabel.setText("Blad polaczenia z internetem!");
                            break;
                        case "erro":
                            errorlabel.setText("Blad logowania!");
                            break;

                        default:
                            mainWindow = new MainWindow(dd);
                            Stage stage = (Stage) txtpasswd.getScene().getWindow();
                            try {
                                mainWindow.start(stage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            } else {
                errorlabel.setText("Nie uzupelniles pol!");
            }
        }catch (Exception e){
            }
        });
    }
}