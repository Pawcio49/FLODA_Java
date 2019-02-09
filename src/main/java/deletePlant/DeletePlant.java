package deletePlant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.CustomerDAO;
import spring.FlodaConnection;
import spring.Info;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class DeletePlant {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
    CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");
    ArrayList<FlodaConnection> dateofCh1;
    String id;
    int who;
    ObservableList<String> choiceBoxList= FXCollections.observableArrayList();
    @FXML
    Button delbut;
    @FXML
    Label info;
    @FXML
    PasswordField password;
    @FXML
    ChoiceBox plants;

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
        who= Integer.parseInt(id);
        dateofCh1 = customerDAO.getFlodaConnection(who);
        int number=dateofCh1.get(0).getNumber();

        choiceBoxList.add("Wybierz roślinę");

        for(int i=0;i<number;i++) {
            choiceBoxList.add(dateofCh1.get(i).getName());
        }

        plants.setItems(choiceBoxList);
        plants.setValue("Wybierz roślinę");

        PhpdelPlant phpdelPlant = new PhpdelPlant();

        String correct="Poprawnie usunięto roślinę";


        delbut.setOnAction(actionEvent -> {

            try {
                String phpReturn=phpdelPlant.delflower( id, (String) plants.getValue(), password.getText());
                info.setText(phpReturn);
                if(phpReturn.equals("Poprawnie usunieto roślinę")){
                     choiceBoxList.remove(plants.getValue());
                    plants.setItems(choiceBoxList);
                    plants.setValue("Wybierz roślinę");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
