package com.example.dell.yukao1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.dell.yukao1.R;

@SuppressLint("AppCompatCustomView")
public class TextAttrs extends TextView {
    public TextAttrs(Context context) {
        super(context);
    }

    public TextAttrs(Context context,  AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextAttrs);
        int color = typedArray.getColor(R.styleable.TextAttrs_textColor, Color.BLACK);
        setTextColor(color);
    }

    public TextAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
