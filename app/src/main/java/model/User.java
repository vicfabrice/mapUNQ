package model;

import android.content.Context;

import java.io.FileOutputStream;
import java.util.UUID;



public class User {

    String username;

    public String getUsername(){

        //obtener GUID
        String uniqueID = UUID.randomUUID().toString();
        username = uniqueID;
        return username;
    }

    public void saveUsernameInInternalMemory(){
        String FILENAME = "username";
        String username = this.getUsername();

        //FileOutputStream fos = getApplicationContext().openFileOutput(filename, getActivity().MODE_PRIVATE);
        //FileOutputStream fos = openFileOutput("username", Context.MODE_PRIVATE);
        //fos.write(username.getBytes());
       // fos.close();
    }
}
