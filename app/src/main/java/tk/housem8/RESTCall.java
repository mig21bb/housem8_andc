package tk.housem8;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2017.
 */

public class RESTCall {


    protected String urlCall(URL direccion) {


        String cadenaJSON;

        try{
            CookieManager cookieManager = (CookieManager) CookieManager.getDefault();
            cookieManager.getCookieStore().getCookies();

            HttpURLConnection conexion= (HttpURLConnection) direccion.openConnection();
            conexion.setRequestProperty("Cookie", cookieManager.getCookieStore().getCookies().get(0).toString()+";");
            //conexion.setRequestMethod("POST");
            System.out.println(conexion.getRequestProperty("Cookie"));
                /*
                connection.setRequestProperty("Cookie",
                TextUtils.join(";",  msCookieManager.getCookieStore().getCookies()));   */
            conexion.setUseCaches(false);
            conexion.connect();
            System.out.println(conexion.getResponseCode());
            System.out.println(conexion.getResponseMessage());

            InputStream entrada=conexion.getInputStream();

            BufferedReader lector=new BufferedReader(new InputStreamReader(entrada));

            StringBuilder cadena=new StringBuilder();
            String linea="";

            while((linea=lector.readLine())!=null){
                cadena.append(linea);
            }

            cadenaJSON=cadena.toString();
            System.out.println(cadena.toString());

        }catch(Exception e){
            e.printStackTrace();
            cadenaJSON="{error: "+e.getMessage()+",cause: "+e.getCause()+" }";
        }

        return cadenaJSON;
    }

}
