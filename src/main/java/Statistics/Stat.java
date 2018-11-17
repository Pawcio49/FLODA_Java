package Statistics;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.Main;
import spring.CustomerDAO;
import spring.FlodaConnection;
import spring.FlodaLog;
import spring.Info;

import javax.xml.ws.FaultAction;

import java.beans.ConstructorProperties;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Struct;
import java.util.Scanner;


public class Stat {
    String id;
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
    CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");
    int who;
    int number;

    @FXML
    private BarChart<?, ?> temperatureChart;
    @FXML
    private BarChart<?, ?> soilChart;
    @FXML
    private BarChart<?, ?> phChart;
    @FXML
    private BarChart<?, ?> humidityChart;
    @FXML
    private BarChart<?, ?> sunChart;
    @FXML
    AnchorPane anchorPane;
    @FXML
    ScrollPane ScrollPane;

    @FXML
    void initialize() {

        File file =
                new File("Plant_ID_Sondy.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine())
            id = sc.nextLine();
        who = Integer.parseInt(id);
        sc.close();
        file.delete();


         FlodaLog[] plant =  customerDAO.getFlodaLog(10, who);

        number=plant[0].getNumber();

        XYChart.Series set1 = new XYChart.Series<>( );
        for(int i=0;i<number;i++){
            set1.getData().add(new XYChart.Data(plant[i].getDate().substring(0,16), plant[i].getTemperature()));
        }

        temperatureChart.getXAxis().setTickLabelsVisible(false);
        temperatureChart.getXAxis().setOpacity(0);
        temperatureChart.getData().addAll(set1);



        XYChart.Series set2 = new XYChart.Series<>();
        for(int i=0;i<number;i++){
            set2.getData().add(new XYChart.Data(plant[i].getDate().substring(0,16), plant[i].getSoil()));
        }
        soilChart.getXAxis().setTickLabelsVisible(false);
        soilChart.getXAxis().setOpacity(0);
        soilChart.getData().addAll(set2);

        XYChart.Series set3 = new XYChart.Series<>();
        for(int i=0;i<number;i++){
            set3.getData().add(new XYChart.Data(plant[i].getDate().substring(0,16), plant[i].getPh()));
        }
        phChart.getXAxis().setTickLabelsVisible(false);
        phChart.getXAxis().setOpacity(0);
        phChart.getData().addAll(set3);

        XYChart.Series set4 = new XYChart.Series<>();
        for(int i=0;i<number;i++){
            set4.getData().add(new XYChart.Data(plant[i].getDate().substring(0,16), plant[i].getHumidity()));
        }
        humidityChart.getXAxis().setTickLabelsVisible(false);
        humidityChart.getXAxis().setOpacity(0);
        humidityChart.getData().addAll(set4);

        XYChart.Series set5 = new XYChart.Series<>();
        for(int i=0;i<number;i++){
            set5.getData().add(new XYChart.Data(plant[i].getDate().substring(0,16), plant[i].getSun()));
        }
        sunChart.getData().addAll(set5);
    }





}
