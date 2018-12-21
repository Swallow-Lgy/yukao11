package com.example.dell.yukao1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dell.yukao1.R;

public class TitleBar extends LinearLayout {
    private Context mContext;
    public TitleBar(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
       init();
    }

    private void init() {
        View view = View.inflate(mContext, R.layout.titlebar,null);
        final EditText editText = view.findViewById(R.id.titleedit);
        view.findViewById(R.id.titleimage).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monButtonClick!=null){
                    monButtonClick.onButtonClick(editText.getText().toString());
                }
            }
        });
        addView(view);
    }
    //全局变量
    onButtonClick monButtonClick;
    //定义方法
    public void setOnclickLister(onButtonClick onclickLister){
        monButtonClick=onclickLister;

    }
    //接口
     public interface onButtonClick{
        void onButtonClick(String str);
    }
}
