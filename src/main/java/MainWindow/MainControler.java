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
    CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");
    Info info;
    private int line;
    ArrayList<FlodaConnection> Plant;
    int number;
    List<Button> button;
    int butSize;

    MainControler(Info info) {
        this.info = info;
    }

    @FXML
    Button newType;
    @FXML
    Button butUser;
    @FXML
    Label label;
    @FXML
    TabPane TAB;
    @FXML
    MenuItem close;
    @FXML
    MenuItem logOut;
    @FXML
    Main menu;
    @FXML
    Button addbut;
    @FXML
    TitledPane StatESP;
    @FXML
    MenuItem refresh;
    @FXML
    Button delbut;
    @FXML
    Button allTypes;

    @FXML
    void initialize() throws IOException {
        label.setText(info.getName() + " " + info.getSurname() + " (" + info.getNick() + ") " + (info.getsu() ? "SuperAdmin" : ""));

        butUser.setOnAction(actionEvent -> {
            try {
                PrintWriter save = new PrintWriter("Plant_ID.txt");
                save.println(info.getID());
                save.close();
                Node node = FXMLLoader.load(getClass().getClassLoader().getResource("screen/user.fxml"));
                Tab tb = new Tab("Ustawienia użytkownika", node);
                TAB.getTabs().add(tb);
                TAB.getSelectionModel().select(tb);
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


                Stage window = (Stage) addbut.getScene().getWindow();
                window.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        addbut.setOnAction(actionEvent -> {
            try {
                PrintWriter save = new PrintWriter("Plant_ID.txt");
                save.println(info.getID());
                save.close();
                Node node2 = FXMLLoader.load(getClass().getClassLoader().getResource("screen/addflower.fxml"));
                Tab tb2 = new Tab("Dodaj roślinę", node2);
                TAB.getTabs().add(tb2);
                TAB.getSelectionModel().select(tb2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        newType.setOnAction(actionEvent -> {
            try {
                PrintWriter save = new PrintWriter("Plant_ID.txt");
                save.println(info.getID());
                save.close();
                Node node3 = FXMLLoader.load(getClass().getClassLoader().getResource("screen/newType.fxml"));
                Tab tb3 = new Tab("Utwórz nowy gatunek", node3);
                TAB.getTabs().add(tb3);
                TAB.getSelectionModel().select(tb3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        allTypes.setOnAction(actionEvent -> {
            try {
                Node node3 = FXMLLoader.load(getClass().getClassLoader().getResource("screen/gatunki.fxml"));
                Tab tb3 = new Tab("Dostępne gatunki", node3);
                TAB.getTabs().add(tb3);
                TAB.getSelectionModel().select(tb3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        button = new ArrayList<Button>();

        Plant = customerDAO.getFlodaConnection(info.getID());


        number=Plant.get(0).getNumber();


        ScrollPane sp=new ScrollPane();
        StatESP.setContent(sp);
        AnchorPane stats=new AnchorPane();
        sp.setContent(stats);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);

        for (int i = 0;i<number; i++) {

            plantAction(stats, i);
            butSize = button.size();

        }



        refresh.setOnAction(actionEvent -> {
            Plant = customerDAO.getFlodaConnection(info.getID());
            button = new ArrayList<Button>();
            stats.getChildren().clear();
            number=Plant.get(0).getNumber();
            for (int i = 0;i<number; i++) {

                plantAction(stats, i);


            }

        });

        delbut.setOnAction(actionEvent -> {
            try {
                PrintWriter save = new PrintWriter("Plant_ID.txt");
                save.println(info.getID());
                save.close();
                Node node2 = FXMLLoader.load(getClass().getClassLoader().getResource("screen/deletePlant.fxml"));
                Tab tb2 = new Tab("Usuń roślinę", node2);
                TAB.getTabs().add(tb2);
                TAB.getSelectionModel().select(tb2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void plantAction(AnchorPane stats, int i) {
        button.add(new Button());
        button.get(i).setLayoutX(68.0);
        button.get(i).setLayoutY(14.0);
        stats.setLeftAnchor(button.get(i), 5.0);
        stats.setRightAnchor(button.get(i), 5.0);
        stats.setTopAnchor(button.get(i), i * 35 + 5.0);

        button.get(i).setText(Plant.get(i).getName());
        if(Plant.get(i).getNormpod()==1 && Plant.get(i).getNormwil()==1 && Plant.get(i).getNormsun()==1 && Plant.get(i).getNormtem()==1){
            button.get(i).setStyle("-fx-background-color:#00A993; -fx-text-fill: white;");
        }

        else{
            button.get(i).setStyle("-fx-background-color: #f8542B; -fx-text-fill: white;");
        }


        int w=i;

        button.get(i).setOnAction(actionEvent -> {

            Node node4 = null;
            try {

                PrintWriter save = new PrintWriter("Plant_ID_Sondy.txt");
                save.println(Plant.get(w).getId_sondy());
                save.close();
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("screen/statistics.fxml"));
                Tab tab = new Tab(Plant.get(w).getName(),root);
                TAB.getTabs().add(tab);
                TAB.getSelectionModel().select(tab);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        stats.getChildren().add(button.get(i));
    }
}
