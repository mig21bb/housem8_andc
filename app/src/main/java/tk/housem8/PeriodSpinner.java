package tk.housem8;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Administrador on 13/10/2017.
 */

public class PeriodSpinner extends Activity implements AdapterView.OnItemSelectedListener {


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
