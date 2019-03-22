package Statistics;

import com.sun.javafx.charts.Legend;
import javafx.scene.shape.Rectangle;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.Main;
import spring.*;


import java.beans.ConstructorProperties;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;


public class Stat {
    String id;
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
    CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");
    int who;
    int number;
    int number2;
    String time;
    int soilTime;
    public boolean soilLegend;

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
    @FXML
    AnchorPane settingAnchor;
    @FXML
    TextField password;
    @FXML
    TextField minutes;
    @FXML
    Button cancel;
    @FXML
    Button confirm;
    @FXML
    Button sondaSet;
    @FXML
    Label info;
    @FXML
    Label info2;
    @FXML
    public Label soilInfo;

    public Legend[] legend = new Legend[4];
    public Legend.LegendItem[] li = new Legend.LegendItem[20];

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
        phpSonda phpSonda=new phpSonda();

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
         String x=customerDAO.findID_from_base(who);
         Types type=customerDAO.getDateTypes(x);

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


        try {
            soilTime=phpSonda.getWatered(who);
        } catch (IOException e) {
            e.printStackTrace();
        }

        legend[0]= (Legend) temperatureChart.lookup(".chart-legend");
        legend[1]= (Legend) soilChart.lookup(".chart-legend");
        legend[2]= (Legend) humidityChart.lookup(".chart-legend");
        legend[3]= (Legend) sunChart.lookup(".chart-legend");

        if(type.getS_d_t()!=0 || type.getS_d_t_x()!=0) {
            li[0] = new Legend.LegendItem("Poniżej " + type.getS_d_t() + "°C", new Rectangle(10, 6, Color.ORANGE));
            li[1] = new Legend.LegendItem("Powyżej " + type.getS_d_t_x() + "°C", new Rectangle(10, 6, Color.web("#f8542B")));
            li[2] = new Legend.LegendItem("Odpowiednia temperatura", new Rectangle(10, 6, Color.web("#00A993")));
        }
        else {
            li[0]=new Legend.LegendItem("", new Rectangle(0,0,Color.BLACK));
            li[1]=new Legend.LegendItem("Brak ustaleń", new Rectangle(10,6,Color.web("#00A993")));
            li[2]=new Legend.LegendItem("", new Rectangle(0,0,Color.BLACK));
        }

        soilLegend=true;

        if(type.getA_w_g()!=0) {
            li[3] = new Legend.LegendItem("Poniżej " + type.getA_w_g() + "%", new Rectangle(10, 6, Color.ORANGE));
            li[4] = new Legend.LegendItem("Odpowiednia wilgotność", new Rectangle(10, 6, Color.web("#00A993")));
            li[5]=new Legend.LegendItem("", new Rectangle(0,0,Color.BLACK));
        } else if(type.getC_k_p()!=0){
            soilLegend = false;
            soilChart.setLegendVisible(false);
            soilChart.setPrefHeight(200);

            String soilInfoText;
            if(soilTime ==1)
                soilInfoText="Roślina była podlewana 1 dzień od ostatniego pomiaru, ";
            else if(soilTime==0)
                soilInfoText="Roślina była podlewana w dzień ostatniego pomiaru, ";
            else
                soilInfoText=("Roślina była podlewana " + soilTime + " dni od ostatniego pomiaru, ");

            if(type.getC_k_p()==1){
                soilInfoText=soilInfoText+"a należy ją podlewać codziennie";
            }
            else
                soilInfoText=soilInfoText+"a należy ją podlewać co " + type.getC_k_p() + " dni";

            soilInfo.setText(soilInfoText);
            soilInfo.setVisible(true);
        }
        else {
            li[3]=new Legend.LegendItem("", new Rectangle(0,0,Color.BLACK));
            li[4]=new Legend.LegendItem("Brak ustaleń", new Rectangle(10,6,Color.web("#00A993")));
            li[5]=new Legend.LegendItem("", new Rectangle(0,0,Color.BLACK));
        }

        if(type.getS_d_w()!=0 || type.getS_d_w_x()!=0) {
            li[6] = new Legend.LegendItem("Poniżej " + type.getS_d_w() + "%", new Rectangle(10, 6, Color.ORANGE));
            li[7] = new Legend.LegendItem("Powyżej " + type.getS_d_w_x() + "%", new Rectangle(10, 6, Color.web("#f8542B")));
            li[8] = new Legend.LegendItem("Odpowiednia wilgotność", new Rectangle(10, 6, Color.web("#00A993")));
        }
        else {
            li[6]=new Legend.LegendItem("", new Rectangle(0,0,Color.BLACK));
            li[7]=new Legend.LegendItem("Brak ustaleń", new Rectangle(10,6,Color.web("#00A993")));
            li[8]=new Legend.LegendItem("", new Rectangle(0,0,Color.BLACK));
        }

