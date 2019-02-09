package Addflower;

import MainWindow.MainControler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.CustomerDAO;
import spring.FlodaConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Addflower {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
    CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");
    String id;
    ObservableList<String> choiceBoxList= FXCollections.observableArrayList();
    ObservableList<String> choiceBoxList2= FXCollections.observableArrayList();
    int valueOfChB1=0;
    int valueOfChB2=0;

    @FXML
    Button add;
    @FXML
    TextField name;
    @FXML
    Label information;
    @FXML
    ChoiceBox choiceBox;
    @FXML
    ChoiceBox choiceBox2;
    @FXML
    TextField id_sondy;
    @FXML
    TextField password;

    @FXML
    void initialize(){
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

        ArrayList<DateofChoiceBox> dateofCh1 = customerDAO.getData();
        ArrayList<DateofChoiceBox> dateofCh2 = customerDAO.getData2();

        for(int i=0;i<dateofCh1.get(0).getNumber();i++) {
            choiceBoxList.add(dateofCh1.get(i).getGatunek());
        }

        for(int i=0;i<dateofCh2.get(0).getNumber();i++) {
            choiceBoxList2.add(dateofCh2.get(i).getGatunek());
        }

        PhpAddFlower phpAddFlower=new PhpAddFlower();


        choiceBox.setItems(choiceBoxList);
        choiceBox.setValue("Domyślne ustawienia");

        choiceBox2.setItems(choiceBoxList2);
        choiceBox2.setValue("Ustawienia dodane przez użytkowników");

        add.setOnAction(actionEvent -> {
            for(int i=0;i<dateofCh1.get(0).getNumber();i++) {
                if(String.valueOf(choiceBox.getValue()).equals(dateofCh1.get(i).getGatunek()))
                    valueOfChB1=dateofCh1.get(i).getId();
            }

            for(int i=0;i<dateofCh2.get(0).getNumber();i++) {
                if(String.valueOf(choiceBox2.getValue()).equals(dateofCh2.get(i).getGatunek()))
                    valueOfChB2=dateofCh2.get(i).getId();
            }

            try {
                information.setText(phpAddFlower.addflower(id,id_sondy.getText(), name.getText(), String.valueOf(valueOfChB1), String.valueOf(valueOfChB2), password.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



    }
}
