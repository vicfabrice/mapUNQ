package model;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guess implements Serializable
{

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("probability")
    @Expose
    private Double probability;
    private final static long serialVersionUID = -2099067155980225439L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Guess() {
    }

    /**
     *
     * @param location
     * @param probability
     */
    public Guess(String location, Double probability) {
        super();
        this.location = location;
        this.probability = probability;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Guess withLocation(String location) {
        this.location = location;
        return this;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Guess withProbability(Double probability) {
        this.probability = probability;
        return this;
    }

}