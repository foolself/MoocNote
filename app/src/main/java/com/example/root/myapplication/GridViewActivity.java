package com.example.root.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-3-19.
 */
public class GridViewActivity extends Activity implements AdapterView.OnItemClickListener{
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private int[] icons = {R.drawable.bag_64px, R.drawable.book_64px,
            R.drawable.bookshelf_64px, R.drawable.browser_64px,
            R.drawable.clock_64px, R.drawable.cloud_64px,
            R.drawable.compass_64px, R.drawable.compose_64px,
            R.drawable.download_64px, R.drawable.folder_64px,
            R.drawable.gear_64px, R.drawable.global_64px
    };
    private String[] iconName = {"bag", "book", "bookshelf", "browser",
            "clock", "cloud", "compass", "compose", "download", "folder",
            "gear", "global"
    };

    private SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        gridView = (GridView) findViewById(R.id.myGridView);

        // 1, data source
        // 2, simpleAdapter
        // 3, set adapter
        // 4, set listener

        dataList = new ArrayList<Map<String, Object>>();
//        getData();
        adapter = new SimpleAdapter(this, getData(), R.layout.gridview_item, new String[]{"pic", "text"}, new int[]{R.id.pic1, R.id.text1});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", icons[i]);
            map.put("text", iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(GridViewActivity.this, "clicked:" + iconName[i], Toast.LENGTH_SHORT).show();
    }
}
