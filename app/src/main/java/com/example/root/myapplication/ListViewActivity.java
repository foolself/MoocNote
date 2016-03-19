package com.example.root.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-3-15.
 */
public class ListViewActivity extends Activity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private ListView myListView;
    private ArrayAdapter arr_adapter;
    private SimpleAdapter sim_adapter;
    private List<Map<String, Object>> sim_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        myListView = (ListView) findViewById(R.id.listView1);

        // ArrayAdapter
//        String[] arr_data = {"list 1", "list 2", "list 3", "list 4"};
//        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,
//                arr_data);
//        myListView.setAdapter(arr_adapter);

        // SimpleAdapter
        // SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
        sim_data = new ArrayList<Map<String, Object>>();
        init_sim_data();
        sim_adapter = new SimpleAdapter(this, sim_data, R.layout.list_item,
                new String[]{"pic", "text"}, new int[] {R.id.pic, R.id.text});
        myListView.setAdapter(sim_adapter);

        myListView.setOnItemClickListener(this);
        myListView.setOnScrollListener(this);
    }


    private void init_sim_data() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("pic", R.drawable.head);
            listem.put("text", "item " + i);
            sim_data.add(listem);
        }
    }

    // item listener
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String text = myListView.getItemAtPosition(i) + "";
        Toast.makeText(this, text, Toast.LENGTH_SHORT);
        Log.i("tag", "?ok");
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Main", "手指没有离开屏幕，视图正在滑动");
                break;
            case SCROLL_STATE_FLING:
                Log.i("Main", "用户手指离开屏幕之前，由于用力滑了一下，视图依靠惯性继续滑动");
                // 下拉刷新
                Map<String, Object> additem = new HashMap<String, Object>();
                additem.put("pic", R.drawable.head);
                additem.put("text", "add a new item");
                sim_data.add(additem);
                // !!!
                sim_adapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main", "视图已停止滑动");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
}