        if(type.getS_d_s()!=0 || type.getS_d_s_x()!=0) {
            li[9] = new Legend.LegendItem("Poniżej " + type.getS_d_s() + " lux", new Rectangle(10, 6, Color.ORANGE));
            li[10] = new Legend.LegendItem("Powyżej " + type.getS_d_s_x() + " lux", new Rectangle(10, 6, Color.web("#f8542B")));
            li[11] = new Legend.LegendItem("Odpowiednie nasłonecznienie", new Rectangle(10, 6, Color.web("#00A993")));
        }
        else {
            li[9]=new Legend.LegendItem("", new Rectangle(0,0,Color.BLACK));
            li[10]=new Legend.LegendItem("Brak ustaleń", new Rectangle(10,6,Color.web("#00A993")));
            li[11]=new Legend.LegendItem("", new Rectangle(0,0,Color.BLACK));
        }


        checkEvent(null);

        if(type.getS_d_t()!=0 || type.getS_d_t_x()!=0){
            for (int i=0; i<number2;i++){
                Node n = temperatureChart.lookup(".data"+i+".chart-bar");
                if(average[i].getTemperature()<type.getS_d_t()){
                    n.setStyle("-fx-bar-fill: orange");
                }
                else if(average[i].getTemperature()>type.getS_d_t_x()) {
                    n.setStyle("-fx-bar-fill: #f8542B");
                }
                else{
                    n.setStyle("-fx-bar-fill:#00A993");
                }
            }
        }
        else {
            for (int i=0; i<number2;i++){
                Node n = temperatureChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill:#00A993");
            }
        }

        if(type.getA_w_g()!=0) {
            for (int i = 0; i < number2; i++) {
                Node n = soilChart.lookup(".data" + i + ".chart-bar");
                if (average[i].getSoil() < type.getA_w_g()) {
                    n.setStyle("-fx-bar-fill: orange");
                } else {
                    n.setStyle("-fx-bar-fill:#00A993");
                }
            }
        }
        else if(type.getC_k_p()!=0 && type.getC_k_p()<soilTime){
            for (int i=0; i<number2;i++){
                Node n = soilChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill: #f8542B");
            }
        }
        else if(type.getC_k_p()!=0 && type.getC_k_p()==soilTime) {
            for (int i = 0; i < number2; i++) {
                Node n = soilChart.lookup(".data" + i + ".chart-bar");
                n.setStyle("-fx-bar-fill: orange");
            }
        }
        else {
            for (int i=0; i<number2;i++){
                Node n = soilChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill:#00A993");
            }
        }

        if(type.getS_d_w()!=0 || type.getS_d_w_x()!=0){
            for (int i=0; i<number2;i++){
                Node n = humidityChart.lookup(".data"+i+".chart-bar");
                if(average[i].getHumidity()<type.getS_d_w()){
                    n.setStyle("-fx-bar-fill: orange");
                }
                else if(average[i].getHumidity()>type.getS_d_w_x()) {
                    n.setStyle("-fx-bar-fill: #f8542B");
                }
                else{
                    n.setStyle("-fx-bar-fill:#00A993");
                }
            }
        }
        else {
            for (int i=0; i<number2;i++){
                Node n = humidityChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill:#00A993");
            }
        }

        if(type.getS_d_s()!=0 || type.getS_d_s_x()!=0){
            for (int i=0; i<number2;i++){
                Node n = sunChart.lookup(".data"+i+".chart-bar");
                if(average[i].getSun()<type.getS_d_s()){
                    n.setStyle("-fx-bar-fill: orange");
                }
                else if(average[i].getSun()>type.getS_d_s_x()) {
                    n.setStyle("-fx-bar-fill: #f8542B");
                }
                else{
                    n.setStyle("-fx-bar-fill:#00A993");
                }
            }
        }
        else {
            for (int i=0; i<number2;i++){
                Node n =sunChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill:#00A993");
            }
        }

        checkBox.setSelected(false);
        checkEvent(null);

        if(type.getS_d_t()!=0 || type.getS_d_t_x()!=0){
            for (int i=0; i<number;i++){
                Node n = temperatureChart.lookup(".data"+i+".chart-bar");
                if(plant[i].getTemperature()<type.getS_d_t()){
                    n.setStyle("-fx-bar-fill: orange");
                }
                else if(plant[i].getTemperature()>type.getS_d_t_x()) {
                    n.setStyle("-fx-bar-fill: #f8542B");
                }
                else{
                    n.setStyle("-fx-bar-fill:#00A993");
                }
            }
        }
        else {
            for (int i=0; i<number;i++){
                Node n = temperatureChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill:#00A993");
            }
        }

