package register;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.CustomerDAO;

import java.io.IOException;

public class RegisterController{

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
    CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");

    @FXML
    Button register;
    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField email;
    @FXML
    TextField nick;
    @FXML
    TextField password;
    @FXML
    TextField r_password;
    @FXML
    Label com;
    @FXML
    Button login;

    @FXML
    void initialize() {
        com.setText("");

        register.setOnAction(actionEvent -> {

            if(!nick.getText().equals("") && !name.getText().equals("") && !surname.getText().equals("") && !email.getText().equals("") && !password.getText().equals("")){
                if(password.getText().equals(r_password.getText())){
                    PHPcontroller phpController = new PHPcontroller();
                    try {
                        com.setText(phpController.signup(nick.getText(), name.getText(), surname.getText(), email.getText(), password.getText()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    com.setText("Hasła muszą być jednakowe");
                }
            }
            else {
                com.setText("Wprowadź wszystkie dane");
            }
        });


        login.setOnAction(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("screen/menu.fxml"));
                Stage stage = (Stage) com.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();

            }
        });
    }
}
