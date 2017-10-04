package tk.housem8;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.Authenticator;
import java.net.PasswordAuthentication;


/**
 * Created by MAB on 14/06/2017.
 */

    public class Parseador {

        String cadenaJSON;

        public String parsea(URL direccion){

            try{
                CookieManager cookieManager = (CookieManager) CookieManager.getDefault();
                cookieManager.getCookieStore().getCookies();
                //System.out.println(cookieManager.getCookieStore().getCookies().size());
                //System.out.println(cookieManager.getCookieStore().getCookies().get(0).getSecure());
               // System.out.println(cookieManager.getCookieStore().getCookies().get(0).getValue());
                //System.out.println(cookieManager.getCookieStore().getCookies().get(0).toString());

                HttpURLConnection conexion= (HttpURLConnection) direccion.openConnection();
                conexion.setRequestProperty("Cookie", cookieManager.getCookieStore().getCookies().get(0).toString()+";");
                //conexion.setRequestMethod("POST");
                System.out.println(conexion.getRequestProperty("Cookie"));
                /*
                connection.setRequestProperty("Cookie",
                TextUtils.join(";",  msCookieManager.getCookieStore().getCookies()));   */
                conexion.setUseCaches(false);
                conexion.connect();
                //System.out.println(conexion.getResponseCode());
                //System.out.println(conexion.getResponseMessage());



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

