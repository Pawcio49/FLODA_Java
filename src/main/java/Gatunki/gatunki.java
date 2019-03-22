package Gatunki;

import Addflower.DateofChoiceBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.CustomerDAO;
import spring.Types;


import javax.swing.text.View;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class gatunki {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
    CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");
    int height;
    String pod;
    String nas;
    String tem;
    String wil;

    @FXML
    AnchorPane mainAnchor;
    @FXML
    RadioButton wszystkie;
    @FXML
    RadioButton domyslne;
    @FXML
    RadioButton dodane;

    @FXML
    public void initialize(){
        ArrayList<Types> defaultTypes = customerDAO.getDefaultTypes();
        ArrayList<Types> addedTypes = customerDAO.getAddedTypes();

        AnchorPane defaultAnchor=new AnchorPane();
        AnchorPane addedAnchor=new AnchorPane();

        mainAnchor.getChildren().addAll(defaultAnchor,addedAnchor);
        mainAnchor.setLeftAnchor(defaultAnchor,0.0);
        mainAnchor.setRightAnchor(defaultAnchor,0.0);
        mainAnchor.setTopAnchor(defaultAnchor,175.0);
        mainAnchor.setLeftAnchor(addedAnchor,0.0);
        mainAnchor.setRightAnchor(addedAnchor,0.0);

        defaultTypes.forEach((n) -> addType(n, defaultAnchor));
        //System.out.println(defaultAnchor.getHeight());
        height=0;
        addedTypes.forEach((n) -> addType(n, addedAnchor));


        wszystkie.setOnAction(actionEvent -> {
            defaultAnchor.setVisible(true);
            addedAnchor.setVisible(true);
            mainAnchor.setTopAnchor(addedAnchor,defaultAnchor.getHeight()+193.0);
            System.out.println(defaultAnchor.getHeight());
        });

        domyslne.setOnAction(actionEvent -> {
            defaultAnchor.setVisible(true);
            addedAnchor.setVisible(false);
        });

        dodane.setOnAction(actionEvent -> {
            defaultAnchor.setVisible(false);
            addedAnchor.setVisible(true);
            mainAnchor.setTopAnchor(addedAnchor,175.0);

        });

        Platform.runLater(() -> {
            mainAnchor.setTopAnchor(addedAnchor,defaultAnchor.getHeight()+193);
        });
    }



    public void addType(Types n, AnchorPane anchorPane){
        Label name = new Label(n.getNazwa());
        anchorPane.getChildren().add(name);
        name.setAlignment(Pos.CENTER);
        name.setTextFill(Paint.valueOf("#00A993"));
        name.setFont(Font.font("System", FontWeight.BOLD,14));
        anchorPane.setLeftAnchor(name,0.0);
        anchorPane.setRightAnchor(name,0.0);
        anchorPane.setTopAnchor(name,14.0+height);

        SplitPane splitPane=new SplitPane();
        anchorPane.getChildren().add(splitPane);
        splitPane.setDividerPositions(0.5);
        anchorPane.setLeftAnchor(splitPane,0.0);
        anchorPane.setRightAnchor(splitPane,0.0);
        anchorPane.setTopAnchor(splitPane,48.0+height);
        splitPane.setPrefHeight(112.0);

        AnchorPane leftAnchor=new AnchorPane();
        AnchorPane rightAnchor=new AnchorPane();
        splitPane.getItems().addAll(leftAnchor,rightAnchor);

        Label podlewanie=new Label("Podlewanie");
        podlewanie.setAlignment(Pos.CENTER);

        if(n.getC_k_p()==0)
        {
            if(n.getA_w_g()==0)
                pod="Brak ustaleń";
            else
                pod="min "+n.getA_w_g()+"j.";
        }
        else
            pod="co "+n.getC_k_p()+" dni";


        Label datePod=new Label(pod);
        datePod.setAlignment(Pos.CENTER);

        Label temperatura=new Label("Temperatura");
        temperatura.setAlignment(Pos.CENTER);

        if(n.getS_d_t()==0 && n.getS_d_t_x()==0)
            tem="Brak ustaleń";
        else
            tem=n.getS_d_t()+"-"+n.getS_d_t_x()+"°C";

        Label dateTem=new Label(tem);
        dateTem.setAlignment(Pos.CENTER);

        leftAnchor.getChildren().addAll(podlewanie,datePod,temperatura,dateTem);
        datePod.setLayoutY(28);
        temperatura.setLayoutY(57);
        dateTem.setLayoutY(85);

        temperatura.setPrefHeight(30.0);
        podlewanie.setPrefHeight(30.0);

        leftAnchor.setLeftAnchor(temperatura,0.0);
        leftAnchor.setRightAnchor(temperatura,0.0);
        leftAnchor.setLeftAnchor(dateTem,0.0);
        leftAnchor.setRightAnchor(dateTem,0.0);
        leftAnchor.setLeftAnchor(podlewanie,0.0);
        leftAnchor.setRightAnchor(podlewanie,0.0);
        leftAnchor.setLeftAnchor(datePod,0.0);
        leftAnchor.setRightAnchor(datePod,0.0);

        Label naslonecznienie=new Label("Nasłonecznienie");
        naslonecznienie.setAlignment(Pos.CENTER);

        if(n.getS_d_s()==0 && n.getS_d_s_x()==0)
            nas= "Brak ustaleń";
        else
            nas=n.getS_d_s()+"-"+n.getS_d_s_x()+" lux/dzień";

        Label dateNas=new Label(nas);

        dateNas.setAlignment(Pos.CENTER);

        Label wilgotnosc=new Label("Wilgotność");
        wilgotnosc.setAlignment(Pos.CENTER);

        if(n.getS_d_w()==0 && n.getS_d_w_x()==0)
            wil="Brak ustaleń";
        else
            wil=n.getS_d_w()+"-"+n.getS_d_w_x()+"%";

        Label dateWil=new Label(wil);
        dateWil.setAlignment(Pos.CENTER);

        rightAnchor.getChildren().addAll(naslonecznienie,dateNas,wilgotnosc,dateWil);
        dateNas.setLayoutY(28);
        wilgotnosc.setLayoutY(57);
        dateWil.setLayoutY(85);

        naslonecznienie.setPrefHeight(30.0);
        wilgotnosc.setPrefHeight(30.0);

        rightAnchor.setLeftAnchor(naslonecznienie,0.0);
        rightAnchor.setRightAnchor(naslonecznienie,0.0);
        rightAnchor.setLeftAnchor(dateNas,0.0);
        rightAnchor.setRightAnchor(dateNas,0.0);
        rightAnchor.setLeftAnchor(wilgotnosc,0.0);
        rightAnchor.setRightAnchor(wilgotnosc,0.0);
        rightAnchor.setLeftAnchor(dateWil,0.0);
        rightAnchor.setRightAnchor(dateWil,0.0);


        height+=177;
    }
}
