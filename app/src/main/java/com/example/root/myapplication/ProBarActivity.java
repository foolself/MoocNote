package com.example.root.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by root on 16-3-20.
 */
public class ProBarActivity extends Activity implements View.OnClickListener {
    private ProgressBar progressBar;
    private Button add;
    private Button sub;
    private Button reset;
    private TextView textView;
    private Button showDialog;
    private int first;
    private int second;
    private int max;
    private ProgressDialog proBarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 启用窗口特征，启动进度条和不带进度的进度条
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_probar);
        // 显示两种进度条
        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
        // Max = 10000
        setProgress(600);

        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        reset = (Button) findViewById(R.id.reset);
        textView = (TextView) findViewById(R.id.textView);
        showDialog = (Button) findViewById(R.id.showDialog);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        reset.setOnClickListener(this);
        showDialog.setOnClickListener(this);

        first = progressBar.getProgress();
        second = progressBar.getSecondaryProgress();
        max = progressBar.getMax();
        textView.setText("First Progress: " + (int) (first / (float) max * 100) + "%" + "\n"
                + "Second Progress: " + (int) (second / (float) max * 100) + "%");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(10);
                break;
            case R.id.sub:
                progressBar.incrementProgressBy(-10);
                progressBar.incrementSecondaryProgressBy(-10);
                break;
            case R.id.reset:
                progressBar.setProgress(30);
                progressBar.setSecondaryProgress(50);
                break;
            case R.id.showDialog:
                proBarDialog = new ProgressDialog(ProBarActivity.this);
                // set style
                proBarDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                proBarDialog.setTitle("ProBar Dialog");
                proBarDialog.setIcon(R.drawable.head);
                proBarDialog.setMessage("Welcome~");
                // set proBar
                proBarDialog.setMax(100);
                proBarDialog.incrementProgressBy(50);
                proBarDialog.setIndeterminate(false);
                // set button
                proBarDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ProBarActivity.this, "Welcome~.~", Toast.LENGTH_SHORT).show();
                    }
                });
                // set cancel button
                proBarDialog.setCancelable(true);
                proBarDialog.show();
                break;
        }
        first = progressBar.getProgress();
        second = progressBar.getSecondaryProgress();
        max = progressBar.getMax();
        textView.setText("First Progress: " + (int)(first / (float)max * 100) + "%" + "\n"
                    + "Second Progress: " + (int)(second / (float)max * 100) + "%" );
    }

}
