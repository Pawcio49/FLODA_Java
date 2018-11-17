package dataView;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.ConnectionHandle;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import spring.CustomerDAO;
import spring.Info;

import javax.persistence.Column;
import java.sql.*;

public class DataViewController {
    @FXML
    TableView table;
    @FXML
    void initialize() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Module.xml");
        CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("CustomerDAO");
        ResultSet rs =customerDAO.getInfoTable("SELECT * FROM floda_user_detail");
        ResultSetMetaData resultSetMetaData = null;
        ObservableList<Info> data = FXCollections.observableArrayList();


        try {
            resultSetMetaData = rs.getMetaData();
            TableColumn tableColumn;
            for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                tableColumn = new TableColumn(resultSetMetaData.getColumnName(i));
                tableColumn.setCellValueFactory(new PropertyValueFactory<Info,Boolean>(resultSetMetaData.getColumnName(i)));
                table.getColumns().add(tableColumn);
                table.getItems().add(resultSetMetaData.getColumnCount());
            }
            while(rs.next()){
                if(rs.wasNull()){break;}
                data.add(customerDAO.findByCustomerId(rs.getString("Nick"),rs.getString("passwd")));
            }
            table.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
