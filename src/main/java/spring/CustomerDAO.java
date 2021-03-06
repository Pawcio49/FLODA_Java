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
    public ArrayList<FlodaConnection> getFlodaConnection(int who);
    public FlodaLog[] getFlodaLog(int line, int who);
    public  ArrayList<DateofChoiceBox> getData();
    public  ArrayList<DateofChoiceBox> getData2();
    public DataOfUser getDataOfUser(String id);
    public FlodaLog[] getFlodaAverage(int line, int who);
    public ArrayList<Types> getDefaultTypes();
    public ArrayList<Types> getAddedTypes();
    public String findID_from_base(int who);
    public Types getDateTypes(String id);
    public int findID_sondy(int who);
}
