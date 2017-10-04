package tk.housem8;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tk.housem8.Entities.House;

public class HouseInfo extends Activity {


    TextView responseText;
    List<String> data = new ArrayList<>();
    ListView homeData;
    House myHouse = new House();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info);

        //homeData=(ListView)findViewById(R.id.homeData);
        //responseText=(TextView)findViewById(R.id.responseText);
        try{
            new houseData().execute(new URL(getResources().getString(R.string.urlHousem8rest)+"houses/search/findByMate?mateId=1"));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private class houseData extends AsyncTask<URL,Void,ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(URL... params) {

            RESTCall call =  new RESTCall();
            ArrayList<String> data = new ArrayList<>();

            try{
                data.add(call.urlCall(params[0]));


            }catch(Exception e){
                e.printStackTrace();
            }

            System.out.println(data);
            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<String> data) {

            //Converter converter = Converter.getInstance();

            try {
                if(data.get(0).indexOf("error")!=1) {
                    //myHouse = (House) converter.JSONToObject(data.get(0), House.class);
                    JSONObject obj = new JSONObject(data.get(0));
                    myHouse = new House(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            EditText pais= (EditText) findViewById(R.id.country);
            pais.setText(myHouse.getCountry());
            pais.setEnabled(false);
            EditText ciudad= (EditText) findViewById(R.id.city);
            ciudad.setText(myHouse.getCity());
            ciudad.setEnabled(false);
            EditText calle= (EditText) findViewById(R.id.street);
            calle.setText(myHouse.getStreet());
            calle.setEnabled(false);
            EditText numero= (EditText) findViewById(R.id.number);
            numero.setText(myHouse.getNumber().toString());
            numero.setEnabled(false);
            EditText planta= (EditText) findViewById(R.id.floor);
            planta.setText(myHouse.getFloor().toString());
            planta.setEnabled(false);
            EditText cp= (EditText) findViewById(R.id.postalCode);
            cp.setText(myHouse.getCp().toString());
            cp.setEnabled(false);
            EditText door= (EditText) findViewById(R.id.door);
            door.setText(myHouse.getApartment().toString());
            door.setEnabled(false);


        }
    }
}
