package spring;
public class Info {

    int ID;
    String Nick;
    String Name;
    String Surname;
    String Email;
    String passwd;
    Boolean blocked;
    Boolean su;
    public Info(int ID, String Nick, String Name, String Surname, String Email, String passwd, boolean blocked,boolean su) {
        this.ID=ID;
        this.Nick=Nick;
        this.Name=Name;
        this.Surname=Surname;
        this.Email=Email;
        this.passwd=passwd;
        this.su=su;
        this.blocked=blocked;
    }
    public Info(String Email) {
        this.Email=Email;
        this.blocked=false;
    }

    public Info(boolean blocked) {
        this.blocked=blocked;
        this.Email="blocked";
    }

    public Info(Info info) {
        this.ID=info.ID;
        this.Nick=info.Nick;
        this.Name=info.Name;
        this.Surname=info.Surname;
        this.Email=info.Email;
        this.passwd=info.passwd;
        this.su=info.su;
        this.blocked=info.blocked;

    }

    public int getID() {
        return ID;
    }

    public String getEmail() {
        return Email;
    }

    public String getNick() {
        return Nick;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getPasswd() {
        return passwd;
    }

    public boolean getblocked() {
        return blocked;
    }
    public boolean getsu() {
        return su;
    }
}
