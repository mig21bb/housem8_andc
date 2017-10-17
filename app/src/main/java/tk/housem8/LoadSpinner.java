package tk.housem8;

import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tk.housem8.Entities.Commerce;
import tk.housem8.Entities.CostFamily;

/**
 * Created by Administrador on 16/10/2017.
 */

public class LoadSpinner extends AsyncTask<URL, Void, ArrayList<String>> {

        private View myView;
        private String className;
        private Spinner spinnerView;

    public Spinner getSpinnerView() {
        return spinnerView;
    }

    public void setSpinnerView(Spinner spinnerView) {
        this.spinnerView = spinnerView;
    }

    LoadSpinner(View myView, String className, Spinner spinnerView){
            this.myView=myView;
            this.className=className;
            this.spinnerView = spinnerView;
        }

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
            try {
                if(data.get(0).indexOf("error")!=1) {
                    //myHouse = (House) converter.JSONToObject(data.get(0), House.class);
                    System.out.println("cData: " + data.get(0));
                    JSONObject obj = null;
                    Class<?> spinnerClass = Class.forName(className);
                    List<Object> jArray = new ArrayList<>();
                    Constructor ctor = spinnerClass.getConstructor(JSONObject.class);

                    obj = new JSONObject(data.get(0));

                    //myHouse = new House(obj);
                    obj = (JSONObject) obj.get("_embedded");
                    Iterator<String> keys = obj.keys();
                    // get some_name_i_wont_know in str_Name
                    String str_Name=keys.next();
                    JSONArray jasonArray = (JSONArray) obj.get(str_Name);
                    for (int i = 0; i < jasonArray.length(); i++) {
                        System.out.println(jasonArray.getJSONObject(i));
                        jArray.add(ctor.newInstance(jasonArray.getJSONObject(i)));
                    }
                    Spinner spinner =  spinnerView;
                    // Create an ArrayAdapter using the string array and a default spinner layout

                    ArrayAdapter<?> adapter = new ArrayAdapter<>(myView.getContext(), android.R.layout.simple_spinner_item, jArray);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Apply the adapter to the spinner
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            //newCost.set;

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }


                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
}
