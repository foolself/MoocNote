package com.example.root.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/*
 * 1, button 实现点击事件三种方法：
 *         匿名内部类
 *         外部类
 *         接口
 * 2, 通过自定义 TextView, 实现跑马灯效果
 * 3, AutoCompleteTextView 使用
 * 4, ToggleButton 按钮实现开关效果
 * 5, CheckBox
 * 6, LinearLayout 外嵌套 ScrollView, 实现垂直滚动
 * 7, RadioGroup
 * 8, Two modes to start other activity
 * 9, ListView
 * 10, DatePicker, TimePicker
 * 11, GridView
 * 12, Spinner
 * 13, ProgressBar
 * 14, WebView
 */

//public class MainActivity extends AppCompatActivity implements View.OnClickListener{
public class MainActivity extends Activity implements View.OnClickListener{
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private AutoCompleteTextView acTextView;
    private MultiAutoCompleteTextView macTextView;
    private String[] res = {"beijing", "henan", "hebei", "shanghai", "shandong", "jinan"};

    private ToggleButton tobt;
    private ImageView imageView;

    private CheckBox checkBox1;
    private CheckBox checkBox2;

    private RadioGroup rg;

    private Button bt_activity_1;
    private Button bt_activity_2;
    private TextView textView_result;

    private Button showListView;
    private Button showDateView;
    private Button showGridView;
    private Button showSpinner;
    private Button showProBar;
    private Button showWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);

        tobt = (ToggleButton) findViewById(R.id.togbt1);
        imageView = (ImageView) findViewById(R.id.image1);

        //OnClickListener by inner class
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "ok, bt1 onclicked", 1).show();
            }
        });

        //OnClickListener by outer class
        bt2.setOnClickListener(new MyOnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Toast.makeText(MainActivity.this, "ok, bt2 onclicked", 2).show();
            }
        });

        //OnClickListener by implements
        bt3.setOnClickListener(this);

        /* AutoCompleteTextView STEP
         * 1st, 初始化控件
         * 2nd, 需要一个适配器
         * 3th, 初始化数据源
         * 4th, 将当前的 adpter 与当前的 AutoCompleteTextView 绑定
         */
        acTextView = (AutoCompleteTextView) findViewById(R.id.autotext1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, res);
        acTextView.setAdapter(adapter);

        /* MultiAutoCompleteTextView STEP
         * 1st, 初始化控件
         * 2nd, 需要一个适配器
         * 3th, 初始化数据源
         * 4th, 将当前的 adapter 与当前的 AutoCompleteTextView 绑定
         * 5th, 设置分隔符，此处用逗号演示
         */
        macTextView = (MultiAutoCompleteTextView) findViewById(R.id.multitext1);
        macTextView.setAdapter(adapter);
        // set Tokenizer as ","
        macTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        //ToggleButton set listener
        tobt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                tobt.setChecked(b);
                imageView.setImageResource(b ? R.drawable.lighton : R.drawable.lightoff);
            }
        });

        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.i("tag", "the football box checked");
            }
        });

        //RadioGroup
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioBt0:
                        Log.i("tag", "the male checked");
                        break;
                    case R.id.radioBt1:
                        Log.i("tag", "the female checked");
                        break;
                }
            }
        });

        bt_activity_1 = (Button) findViewById(R.id.bt_activity_1);
        bt_activity_2 = (Button) findViewById(R.id.bt_activity_2);
        textView_result = (TextView) findViewById(R.id.result_content);
        bt_activity_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        bt_activity_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        // ListView
        showListView = (Button) findViewById(R.id.showListView);
        showListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        // DateView: datePicker, timePicker
        showDateView = (Button) findViewById(R.id.showDateView);
        showDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DateActivity.class);
                startActivity(intent);
            }
        });

        // GridView
        showGridView = (Button) findViewById(R.id.showGridView);
        showGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
                startActivity(intent);
            }
        });

        // Spinner
        showSpinner = (Button) findViewById(R.id.showSpinner);
        showSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                startActivity(intent);
            }
        });

        // ProBar
        showProBar = (Button) findViewById(R.id.showProBar);
        showProBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProBarActivity.class);
                startActivity(intent);
            }
        });

        // WebView
        showWebView = (Button) findViewById(R.id.showWebView);
        showWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            String result = data.getStringExtra("data");
            textView_result.setText(result);
        }
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "ok, bt3 onclicked", 3).show();
    }
}

class MyOnClickListener implements View.OnClickListener {
    private int count = 0;
    @Override
    public void onClick(View v) {
        if (count % 2 == 0) {
            v.setAlpha(0.5f);
        }
        else v.setAlpha(1.0f);
        count++;
    }
}