        if(type.getA_w_g()!=0){
            for (int i=0; i<number;i++){
                Node n = soilChart.lookup(".data"+i+".chart-bar");
                if(plant[i].getSoil()<type.getA_w_g()){
                    n.setStyle("-fx-bar-fill: orange");
                }
                else{
                    n.setStyle("-fx-bar-fill:#00A993");
                }
            }
        }
        else if(type.getC_k_p()!=0 && type.getC_k_p()<soilTime){
            for (int i=0; i<number;i++){
                Node n = soilChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill: #f8542B");
            }
        }
        else if(type.getC_k_p()!=0 && type.getC_k_p()==soilTime){
            for (int i=0; i<number;i++){
                Node n = soilChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill: orange");
            }
        }
        else {
            for (int i=0; i<number;i++){
                Node n = soilChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill:#00A993");
            }
        }

        if(type.getS_d_w()!=0 || type.getS_d_w_x()!=0){
            for (int i=0; i<number;i++){
                Node n = humidityChart.lookup(".data"+i+".chart-bar");
                if(plant[i].getHumidity()<type.getS_d_w()){
                    n.setStyle("-fx-bar-fill: orange");
                }
                else if(plant[i].getHumidity()>type.getS_d_w_x()) {
                    n.setStyle("-fx-bar-fill: #f8542B");
                }
                else{
                    n.setStyle("-fx-bar-fill:#00A993");
                }
            }
        }
        else {
            for (int i=0; i<number;i++){
                Node n = humidityChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill:#00A993");
            }
        }

        if(type.getS_d_s()!=0 || type.getS_d_s_x()!=0){
            for (int i=0; i<number;i++){
                Node n = sunChart.lookup(".data"+i+".chart-bar");
                if(plant[i].getSun()<type.getS_d_s()){
                    n.setStyle("-fx-bar-fill: orange");
                }
                else if(plant[i].getSun()>type.getS_d_s_x()) {
                    n.setStyle("-fx-bar-fill: #f8542B");
                }
                else{
                    n.setStyle("-fx-bar-fill:#00A993");
                }
            }
        }
        else {
            for (int i=0; i<number;i++){
                Node n =sunChart.lookup(".data"+i+".chart-bar");
                n.setStyle("-fx-bar-fill:#00A993");
            }
        }



        sondaSet.setOnAction(actionEvent -> {

            confirm.setDefaultButton(true);
            settingAnchor.setVisible(true);
            info.setText("");
            info2.setText("");
            try {
                minutes.setText(phpSonda.getTime(who));
            } catch (IOException e) {
                e.printStackTrace();
            }
            password.setText("");
        });

        cancel.setOnAction(actionEvent -> {
            confirm.setDefaultButton(false);
            settingAnchor.setVisible(false);
        });

        confirm.setOnAction(actionEvent -> {
            if(minutes.getText().equals("")) {
                info.setText("Uzupełnij okres pomiarów");
            }
            else if(Integer.parseInt(minutes.getText()) <= 1440){
                String text;
                time= String.valueOf(Integer.parseInt(minutes.getText()));

                try {
                    if(minutes.getText().equals(phpSonda.getTime(who)))
                    {
                        text=phpSonda.modifySonda("",password.getText(),who);
                        switch (text) {
                            case "1":
                                info.setText("Zmień okres pomiarów lub hasło sondy");
                                break;
                            case "3":
                                info.setText("Wprowadzone hasło sondy jest aktualnym hasłem");
                                break;
                            case "4":
                                info2.setText("Zmieniono hasło sondy");
                                confirm.setDefaultButton(false);
                                settingAnchor.setVisible(false);
                                break;
                        }
                    }
                    else {
                        text=phpSonda.modifySonda(time, password.getText(), who);
                        switch (text) {
                            case "2":
                                info2.setText("Zmieniono okres pomiarów");
                                confirm.setDefaultButton(false);
                                settingAnchor.setVisible(false);
                                break;
                            case "5":
                                info.setText("Wprowadzone hasło sondy jest aktualnym hasłem");
                                break;
                            case "6":
                                info2.setText("Zmieniono okres pomiarów i hasło sondy");
                                confirm.setDefaultButton(false);
                                settingAnchor.setVisible(false);
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                info.setText("Okres pomiarów może wynosić maksymalnie 1440 minut");
            }
        });

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

        legend[0].getItems().setAll(li[0],li[1],li[2]);
        if(soilLegend)
            legend[1].getItems().setAll(li[3],li[4],li[5]);
        legend[2].getItems().setAll(li[6],li[7],li[8]);
        legend[3].getItems().setAll(li[9],li[10],li[11]);

    }
}
