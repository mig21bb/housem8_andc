package tk.housem8;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.SimpleFormatter;

import tk.housem8.Entities.Commerce;
import tk.housem8.Entities.Cost;
import tk.housem8.Entities.CostFamily;
import tk.housem8.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrador on 07/10/2017.
 */

public class NewCost extends Fragment {

    View myView;
    List<CostFamily> costFamilies = new ArrayList<>();//costFamilies/search/findAllActive
    Cost newCost = new Cost();
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;
    TextView dateView;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.new_cost,container,false);
        dateView = (TextView) myView.findViewById(R.id.dateButton);
        dateView.setText(sdf.format(myCalendar.getTime()));

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(myView.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        try {
            AsyncTask<URL, Void, ArrayList<String>> loadCostFamilies = new LoadSpinner(myView,"tk.housem8.Entities.CostFamily", (Spinner) myView.findViewById(R.id.costFamilySpinner));
            loadCostFamilies.execute(new URL(getResources().getString(R.string.urlHousem8rest) + "costFamilies/search/findAllActive"));
            AsyncTask<URL, Void, ArrayList<String>> loadCommerces = new LoadSpinner(myView,"tk.housem8.Entities.Commerce", (Spinner) myView.findViewById(R.id.spinnerCommerce));
            loadCommerces.execute(new URL(getResources().getString(R.string.urlHousem8rest) + "commerces/search/findAllActive"));

        } catch (MalformedURLException e) {
            e.printStackTrace();

        }

        Button save = (Button) myView.findViewById(R.id.saveCost);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveCost();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        Button back = (Button) myView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMyCosts();
            }
        });

    return myView;

    }

    private  void saveCost() throws ParseException {
        Cost c = new Cost();
        Spinner costFamilySpinner = (Spinner) myView.findViewById(R.id.costFamilySpinner);
        CostFamily cf = (CostFamily) costFamilySpinner.getSelectedItem();
        c.setCostFamily(myView.getContext().getResources().getString(R.string.urlHousem8rest),cf.getId());
        Spinner commerceSpinner = (Spinner) myView.findViewById(R.id.spinnerCommerce);
        Commerce sc = (Commerce) commerceSpinner.getSelectedItem();
        c.setCommerce(myView.getContext().getResources().getString(R.string.urlHousem8rest),sc.getId());
        SharedPreferences userDetails = myView.getContext().getSharedPreferences("userdetails", myView.getContext().MODE_PRIVATE);
        if(userDetails.contains("user_id") && userDetails.contains("house_id")) {
            String mateId = userDetails.getString("user_id", "");
            //System.out.println("usermail:"+usermail);
            String houseId = userDetails.getString("house_id", "");
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat RESTformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            c.setFechaCreacion(calendar.getTime());
            TextView fecha = (TextView) myView.findViewById(R.id.dateButton);
            Date costDate = inputFormat.parse((String) fecha.getText());
            calendar.setTime(costDate);
            c.setDatetime(costDate);
            //c.setDatetime(calendar.getTime());
            EditText desc = (EditText) myView.findViewById(R.id.description);
            c.setDescription(String.valueOf(desc.getText()));
            c.setActivo(true);
            Gson gson = new Gson();
            String json = gson.toJson(c);
            System.out.println(json);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new MyCosts()).commit();
        } catch (ParseException e) {
        e.printStackTrace();
    }
    }

    private void backToMyCosts(){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new MyCosts()).commit();
    }

    private void updateLabel() {


        dateView.setText(sdf.format(myCalendar.getTime()));
    }






}
