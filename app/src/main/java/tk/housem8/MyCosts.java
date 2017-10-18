package tk.housem8;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrador on 07/10/2017.
 */

public class MyCosts extends Fragment {


    ListView listadoCostes;
    View myView;
    ArrayList<String> data = new ArrayList<>();
    Spinner spinner;
    List<String> periodArray;
    String actualPeriod;
    Period p = new Period();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.my_costs, container, false);

        try {

            actualPeriod = p.getPeriodFromDate();
            ArrayList<Date> entreFechas = p.getFirstLastDaysOfPeriod(actualPeriod);
            periodArray = p.fillFullYear(actualPeriod);

            spinner = (Spinner) myView.findViewById(R.id.periodSpinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<String> periodAdapter = new ArrayAdapter<String>(myView.getContext(), android.R.layout.simple_spinner_item, periodArray);

            FloatingActionButton fab = (FloatingActionButton) myView.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new NewCost()).commit();
                }
            });

            // Specify the layout to use when the list of choices appears
            periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(periodAdapter);
            spinner.setSelection(periodArray.indexOf(actualPeriod));
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    actualPeriod = periodArray.get(position);
                    ArrayList<Date> entreFechas = p.getFirstLastDaysOfPeriod(actualPeriod);
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                    SharedPreferences userDetails = myView.getContext().getSharedPreferences("userdetails", myView.getContext().MODE_PRIVATE);
                    try {
                        data = new costsData().execute(new URL(getResources().getString(R.string.urlHousem8rest) + "costRests/search/findByMateAndHouse?mateId="+userDetails.getString("user_id", "")+"&houseId="+userDetails.getString("house_id", "")+"&startDate=" + format.format(entreFechas.get(0)) + "&endDate=" + format.format(entreFechas.get(1)))).get();
                        if (listadoCostes != null) {
                            listadoCostes.setAdapter(null);
                            listadoCostes.clearChoices();
                        }


                        listInflater();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }


            });

            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

            //data = new costsData().execute(new URL(getResources().getString(R.string.urlHousem8rest) + "costRests/search/findByMateAndHouse?mateId=1&houseId=1&startDate=" + format.format(entreFechas.get(0)) + "&endDate=" + format.format(entreFechas.get(1)))).get();
            //listInflater();
            /*if (data.get(0).indexOf("error") != 1) {

                JSONObject obj = null;
                obj = new JSONObject(data.get(0));
                obj = (JSONObject) obj.get("_embedded");
                JSONArray costArray = (JSONArray) obj.get("costRests");
                for (int i = 0; i < costArray.length(); i++) {
                    costes.add(costArray.getJSONObject(i));
                }
            }

            CostAdapter adaptador = new CostAdapter(myView.getContext(), costes);
            listadoCostes = (ListView) myView.findViewById(R.id.costsList);
            listadoCostes.setAdapter(adaptador);
            listadoCostes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getApplicationContext(),datos.get(position).getTitulo(),Toast.LENGTH_SHORT).show();
                    //Intent i=new Intent(getApplicationContext(),Reproducir.class);
                    //i.putExtra("objCancion",datos.get(position));
                    // startActivity(i);
                }
            });
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myView;
    }

    private void listInflater() {
        final ArrayList<JSONObject> costes = new ArrayList<>();
        try {
            if (data.get(0).indexOf("error") != 1) {
                System.out.println(data.get(0).toString());

                JSONObject obj = null;

                obj = new JSONObject(data.get(0));

                obj = (JSONObject) obj.get("_embedded");
                JSONArray costArray = (JSONArray) obj.get("costRests");
                for (int i = 0; i < costArray.length(); i++) {
                    costes.add(costArray.getJSONObject(i));
                }
            }

            CostAdapter adaptador = new CostAdapter(myView.getContext(), costes);
            listadoCostes = (ListView) myView.findViewById(R.id.costsList);
            listadoCostes.setAdapter(adaptador);

            listadoCostes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        Toast.makeText(myView.getContext(), costes.get(position).get("mateId").toString(), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Intent i=new Intent(getApplicationContext(),Reproducir.class);
                    //i.putExtra("objCancion",datos.get(position));
                    // startActivity(i);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private class costsData extends AsyncTask<URL, Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(URL... params) {

            RESTCall call = new RESTCall();
            ArrayList<String> data = new ArrayList<>();

            try {


                data.add(call.urlCall(params[0]));


            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(data);
            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<String> data) {
           /* try {
            if(data.get(0).indexOf("error")!=1) {
                //myHouse = (House) converter.JSONToObject(data.get(0), House.class);
                System.out.println("cData: " + data.get(0));
                JSONObject obj = null;

                obj = new JSONObject(data.get(0));

                //myHouse = new House(obj);
                obj = (JSONObject) obj.get("_embedded");
                JSONArray costArray = (JSONArray) obj.get("costRests");
                for (int i = 0; i < costArray.length(); i++) {
                    System.out.println(costArray.getJSONObject(i));
                    //CostRest cr = new CostRest(costArray.getJSONObject(i));
                    costes.add(costArray.getJSONObject(i));
                }
            }
            } catch (JSONException e) {
                    e.printStackTrace();
                }

*/
        }


            /*
            //Converter converter = Converter.getInstance();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Instanciamos el ListView
            listaCanciones = (ListView)findViewById(R.id.listacanciones);

            datos=new ArrayList<Cancion>();


            Adaptador adaptador=new Adaptador(this,datos);
            listaCanciones.setAdapter(adaptador);
            listaCanciones.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getApplicationContext(),datos.get(position).getTitulo(),Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),Reproducir.class);
                    i.putExtra("objCancion",datos.get(position));
                    startActivity(i);
                }

            });
            */


    }

}
