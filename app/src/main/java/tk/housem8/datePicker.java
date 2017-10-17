package tk.housem8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Administrador on 26/06/2016.
 */
public class datePicker extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_picker);
        Log.d("MyKm","MA-on date_picker");

    }

    public void sendDate(View view){
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar date = new GregorianCalendar(year,month,day);
        String dateS = DateFormat.getDateInstance().format(date);

        Intent intent = new Intent();
        intent.putExtra("date",dateS);
        setResult(RESULT_OK,intent);
        Log.d("MyKm","MA-date picker return date="+dateS);
        finish();

    }
}
