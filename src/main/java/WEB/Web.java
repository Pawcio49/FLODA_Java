package WEB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class Web {

    @FXML
    WebView webView;


    @FXML
    void initialize(){
        WebEngine engine = webView.getEngine();
        engine.load("http://serwer1727017.home.pl/2ti/floda/web/wp");
    }
}
