package tk.housem8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.security.MessageDigest;
import java.util.concurrent.ExecutionException;

import tk.housem8.Entities.Mate;

public class MainActivity extends AppCompatActivity {

    Button entryButton;
    Button logInButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences userDetails = getSharedPreferences("userdetails", MODE_PRIVATE);
        if(userDetails.contains("usermail") && userDetails.contains("password")){
            String usermail = userDetails.getString("usermail","");
            //System.out.println("usermail:"+usermail);
            String password = userDetails.getString("password","");
            //System.out.println("password:"+password);
            UserLoginTask loginTask = new UserLoginTask(getApplicationContext(),usermail,password);
            try {
                Boolean loginSuccess = loginTask.execute().get();
                if(loginSuccess){
                    Intent i = new Intent(getApplicationContext(), activity_navigation.class);
                    startActivity(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        // asociamos el botón entrar a la variable entryButton
        entryButton= (Button) findViewById(R.id.entryButton);

        logInButton= (Button) findViewById(R.id.loginButton);




        // Evento onclick
        entryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), activity_navigation.class);
                startActivity(i);
            }
        });

        // Evento onclick
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);



    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



}


