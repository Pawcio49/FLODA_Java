package spring;

import Addflower.DateofChoiceBox;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface CustomerDAO {
    public DriverManagerDataSource ds = null;
    public String insert(String whose, String name);
    public Info findByCustomerId(String nick,String passwd);
    public ResultSet getInfoTable(String statement);
    //public FlodaConnection[] getFlodaConnection(int line, int who);
    public ArrayList<FlodaConnection> getFlodaConnection(int line, int who);
    public FlodaLog[] getFlodaLog(int line, int who);
    public  ArrayList<DateofChoiceBox> getData();
    public  ArrayList<DateofChoiceBox> getData2();
    public DataOfUser getDataOfUser(String id);
    public FlodaLog[] getFlodaAverage(int line, int who);
}
