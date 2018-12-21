package spring;

public class DataOfUser {
    String nick;
    String name;
    String surname;
    String password;

    public DataOfUser(String name,String surname, String nick){
        this.name=name;
        this.surname=surname;
        this.nick=nick;
    }


    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

}
