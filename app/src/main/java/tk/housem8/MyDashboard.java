package tk.housem8;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import tk.housem8.Entities.House;

public class MyDashboard extends Fragment {

        View myView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            myView = inflater.inflate(R.layout.my_dashboard,container,false);
            return myView;
        }

}
