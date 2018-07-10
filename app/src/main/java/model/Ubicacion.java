package model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ubicacion implements Serializable
{

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private Boolean success;
    private final static long serialVersionUID = -1308932815144043067L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Ubicacion() {
    }

    /**
     *
     * @param message
     * @param data
     * @param success
     */
    public Ubicacion(Data data, String message, Boolean success) {
        super();
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Ubicacion withData(Data data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ubicacion withMessage(String message) {
        this.message = message;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Ubicacion withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

}