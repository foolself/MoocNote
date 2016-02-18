package com.example.root.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

/*
 * 1，button 实现点击事件三种方法：
 *         匿名内部类
 *         外部类
 *         接口
 * 2，通过自定义 TextView, 实现跑马灯效果
 * 3，AutoCompleteTextView 使用
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private AutoCompleteTextView acTextView;
    private MultiAutoCompleteTextView macTextView;
    private String[] res = {"beijing", "henan", "hebei", "shanghai", "shandong", "jinan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);


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
         * 4th, 将当前的 adpter 与当前的 AutoCompleteTextView 绑定
         * 5th, 设置分隔符，此处用逗号演示
         */
        macTextView = (MultiAutoCompleteTextView) findViewById(R.id.multitext1);
        macTextView.setAdapter(adapter);
        // set Tokenizer as ","
        macTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
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