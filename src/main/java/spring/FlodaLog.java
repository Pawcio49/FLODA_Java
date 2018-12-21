package spring;

public class FlodaLog {
    int temperature;
    int soil;
    int humidity;
    int sun;
    String date;
    boolean end;
    int number;

    public FlodaLog(int temperature, int soil, int humidity, int sun, String date, boolean end) {
        this.temperature=temperature;
        this.soil=soil;
        this.humidity=humidity;
        this.sun=sun;
        this.date=date;
        this.end=end;
    }

    public FlodaLog(FlodaLog flodaLog) {
        this.temperature=flodaLog.temperature;
        this.soil=flodaLog.soil;
        this.humidity=flodaLog.humidity;
        this.sun=flodaLog.sun;
        this.date=flodaLog.date;
        this.end=flodaLog.end;
        this.number=flodaLog.number;
    }

    public FlodaLog(boolean end) {
        this.end=end;
    }

    public int getTemperature(){ return temperature; }
    public int getSoil(){ return soil; }
    public int getHumidity(){ return humidity; }
    public int getSun(){ return sun; }
    public String getDate(){ return date; }
    public boolean getEnd(){ return end; }
    public int getNumber(){return number;}

    public FlodaLog(){ }
}
