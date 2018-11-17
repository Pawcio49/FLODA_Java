package MainWindow;

import Statistics.Stat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.Main;
import spring.CustomerDAO;
import spring.FlodaConnection;
import spring.Info;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class MainControler {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
    Info info;
    private int line;

    MainControler(Info info) {
        this.info = info;
    }

    @FXML
    Button logowanie;
    @FXML
    Button web;
    @FXML
    Button button;
    @FXML
    Label label;
    @FXML
    TabPane TAB;
    @FXML
    TitledPane adminfx;
    @FXML
    MenuItem close;
    @FXML
    MenuItem logOut;
    @FXML
    Main menu;
    @FXML
    AnchorPane stats;


    @FXML
    void initialize() throws IOException {
        label.setText(info.getName() + " " + info.getSurname() + " (" + info.getNick() + ") " + (info.getsu() == true ? "SuperAdmin" : ""));
        if (info.getsu() == false) {
            adminfx.visibleProperty().set(false);
            adminfx.setDisable(true);
        }

        button.setOnAction(actionEvent -> {
            try {
                Node node = FXMLLoader.load(getClass().getClassLoader().getResource("screen/dataView.fxml"));
                Tab tb = new Tab("Baza danych", node);
                TAB.getTabs().add(tb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        web.setOnAction(actionEvent -> {
            try {
                Node node2 = FXMLLoader.load(getClass().getClassLoader().getResource("screen/Web.fxml"));
                Tab tb2 = new Tab("WEB", node2);
                TAB.getTabs().add(tb2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        close.setOnAction(actionEvent -> {
            System.exit(0);
        });


        logOut.setOnAction(actionEvent -> {


            try {
                menu = new Main();
                Parent node3 = FXMLLoader.load(getClass().getClassLoader().getResource("screen/menu.fxml"));
                Scene scene = new Scene(node3);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                menu.start(primaryStage);


                Stage window = (Stage) web.getScene().getWindow();
                window.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");
        List<Button> button = new ArrayList<Button>();

        ArrayList<FlodaConnection> Plant = customerDAO.getFlodaConnection(100, info.getID());
        //FlodaConnection[] Plant = customerDAO.getFlodaConnection(100, info.getID());

        int number=Plant.get(0).getNumber();
        //int number=Plant[0].getNumber();


        for (int i = 0;i<number; i++) {

                button.add(new Button());
                button.get(i).setLayoutX(68.0);
                button.get(i).setLayoutY(14.0);
                stats.setLeftAnchor(button.get(i), 5.0);
                stats.setRightAnchor(button.get(i), 5.0);
                stats.setTopAnchor(button.get(i), i * 35 + 5.0);

                 button.get(i).setText(Plant.get(i).getName());
                 //button.get(i).setText(Plant[i].getName());

                int w=i;

                button.get(i).setOnAction(actionEvent -> {

                    Node node4 = null;
                    try {

                        PrintWriter save = new PrintWriter("Plant_ID_Sondy.txt");
                        save.println(Plant.get(w).getId_sondy());
                        //save.println(Plant[w].getId_sondy());
                        save.close();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("screen/statistics.fxml"));
                        Tab tab = new Tab(Plant.get(w).getName(),root);
                        //Tab tab = new Tab(Plant[w].getName(),root);
                        TAB.getTabs().add(tab);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

                stats.getChildren().add(button.get(i));


        }


    }
}
