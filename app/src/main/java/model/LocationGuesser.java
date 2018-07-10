package model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationGuesser implements Serializable
{

    @SerializedName("location_names")
    @Expose
    private LocationNames locationNames;
    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions = null;
    @SerializedName("guesses")
    @Expose
    private List<Guess> guesses = null;
    private final static long serialVersionUID = -6496158036783790692L;

    /**
     * No args constructor for use in serialization
     *
     */
    public LocationGuesser() {
    }

    /**
     *
     * @param guesses
     * @param predictions
     * @param locationNames
     */
    public LocationGuesser(LocationNames locationNames, List<Prediction> predictions, List<Guess> guesses) {
        super();
        this.locationNames = locationNames;
        this.predictions = predictions;
        this.guesses = guesses;
    }

    public LocationNames getLocationNames() {
        return locationNames;
    }

    public void setLocationNames(LocationNames locationNames) {
        this.locationNames = locationNames;
    }

    public LocationGuesser withLocationNames(LocationNames locationNames) {
        this.locationNames = locationNames;
        return this;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    public LocationGuesser withPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
        return this;
    }

    public List<Guess> getGuesses() {
        return guesses;
    }

    public String mostProbablyLocation(){
        if (guesses.size()<=1){
            if (guesses.get(0).getLocation()=="?"){
                return "No tenemos ubicación conocida";
            } else {return guesses.get(0).getLocation();}
        } else {return guesses.get(0).getLocation();}

    }

    public String almostProbablyLocation(){
        if (guesses.size()<=1){
            if (guesses.get(0).getLocation()=="?"){
                return "No tenemos ubicación conocida";
            } else {return "No tenemos ubicación conocida";}
        } else {return guesses.get(1).getLocation();}
    }
    public void setGuesses(List<Guess> guesses) {
        this.guesses = guesses;
    }

    public LocationGuesser withGuesses(List<Guess> guesses) {
        this.guesses = guesses;
        return this;
    }

}
