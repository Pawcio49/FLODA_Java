package Addflower;

public class DateofChoiceBox {
    String gatunek;
    int id;
    int number;

    public DateofChoiceBox(String gatunek, int id){
        this.gatunek=gatunek;
        this.id=id;
    }

    public void setGatunek(String gatunek){
        this.gatunek=gatunek;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGatunek() {
        return gatunek;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }
}
