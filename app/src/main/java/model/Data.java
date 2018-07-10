package model;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("loc")
    @Expose
    private String loc;
    @SerializedName("prob")
    @Expose
    private Double prob;
    @SerializedName("seen")
    @Expose
    private Integer seen;
    private final static long serialVersionUID = -2214283706339528383L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param loc
     * @param seen
     * @param prob
     */
    public Data(String loc, Double prob, Integer seen) {
        super();
        this.loc = loc;
        this.prob = prob;
        this.seen = seen;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Data withLoc(String loc) {
        this.loc = loc;
        return this;
    }

    public Double getProb() {
        return prob;
    }

    public void setProb(Double prob) {
        this.prob = prob;
    }

    public Data withProb(Double prob) {
        this.prob = prob;
        return this;
    }

    public Integer getSeen() {
        return seen;
    }

    public void setSeen(Integer seen) {
        this.seen = seen;
    }

    public Data withSeen(Integer seen) {
        this.seen = seen;
        return this;
    }

}
