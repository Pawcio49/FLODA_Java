package Statistics;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.Main;
import spring.CustomerDAO;
import spring.FlodaConnection;
import spring.FlodaLog;
import spring.Info;


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
    int number2;

    @FXML
    private BarChart<?, ?> temperatureChart;
    @FXML
    private BarChart<?, ?> soilChart;
    @FXML
    private BarChart<?, ?> humidityChart;
    @FXML
    private BarChart<?, ?> sunChart;
    @FXML
    AnchorPane anchorPane;
    @FXML
    ScrollPane ScrollPane;
    @FXML
    CheckBox checkBox;

    public XYChart.Series set1;
    public XYChart.Series set2;
    public XYChart.Series set5;
    public XYChart.Series set4;
    public XYChart.Series set6;
    public XYChart.Series set7;
    public XYChart.Series set8;
    public XYChart.Series set9;

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
         FlodaLog[] average= customerDAO.getFlodaAverage(10, who);


            number = plant[0].getNumber();
            number2 = average[0].getNumber();


            set1 = new XYChart.Series<>();
            set6 = new XYChart.Series<>();
            for (int i = 0; i < number; i++) {
                set1.getData().add(new XYChart.Data(plant[i].getDate().substring(0, 16), plant[i].getTemperature()));
            }
            for (int i = 0; i < number2; i++) {
            set6.getData().add(new XYChart.Data(average[i].getDate(), average[i].getTemperature()));
            }
            temperatureChart.getXAxis().setTickLabelsVisible(false);
            temperatureChart.getXAxis().setOpacity(0);

            set2 = new XYChart.Series<>();
            set7 = new XYChart.Series<>();
            for (int i = 0; i < number; i++) {
                set2.getData().add(new XYChart.Data(plant[i].getDate().substring(0, 16), plant[i].getSoil()));
            }
            for (int i = 0; i < number2; i++) {
            set7.getData().add(new XYChart.Data(average[i].getDate(), average[i].getSoil()));
            }
            soilChart.getXAxis().setTickLabelsVisible(false);
            soilChart.getXAxis().setOpacity(0);



            set4 = new XYChart.Series<>();
            set8 = new XYChart.Series<>();
            for (int i = 0; i < number; i++) {
                set4.getData().add(new XYChart.Data(plant[i].getDate().substring(0, 16), plant[i].getHumidity()));
            }
        for (int i = 0; i < number2; i++) {
            set8.getData().add(new XYChart.Data(average[i].getDate(), average[i].getHumidity()));
        }
            humidityChart.getXAxis().setTickLabelsVisible(false);
            humidityChart.getXAxis().setOpacity(0);


            set5 = new XYChart.Series<>();
            set9 = new XYChart.Series<>();
            for (int i = 0; i < number; i++) {
                set5.getData().add(new XYChart.Data(plant[i].getDate().substring(0, 16), plant[i].getSun()));
            }
        for (int i = 0; i < number2; i++) {
            set9.getData().add(new XYChart.Data(average[i].getDate(), average[i].getSun()));
        }

        checkEvent(null);


    }

    public void checkEvent(ActionEvent event){
        temperatureChart.getData().clear();
        soilChart.getData().clear();
        humidityChart.getData().clear();
        sunChart.getData().clear();
        if(checkBox.isSelected()){
            temperatureChart.getData().addAll(set6);
            soilChart.getData().addAll(set7);
            humidityChart.getData().addAll(set8);
            sunChart.getData().addAll(set9);
        }
        else{
            temperatureChart.getData().addAll(set1);
            soilChart.getData().addAll(set2);
            humidityChart.getData().addAll(set4);
            sunChart.getData().addAll(set5);
        }
    }
}
