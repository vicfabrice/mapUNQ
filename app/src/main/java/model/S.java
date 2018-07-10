package model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class S implements Serializable
{

    @SerializedName("bluetooth")
    @Expose
    private Bluetooth bluetooth;
    @SerializedName("wifi")
    @Expose
    private Wifi wifi;
    private final static long serialVersionUID = 5060806384885404989L;

    /**
     * No args constructor for use in serialization
     *
     */
    public S() {
    }

    /**
     *
     * @param wifi
     * @param bluetooth
     */
    public S(Bluetooth bluetooth, Wifi wifi) {
        super();
        this.bluetooth = bluetooth;
        this.wifi = wifi;
    }

    public Bluetooth getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(Bluetooth bluetooth) {
        this.bluetooth = bluetooth;
    }

    public S withBluetooth(Bluetooth bluetooth) {
        this.bluetooth = bluetooth;
        return this;
    }

    public Wifi getWifi() {
        return wifi;
    }

    public void setWifi(Wifi wifi) {
        this.wifi = wifi;
    }

    public S withWifi(Wifi wifi) {
        this.wifi = wifi;
        return this;
    }

}