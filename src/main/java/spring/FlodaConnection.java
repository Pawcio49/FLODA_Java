package spring;

public class FlodaConnection {
    int id;
    String whose;
    int id_sondy;
    String name;
    int id_from_base;
    boolean end;
    int number;

    public FlodaConnection(int id, String whose, int id_sondy, String name, int id_from_base, boolean end) {
        this.id=id;
        this.whose=whose;
        this.id_sondy=id_sondy;
        this.name=name;
        this.id_from_base=id_from_base;
        this.end=end;
    }

    public FlodaConnection(FlodaConnection flodaConnection) {
            this.id=flodaConnection.id;
            this.whose=flodaConnection.whose;
            this.id_sondy=flodaConnection.id_sondy;
            this.name=flodaConnection.name;
            this.id_from_base=flodaConnection.id_from_base;
            this.end=flodaConnection.end;
            this.number=flodaConnection.number;
    }

    public FlodaConnection(boolean end) {
        this.end=end;
    }

    public int getId(){ return id; }
    public String getWhose(){ return whose; }
    public int getId_sondy(){ return id_sondy; }
    public String getName(){ return name; }
    public int getId_from_base(){ return id_from_base; }
    public boolean getEnd(){ return end; }
    public int getNumber(){return number;}
    public FlodaConnection(){

    }
}
