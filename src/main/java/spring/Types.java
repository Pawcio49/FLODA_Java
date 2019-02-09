package spring;

public class Types {
    String nazwa;
    int s_d_s;
    int s_d_s_x;
    int a_w_g;
    int s_d_t;
    int s_d_t_x;
    int s_d_w;
    int s_d_w_x;
    int c_k_p;
    int number;

    public Types(String nazwa, int s_d_s, int s_d_s_x, int a_w_g, int s_d_t, int s_d_t_x, int s_d_w, int s_d_w_x, int c_k_p) {
        this.nazwa=nazwa;
        this.s_d_s = s_d_s;
        this.s_d_s_x = s_d_s_x;
        this.a_w_g = a_w_g;
        this.s_d_t = s_d_t;
        this.s_d_t_x = s_d_t_x;
        this.s_d_w = s_d_w;
        this.s_d_w_x = s_d_w_x;
        this.c_k_p = c_k_p;
    }

    public Types(){}

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getNumber() {
        return number;
    }

    public int getC_k_p() {
        return c_k_p;
    }

    public int getS_d_s() {
        return s_d_s;
    }

    public int getS_d_s_x() {
        return s_d_s_x;
    }

    public int getA_w_g() {
        return a_w_g;
    }

    public int getS_d_t() {
        return s_d_t;
    }

    public int getS_d_t_x() {
        return s_d_t_x;
    }

    public int getS_d_w() {
        return s_d_w;
    }

    public int getS_d_w_x() {
        return s_d_w_x;
    }
}
