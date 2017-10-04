package tk.housem8;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import tk.housem8.Entities.Mate;

/**
 * Created by Administrador on 03/10/2017.
 */

public class Converter {

    private static Converter instance = null;
    protected Converter() {
        // Exists only to defeat instantiation.
    }
    public static Converter getInstance() {
        if(instance == null) {
            instance = new Converter();
        }
        return instance;
    }

    public Object JSONToObject(String input, Class objectClass) throws IOException {


        Gson gson = new Gson();
        Class obj = gson.fromJson(input, objectClass.getClass());
        //System.out.println(obj.getId());

        return obj;

    }

}
