package spring;

import Addflower.DateofChoiceBox;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.validation.constraints.Null;
import java.sql.*;
import java.util.ArrayList;

public class JdbcCustomerDAO implements CustomerDAO {
    private DriverManagerDataSource ds;

    public String insert(String whose, String name) {

        String sql = "INSERT INTO FLODA_connections " +
                "(whose, Name, ID ) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, whose);
            ps.setString(2, name);
            ps.setString(3, "11");

            ps.executeUpdate();
            ps.close();

        } catch (SQLTimeoutException f) {

        } catch (SQLException e) {
            return "You can't add flower!";

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return "Dodano kwiatka";
    }

    public ResultSet getInfoTable(String statement) {
        ResultSet rs = null;
        String sql = statement;
        Connection connection = null;
        try {
            connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public Info findByCustomerId(String nick, String passwd) {

        String sql = "SELECT * FROM floda_user_detail WHERE Nick = ? AND passwd = md5(?)";

        Connection conn = null;

        try {
            conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nick);
            ps.setString(2, passwd);
            Info customer = null;
            ResultSet rs = ps.executeQuery();
            if (!rs.wasNull()) {
                if (rs.next()) {
                    if (rs.getBoolean("blocked") == true) {
                        customer = new Info("blocked");
                    } else {
                        customer = new Info(
                                rs.getInt("ID"),
                                rs.getString("Nick"),
                                rs.getString("Name"),
                                rs.getString("Surname"),
                                rs.getString("Email"),
                                rs.getString("passwd"),
                                rs.getBoolean("blocked"),
                                rs.getBoolean("su")
                        );
                    }

                }else {
                    return new Info(
                            "erro"
                    );
                 }

            }
            rs.close();
            ps.close();
            return customer;

        } catch (CommunicationsException F) {
            return new Info(
                    "err"

            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void setDs(DriverManagerDataSource ds) {
        this.ds = ds;
    }

    public DriverManagerDataSource getDs() {
        return ds;
    }

    public ArrayList<FlodaConnection> getFlodaConnection(int line, int who) {
        //public FlodaConnection[] getFlodaConnection(int line, int who) {
        String sql = "SELECT ID, whose, ID_sondy, Name, ID_from_base FROM FLODA_connections WHERE whose = " + who;

        Connection conn = null;
        FlodaConnection floCon;


        try {
            conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ArrayList<FlodaConnection> customer = new ArrayList<FlodaConnection>();
            //FlodaConnection customer[]=new FlodaConnection[10];

            int number = 0;
            ResultSet rs = ps.executeQuery();

            for (int i = 0; ; i++) {
                if (!rs.next()) {
                    break;
                } else {

                    floCon = new FlodaConnection(
                            //customer[i] = new FlodaConnection(
                            rs.getInt("ID"),
                            rs.getString("whose"),
                            rs.getInt("ID_sondy"),
                            rs.getString("Name"),
                            rs.getInt("ID_from_base"),
                            false);
                    number++;
                    customer.add(floCon);
                }
            }

            if(number==0)
            {
                floCon=new FlodaConnection(0,"0",0,"0",0,false);
                customer.add(floCon);
            }

            customer.get(0).number = number;
            //customer[0].number=number;
            rs.close();
            ps.close();
            return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }


    public FlodaLog[] getFlodaLog(int line, int who) {
        String sql = "SELECT nr_floda, temperature, soil, humidity, sun, date FROM floda_log WHERE nr_floda = " + who + " order by id desc";

        Connection conn = null;

        try {
            conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            int number = 0;
            ResultSet rs = ps.executeQuery();
            FlodaLog[] customer = new FlodaLog[line];
            customer[0]=new FlodaLog(0,0,0,0,"0",false);
            for (int i = 0; i < line; i++) {


                if (!rs.next()) {
                    break;
                } else {
                    customer[i] = new FlodaLog(
                            rs.getInt("temperature"),
                            rs.getInt("soil"),
                            rs.getInt("humidity"),
                            rs.getInt("sun"),
                            rs.getString("date"),
                            false);
                    number++;
                }
            }
            customer[0].number = number;
            rs.close();
            ps.close();
            return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public FlodaLog[] getFlodaAverage(int line, int who) {
        String sql = "select * from srednia_dniowa where nr_floda=" + who;

        Connection conn = null;

        try {
            conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            int number = 0;
            ResultSet rs = ps.executeQuery();
            FlodaLog[] customer = new FlodaLog[line];
            customer[0]=new FlodaLog(0,0,0,0,"0",false);

            for (int i = 0; i < line; i++) {


                if (!rs.next()) {
                    break;
                } else {
                    StringBuilder content = new StringBuilder();
                    content.append(rs.getString("day"));
                    content.append(" ");
                    content.append(rs.getString("month"));
                    customer[i] = new FlodaLog(
                            rs.getInt("temp"),
                            rs.getInt("soil"),
                            rs.getInt("humidity"),
                            rs.getInt("sun"),
                            content.toString(),
                            false);
                    number++;
                }
            }
            customer[0].number = number;
            rs.close();
            ps.close();
            return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public  ArrayList<DateofChoiceBox> getData() {
        String sql = "SELECT Nazwa, ID FROM FLODA_main_database WHERE id_autora = 1";

        Connection conn = null;

        try {
            conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            int number = 0;
            ResultSet rs = ps.executeQuery();
            ArrayList<DateofChoiceBox> customer = new ArrayList<DateofChoiceBox>();

            customer.add(new DateofChoiceBox("Domyslne ustawienia",0));
            while(rs.next()){
                    customer.add(new DateofChoiceBox(
                            rs.getString("Nazwa"),
                            rs.getInt("ID")
                    ));

                    number++;

            }
            customer.get(0).setNumber(number+1);
            rs.close();
            ps.close();
            return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }



    }
    public  ArrayList<DateofChoiceBox> getData2() {
        String sql = "SELECT Nazwa, ID FROM FLODA_main_database WHERE id_autora != 1";

        Connection conn = null;

        try {
            conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            int number = 0;
            ResultSet rs = ps.executeQuery();
            ArrayList<DateofChoiceBox> customer = new ArrayList<DateofChoiceBox>();

            customer.add(new DateofChoiceBox("Ustawienia dodane przez użytkownikow", 0));
            while (rs.next()) {
                customer.add(new DateofChoiceBox(
                        rs.getString("Nazwa"),
                        rs.getInt("ID")
                ));

                number++;

            }
            customer.get(0).setNumber(number + 1);
            rs.close();
            ps.close();
            return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public DataOfUser getDataOfUser(String id) {

        String sql = "SELECT Name, Surname, Nick FROM floda_user_detail WHERE ID = ?";

        Connection conn = null;

        try {
            conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            DataOfUser dataOfUser = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dataOfUser = new DataOfUser(
                        rs.getString("Name"),
                        rs.getString("Surname"),
                        rs.getString("Nick")
                );
            } else {
            }

            rs.close();
            ps.close();
            return dataOfUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

}




