package com.ansid.alertzm.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansid.alertzm.R;

public class SetTrackers extends AppCompatActivity {
    ListView trackerListView;
    String[] trackers = {"Porn Activity Tracker", "Sleep Activity Tracker", "Password History Tracker", "Remind me Tracker"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_trackers);
        trackerListView = (ListView) findViewById(R.id.list_view_setTracker);
        trackerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.template_list_view, trackers));

        trackerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = null;
                if(view!=null) {
                    TextView textView = (TextView) view;
                    name = textView.getText().toString();
                }
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(trackerListView.getContext(), R.style.Theme_AlertzM);
                alertDialog.setTitle("Enable Tracking");
                alertDialog.setCancelable(true);
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                String finalName = name;
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(SetTrackers.this, "TRACKING NOW: "+ finalName, Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setMessage("Would you like to enable "+name+ "tracker ?");
                alertDialog.show();
            }
        });
    }
}
