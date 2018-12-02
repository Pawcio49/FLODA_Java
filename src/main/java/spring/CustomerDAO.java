package spring;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface CustomerDAO {
    public DriverManagerDataSource ds = null;
    public void insert(String nick, String name, String surname, String email, String passwd);
    public Info findByCustomerId(String nick,String passwd);
    public ResultSet getInfoTable(String statement);
    //public FlodaConnection[] getFlodaConnection(int line, int who);
    public ArrayList<FlodaConnection> getFlodaConnection(int line, int who);
    public FlodaLog[] getFlodaLog(int line, int who);

}
