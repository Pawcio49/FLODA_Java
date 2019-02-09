package NewType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class newType {
    String id;

    @FXML
    TextField nazwa;
    @FXML
    TextField minNas;
    @FXML
    TextField maxNas;
    @FXML
    TextField c_k_p;
    @FXML
    TextField minTem;
    @FXML
    TextField maxTem;
    @FXML
    TextField minWil;
    @FXML
    TextField maxWil;
    @FXML
    TextField www;
    @FXML
    Button button;
    @FXML
    Label information;
    @FXML
    ChoiceBox choicePod;
    @FXML
    ChoiceBox choiceNas;
    @FXML
    CheckBox checkPod;
    @FXML
    CheckBox checkTem;
    @FXML
    CheckBox checkNas;
    @FXML
    CheckBox checkWil;
    @FXML
    CheckBox checkLin;
    @FXML
    RadioButton radioPod1;
    @FXML
    RadioButton radioPod2;
    @FXML
    RadioButton radioNas1;
    @FXML
    RadioButton radioNas2;
    @FXML
    SplitPane splitTem;
    @FXML
    SplitPane splitNas;
    @FXML
    SplitPane splitWil;
    @FXML
    Label Pod;
    @FXML
    Label Tem;
    @FXML
    Label Nas;
    @FXML
    Label Wil;
    @FXML
    Label Lin;

    @FXML
    public void initialize(){
        metPod();

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

        choicePod.getItems().addAll("Wybierz opcję","*(0j-Suchy czujnik)*","*(100j-Szklanka z wodą)*","Sucho (poniżej 35j)","Wilgotno (36-60j)","Mokro (61-80j)","Stoi woda (powyżej 80j)");
        choicePod.setValue("Wybierz opcję");

        choiceNas.getItems().addAll("Wybierz opcję", "Pełne słońce", "Częściowe słońce/Częściowy cień", "Lekko nasłoneczniony", "Pełny cień");
        choiceNas.setValue("Wybierz opcję");

        radioPod1.setOnAction(actionEvent -> {
            c_k_p.setVisible(true);
            choicePod.setVisible(false);
        });

        radioPod2.setOnAction(actionEvent -> {
            c_k_p.setVisible(false);
            choicePod.setVisible(true);
        });

        radioNas1.setOnAction(actionEvent -> {
            splitNas.setVisible(true);
            choiceNas.setVisible(false);
        });

        radioNas2.setOnAction(actionEvent -> {
            splitNas.setVisible(false);
            choiceNas.setVisible(true);
        });


        checkPod.setOnAction(actionEvent -> {
            metPod();
        });

        checkTem.setOnAction(actionEvent -> {
            metTem();
        });

        checkNas.setOnAction(actionEvent -> {
            metNas();
        });

        checkWil.setOnAction(actionEvent -> {
            metWil();
        });

        checkLin.setOnAction(actionEvent -> {
            metLin();
        });

        /*button.setOnAction(actionEvent -> {
            PhpNewType phpNewType=new PhpNewType();
            try {
              information.setText(phpNewType.newType(id, nazwa.getText(), s_d_s.getText(), a_w_g.getText(), c_k_p.getText(), s_d_t.getText(), s_d_w.getText(),www.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    public void metPod(){
        if(checkPod.isSelected()){
            if(radioPod1.isSelected()){
                c_k_p.setVisible(true);
            }
            else if(radioPod2.isSelected()){
                choicePod.setVisible(true);
            }
            radioPod1.setVisible(true);
            radioPod2.setVisible(true);
            Tem.setLayoutY(285);
            checkTem.setLayoutY(285);
        }
        else{
            radioPod1.setVisible(false);
            radioPod2.setVisible(false);
            c_k_p.setVisible(false);
            choicePod.setVisible(false);
            Tem.setLayoutY(151);
            checkTem.setLayoutY(151);
        }
        metTem();
    }
    public void metTem(){
        splitTem.setLayoutY(28+Tem.getLayoutY());
        if(checkTem.isSelected()){
            splitTem.setVisible(true);
            Nas.setLayoutY(50+splitTem.getLayoutY());
            checkNas.setLayoutY(50+splitTem.getLayoutY());
        }
        else{
            splitTem.setVisible(false);
            Nas.setLayoutY(40+Tem.getLayoutY());
            checkNas.setLayoutY(40+Tem.getLayoutY());
        }

        metNas();
    }

    public void metNas(){
        radioNas1.setLayoutY(Nas.getLayoutY()+28);
        radioNas2.setLayoutY(Nas.getLayoutY()+96);
        splitNas.setLayoutY(Nas.getLayoutY()+56);
        choiceNas.setLayoutY(Nas.getLayoutY()+125);
        if(checkNas.isSelected()){
            if(radioNas1.isSelected()){
                splitNas.setVisible(true);
            }
            else if(radioNas2.isSelected()){
                choiceNas.setVisible(true);
            }
            radioNas1.setVisible(true);
            radioNas2.setVisible(true);
            Wil.setLayoutY(45+choiceNas.getLayoutY());
            checkWil.setLayoutY(45+choiceNas.getLayoutY());

        }
        else{
            radioNas1.setVisible(false);
            radioNas2.setVisible(false);
            splitNas.setVisible(false);
            choiceNas.setVisible(false);
            Wil.setLayoutY(40+Nas.getLayoutY());
            checkWil.setLayoutY(40+Nas.getLayoutY());
        }
        metWil();
    }

    public void metWil(){
        splitWil.setLayoutY(28+Wil.getLayoutY());
        if(checkWil.isSelected()){
            splitWil.setVisible(true);
            Lin.setLayoutY(50+splitWil.getLayoutY());
            checkLin.setLayoutY(50+splitWil.getLayoutY());
        }
        else{
            splitWil.setVisible(false);
            Lin.setLayoutY(40+Wil.getLayoutY());
            checkLin.setLayoutY(40+Wil.getLayoutY());
        }
        metLin();
    }

    public void metLin(){
        www.setLayoutY(28+Lin.getLayoutY());
        if(checkLin.isSelected()){
            www.setVisible(true);
            button.setLayoutY(77+Lin.getLayoutY());
            information.setLayoutY(114+Lin.getLayoutY());
        }
        else{
            www.setVisible(false);
            button.setLayoutY(49+Lin.getLayoutY());
            information.setLayoutY(86+Lin.getLayoutY());
        }
    }
}
