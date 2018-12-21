package User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.CustomerDAO;
import spring.DataOfUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class user {
    String id;
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
    CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");

    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField nick;
    @FXML
    PasswordField oldPass;
    @FXML
    PasswordField newPass;
    @FXML
    PasswordField conPass;
    @FXML
    Button modify;
    @FXML
    Label infoPass;
    @FXML
    Button commit;
    @FXML
    Label info;

    @FXML
    public void initialize(){
        File file =
                new File("Plant_ID.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine())
            id = sc.nextLine();
        sc.close();
        file.delete();

        DataOfUser dataOfUser= customerDAO.getDataOfUser(id);
        name.setText(dataOfUser.getName());
        surname.setText(dataOfUser.getSurname());
        nick.setText(dataOfUser.getNick());
        PhpModifyUser phpModifyUser=new PhpModifyUser();

        modify.setOnAction(actionEvent ->{
            try {
                info.setText(phpModifyUser.modifyUser(nick.getText(),name.getText(),surname.getText(),id));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        commit.setOnAction(actionEvent ->{
            try {
                infoPass.setText(phpModifyUser.modifyPassword(id,oldPass.getText(),newPass.getText(),conPass.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
