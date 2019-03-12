package spring;

public class FlodaConnection {
    int id;
    int id_sondy;
    String name;
    boolean end;
    int number;
    int normwil;
    int normtem;
    int normsun;
    int normpod;

    public FlodaConnection(int id, int id_sondy, String name, int normwil, int normtem, int normsun, int normpod, boolean end) {
        this.id = id;
        this.id_sondy = id_sondy;
        this.name = name;
        this.end = end;
        this.normwil = normwil;
        this.normtem = normtem;
        this.normsun = normsun;
        this.normpod = normpod;
    }

    public FlodaConnection(boolean end) {
        this.end=end;
    }

    public int getId(){ return id; }
    public int getId_sondy(){ return id_sondy; }
    public String getName(){ return name; }
    public boolean getEnd(){ return end; }
    public int getNumber(){return number;}

    public void setId(int id) {
        this.id = id;
    }

    public int getNormwil() {
        return normwil;
    }

    public void setNormwil(int normwil) {
        this.normwil = normwil;
    }

    public int getNormtem() {
        return normtem;
    }

    public void setNormtem(int normtem) {
        this.normtem = normtem;
    }

    public int getNormsun() {
        return normsun;
    }

    public void setNormsun(int normsun) {
        this.normsun = normsun;
    }

    public int getNormpod() {
        return normpod;
    }

    public void setNormpod(int normpod) {
        this.normpod = normpod;
    }
}
