package tk.housem8;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.provider.Settings;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.security.MessageDigest;

import tk.housem8.Entities.House;
import tk.housem8.Entities.Mate;

/**
 * Created by Administrador on 10/10/2017.
 */
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final Context context;
        public Boolean loginSuccess;


        UserLoginTask(Context context,String email, String password) {
            this.mEmail = email;
            this.mPassword = password;
            this.context = context;
        }



        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            Boolean response = false;

            try {
                String urlText = context.getString(R.string.urlHousem8rest);
                System.out.println(urlText);
                URL url = new URL(urlText);
                CookieManager cookieManager = new CookieManager();
                CookieHandler.setDefault(cookieManager);
                Authenticator.setDefault(new Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mEmail,mPassword.toCharArray());
                    }});
                HttpURLConnection conexion= (HttpURLConnection) url.openConnection();
                System.out.println(cookieManager.getCookieStore().getCookies().size());
                if(cookieManager.getCookieStore().getCookies().size()>0){
                    System.out.println(cookieManager.getCookieStore().getCookies().get(0).toString());
                }

                conexion.setUseCaches(false);
                conexion.connect();
                System.out.println(conexion.getResponseCode());
                if(conexion.getResponseCode()==200){
                    SharedPreferences userDetails = context.getSharedPreferences("userdetails", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = userDetails.edit();
                    edit.clear();
                    edit.putString("usermail", mEmail);
                    edit.putString("password", String.valueOf(mPassword.toCharArray()));

                    conexion.disconnect();
                    response = true;
                    url = new URL(urlText+"mates/search/findByEmail?email="+mEmail);
                    conexion =(HttpURLConnection) url.openConnection();
                    conexion.setRequestProperty("Cookie", cookieManager.getCookieStore().getCookies().get(0).toString()+";");
                    //conexion.setUseCaches(false);
                    conexion.connect();
                    Mate user = new Mate();
                    if(conexion.getResponseCode()==200){
                        user = (Mate) responseToObject(conexion.getInputStream(),"tk.housem8.Entities.Mate");
                        System.out.println("user_id ="+user.getId());
                        edit.putString("user_id", user.getId().toString());
                    }
                    if(cookieManager.getCookieStore().getCookies().size()>0) {
                        System.out.println(cookieManager.getCookieStore().getCookies().get(0).toString());

                    }

                    conexion.disconnect();
                    if(user.getId() != null) {
                        url = new URL(urlText + "houses/search/findByMate?mateId=" + user.getId());
                        conexion = (HttpURLConnection) url.openConnection();
                        conexion.setRequestProperty("Cookie", cookieManager.getCookieStore().getCookies().get(0).toString() + ";");
                        //conexion.setUseCaches(false);
                        conexion.connect();
                        if (conexion.getResponseCode() == 200) {
                            House myHome = (House) responseToObject(conexion.getInputStream(),"tk.housem8.Entities.House");
                            edit.putString("house_id", myHome.getId().toString());
                        }
                    }
                    edit.commit();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

/*
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }
*/
            // TODO: register the new account here.

            loginSuccess=response;
            return response;
        }

        public Boolean getLoginSuccess() {
            return loginSuccess;
        }

        public void setLoginSuccess(Boolean loginSuccess) {
            this.loginSuccess = loginSuccess;
        }

        public static final Object responseToObject(InputStream input, String entityClass) throws IOException, ClassNotFoundException {

            InputStream entrada=input;

            BufferedReader lector=new BufferedReader(new InputStreamReader(entrada));

            StringBuilder cadena=new StringBuilder();
            String linea="";

            while((linea=lector.readLine())!=null){
                cadena.append(linea);
            }
            Class<?> eClass = Class.forName(entityClass);

            String cadenaJSON=cadena.toString();

            Gson gson = new Gson();
            Object obj = gson.fromJson(cadenaJSON, eClass);


            return obj;

        }
}
