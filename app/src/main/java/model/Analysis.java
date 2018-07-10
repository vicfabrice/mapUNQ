package model;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Analysis implements Serializable
{

    @SerializedName("analysis")
    @Expose
    private LocationGuesser analysis;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sensors")
    @Expose
    private Sensors sensors;
    @SerializedName("success")
    @Expose
    private Boolean success;
    private final static long serialVersionUID = 7789204161700272570L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Analysis() {
    }

    /**
     *
     * @param sensors
     * @param message
     * @param analysis
     * @param success
     */
    public Analysis(LocationGuesser analysis, String message, Sensors sensors, Boolean success) {
        super();
        this.analysis = analysis;
        this.message = message;
        this.sensors = sensors;
        this.success = success;
    }

    public LocationGuesser getAnalysis() {
        return analysis;
    }

    public void setAnalysis(LocationGuesser analysis) {
        this.analysis = analysis;
    }

    public Analysis withAnalysis(LocationGuesser analysis) {
        this.analysis = analysis;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Analysis withMessage(String message) {
        this.message = message;
        return this;
    }

    public Sensors getSensors() {
        return sensors;
    }

    public void setSensors(Sensors sensors) {
        this.sensors = sensors;
    }

    public Analysis withSensors(Sensors sensors) {
        this.sensors = sensors;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Analysis withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

}