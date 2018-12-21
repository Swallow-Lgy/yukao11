package com.example.dell.yukao1.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dell.yukao1.R;
import com.example.dell.yukao1.view.LayoutViewGroup;
import com.example.dell.yukao1.view.TitleBar;

import org.w3c.dom.Text;

public class LoaginActivity extends AppCompatActivity {
     private LayoutViewGroup layoutViewGroup1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loagin);
       layoutViewGroup1 =  findViewById(R.id.layout1);
        TitleBar titleBar = findViewById(R.id.titlebar);
        titleBar.setOnclickLister(new TitleBar.onButtonClick() {
            @Override
            public void onButtonClick(String str) {
                TextView tv = new TextView(LoaginActivity.this);
                 tv.setText(str);
                 tv.setTextColor(Color.RED);
                 tv.setTextSize(20);
                 tv.setBackgroundResource(R.drawable.text_bg);
                 layoutViewGroup1.addView(tv);
            }
        });
    }
}
