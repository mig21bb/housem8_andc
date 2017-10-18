package tk.housem8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrador on 10/10/2017.
 */

public class DashboardAdapter extends ArrayAdapter {

    protected Context contexto;
    ArrayList<JSONObject> datos;

    public DashboardAdapter(Context c, ArrayList<JSONObject> datos){
        super(c,R.layout.cost,datos);

        this.contexto = c;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflador= LayoutInflater.from(contexto);

        //El metodo inflate me devuelve la vista inflada
        convertView=inflador.inflate(R.layout.cost,null);
        try {
        //Accedo a traves de la posicion del array al objeto
        JSONObject cost=datos.get(position);

        ImageView commerceLogo=(ImageView)convertView.findViewById(R.id.commerceLogo);
        Picasso.with(contexto).load(cost.getString("commerceLogo")).into(commerceLogo);

        TextView concepto=(TextView)convertView.findViewById(R.id.concepto);
            concepto.setText((String) cost.get("description").toString());

            TextView fecha=(TextView)convertView.findViewById(R.id.fecha);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date datetime = new Date();
            try {
                datetime = format.parse(cost.get("datetime").toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            fecha.setText(format.format(datetime));

        TextView commerceName=(TextView)convertView.findViewById(R.id.commerceName);
            commerceName.setText((String) cost.get("commerceName").toString());

        TextView amount=(TextView)convertView.findViewById(R.id.amount);
            amount.setText( cost.get("amount").toString()+" €");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
