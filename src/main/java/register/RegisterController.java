package register;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.CustomerDAO;

public class RegisterController {

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
    void initialize() {
        register.setOnAction(actionEvent -> {

            if(!nick.getText().equals("") && !name.getText().equals("") && !surname.getText().equals("") && !email.getText().equals("") && !password.getText().equals("") && password.getText().equals(r_password.getText())){
               customerDAO.insert(nick.getText(), name.getText(), surname.getText(), email.getText(), password.getText());

            }
        });


    }
}
