package com.example.root.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16-3-19.
 */
public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener{
    private Spinner spinner;
    private TextView textView;
    private List<String> list;
    private ArrayAdapter<String> adapter;
    // if you want SimpleAdapter, look up the example of ListView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        textView = (TextView) findViewById(R.id.textView);
        spinner = (Spinner) findViewById(R.id.mySpinner);

        list = new ArrayList<String>();
        textView.setText("You selected: Beijing");
        dataInit();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    private void dataInit() {
        list.add("Beijing");
        list.add("Shanghai");
        list.add("Nanjing");
        list.add("Guangzhou");
        list.add("Qingdao");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String cityName = adapter.getItem(i); //or use list(i);
        textView.setText("You selected: " + cityName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
