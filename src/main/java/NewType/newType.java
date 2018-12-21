package NewType;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class newType {
    String id;

    @FXML
    TextField nazwa;
    @FXML
    TextField s_d_s;
    @FXML
    TextField a_w_g;
    @FXML
    TextField c_k_p;
    @FXML
    TextField s_d_t;
    @FXML
    TextField s_d_w;
    @FXML
    TextField www;
    @FXML
    Button button;
    @FXML
    Label information;

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


        button.setOnAction(actionEvent -> {
            //System.out.println(id + nazwa.getText() + s_d_s.getText() + a_w_g.getText() + c_k_p.getText() + s_d_t.getText() +  s_d_w.getText());
            PhpNewType phpNewType=new PhpNewType();
            try {
              information.setText(phpNewType.newType(id, nazwa.getText(), s_d_s.getText(), a_w_g.getText(), c_k_p.getText(), s_d_t.getText(), s_d_w.getText(),www.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
