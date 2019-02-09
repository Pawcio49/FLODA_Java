package recover;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class Recover {
    String mail;
    int mailCode;
    int verifyCode;

    @FXML
    TextField email;
    @FXML
    TextField kod;
    @FXML
    PasswordField newPass;
    @FXML
    PasswordField verPass;
    @FXML
    Button sendMail;
    @FXML
    Button change;
    @FXML
    Button verify;
    @FXML
    Label comPass;
    @FXML
    Label comCode;
    @FXML
    Label comMail;
    @FXML
    ImageView login;

    @FXML
    public void initialize(){
        PhpRecover phpRecover=new PhpRecover();
        sendMail.setOnAction(actionEvent -> {
            verify.setDefaultButton(false);
            change.setDefaultButton(false);
            sendMail.setDefaultButton(true);
            try {
                mail=email.getText();
                mailCode=phpRecover.mail(mail);
                kod.setVisible(false);
                verify.setVisible(false);

                if(mailCode==1) {
                    comMail.setText("Link potwierdzający został wysłany na e-maila!");
                    // System.out.println(phpRecover.mail(email.getText()));
                    kod.setVisible(true);
                    verify.setVisible(true);
                    verify.setDefaultButton(true);
                    sendMail.setDefaultButton(false);
                }
                else if(mailCode==0){
                    comMail.setText("Wpisz e-maila");
                }
                else{
                    comMail.setText("Nie ma zarejestrowanego żadnego użytkownika o podanym e-mailu");
                }
                newPass.setVisible(false);
                verPass.setVisible(false);
                change.setVisible(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            kod.setText("");
            comCode.setText("");
            comPass.setText("");
            verPass.setText("");
            newPass.setText("");
        });

        verify.setOnAction(actionEvent -> {
            change.setDefaultButton(false);
            verify.setDefaultButton(true);

            try {
                comPass.setText("");
                verPass.setText("");
                newPass.setText("");
                verifyCode=phpRecover.verify(mail, kod.getText());
                if (verifyCode==0){
                    comCode.setText("Wpisz kod");
                    newPass.setVisible(false);
                    verPass.setVisible(false);
                    change.setVisible(false);
                }
                else if (verifyCode==1){
                    comCode.setText("Błędny kod");
                    newPass.setVisible(false);
                    verPass.setVisible(false);
                    change.setVisible(false);
                }
                else{
                    comCode.setText("Poprawny kod");
                    newPass.setVisible(true);
                    verPass.setVisible(true);
                    change.setVisible(true);
                    change.setDefaultButton(true);
                    verify.setDefaultButton(false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        change.setOnAction(actionEvent -> {
            try {
                comPass.setText(phpRecover.change(mail, newPass.getText(),verPass.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        login.setOnMouseClicked(actionEvent -> {
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("screen/menu.fxml"));
                Stage stage = (Stage) change.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();

            }
        });
    }
}
