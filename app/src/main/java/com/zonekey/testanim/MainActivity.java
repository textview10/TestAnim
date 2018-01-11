package com.zonekey.testanim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zonekey.testanim.view.LeftPopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_test1,iv_test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_test1 =  findViewById(R.id.iv_test1);
        iv_test2 =  findViewById(R.id.iv_test2);
        iv_test1.setOnClickListener(this);
        iv_test2.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.iv_test1:
                LeftPopupWindow leftPopupWindow = new LeftPopupWindow(MainActivity.this,iv_test1);
                leftPopupWindow.show(MainActivity.this);
                break;
            case R.id.iv_test2:

                break;
        }
    }

}
