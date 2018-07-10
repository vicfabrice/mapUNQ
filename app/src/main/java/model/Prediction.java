package model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prediction implements Serializable
{

    @SerializedName("locations")
    @Expose
    private List<String> locations = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("probabilities")
    @Expose
    private List<Double> probabilities = null;
    private final static long serialVersionUID = 7916883911020638704L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Prediction() {
    }

    /**
     *
     * @param locations
     * @param name
     * @param probabilities
     */
    public Prediction(List<String> locations, String name, List<Double> probabilities) {
        super();
        this.locations = locations;
        this.name = name;
        this.probabilities = probabilities;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public Prediction withLocations(List<String> locations) {
        this.locations = locations;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Prediction withName(String name) {
        this.name = name;
        return this;
    }

    public List<Double> getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(List<Double> probabilities) {
        this.probabilities = probabilities;
    }

    public Prediction withProbabilities(List<Double> probabilities) {
        this.probabilities = probabilities;
        return this;
    }

}