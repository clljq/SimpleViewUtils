package com.leech.viewinjectutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 这是对ViewUtils框架的测试
 * */
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.tv)
    private TextView textView;

    @ViewInject(R.id.btn1)
    private Button button1;

    @ViewInject(R.id.btn2)
    private Button button2;

    @ViewInject(R.id.btn3)
    private Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开始注入
        ViewUtils.inject(this);
        Log.i("test", (String) textView.getText());
    }

    @ClickInject({R.id.btn1,R.id.btn2,R.id.btn3})
    private void click(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Toast.makeText(MainActivity.this, ((Button)view).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(MainActivity.this, ((Button)view).getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn3:
                Toast.makeText(MainActivity.this, ((Button)view).getText(), Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
