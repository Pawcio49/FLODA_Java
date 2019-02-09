package MainWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import spring.Info;

public class MainWindow {
    public Info info;

    public MainWindow(Info info){
        this.info=info;
    }
    public void start(Stage window) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("screen/mainWindow.fxml"));

        MainControler mainControler = new MainControler(info);
        fxmlLoader.setController(mainControler);
        window.setScene(new Scene(fxmlLoader.load()));
        window.setTitle("Floda");
        window.resizableProperty().setValue(true);


        window.show();

    }
}
