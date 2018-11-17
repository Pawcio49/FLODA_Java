package WEB;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class Web {

    @FXML
    WebView webView;

    @FXML
    void initialize(){
        WebEngine engine = webView.getEngine();
        engine.load("http://www.pawdop.cba.pl");
    }
}
