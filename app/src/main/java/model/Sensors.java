package model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sensors implements Serializable
{

    @SerializedName("t")
    @Expose
    private Long t;
    @SerializedName("f")
    @Expose
    private String f;
    @SerializedName("d")
    @Expose
    private String d;
    @SerializedName("s")
    @Expose
    private S s;
    @SerializedName("gps")
    @Expose
    private Gps gps;
    private final static long serialVersionUID = -6304934271253573802L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sensors() {
    }

    /**
     *
     * @param f
     * @param d
     * @param t
     * @param s
     * @param gps
     */
    public Sensors(Long t, String f, String d, S s, Gps gps) {
        super();
        this.t = t;
        this.f = f;
        this.d = d;
        this.s = s;
        this.gps = gps;
    }

    public Long getT() {
        return t;
    }

    public void setT(Long t) {
        this.t = t;
    }

    public Sensors withT(Long t) {
        this.t = t;
        return this;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public Sensors withF(String f) {
        this.f = f;
        return this;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public Sensors withD(String d) {
        this.d = d;
        return this;
    }

    public S getS() {
        return s;
    }

    public void setS(S s) {
        this.s = s;
    }

    public Sensors withS(S s) {
        this.s = s;
        return this;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    public Sensors withGps(Gps gps) {
        this.gps = gps;
        return this;
    }

}