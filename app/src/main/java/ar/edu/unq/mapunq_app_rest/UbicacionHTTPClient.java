package ar.edu.unq.mapunq_app_rest;


import model.Analysis;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface UbicacionHTTPClient {

    @GET("/api/v1/location/unq/{us}")
    //@GET("/api/v1/location_basic/unq/mobile")
    Call<Analysis> getAnalysis(@Path("us")String us); //getUser(@Path("username") String username);
}
