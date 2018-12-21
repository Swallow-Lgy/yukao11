package com.example.dell.yukao1.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dell.yukao1.R;
import com.example.dell.yukao1.adpter.ProductsAdpter;
import com.example.dell.yukao1.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class CustomCunterView  extends RelativeLayout implements View.OnClickListener {
   private EditText editCar;
    public CustomCunterView(Context context) {
        super(context);
        init(context);
    }



    public CustomCunterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomCunterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private Context context;
    private void init(Context context) {
         this.context = context;
       View view = View.inflate(context, R.layout.goods_price,null);
        ImageView addImage  = view.findViewById(R.id.add);
        ImageView jianImage=view.findViewById(R.id.jian);
         editCar =  view.findViewById(R.id.edit_shop_car);
        addImage.setOnClickListener(this);
        jianImage.setOnClickListener(this);
        addView(view);
        editCar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = s.toString();
                 num = Integer.valueOf(string);
                 list.get(position).setNum(num);
                if (mcallBackLister!=null){
                     mcallBackLister.callBack();
                 }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private int num;
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.add:
                //数据加
                num++;
                //ui设置
                editCar.setText(num+"");
                //bean类里设置
                list.get(position).setNum(num);
                //发送接口
                 mcallBackLister.callBack();
                 productsAdpter.notifyDataSetChanged();
                 break;
            case R.id.jian:
                if (num>1){
                    num--;
                }
                else {
                    Toast.makeText(context,"我是有底线的",Toast.LENGTH_SHORT).show();
                }
                editCar.setText(num+"");
                list.get(position).setNum(num);
                mcallBackLister.callBack();
                productsAdpter.notifyDataSetChanged();
                break;
                 default:
                     break;
        }
    }
    //传递的数据
    private List<ShopBean.DataBean.ListBean> list = new ArrayList<>();
   //下标
    private int position;
    //适配器
    ProductsAdpter productsAdpter;
    public void setData(ProductsAdpter productsAdpter,List<ShopBean.DataBean.ListBean>list,int i){
           this.list = list;
           this.productsAdpter = productsAdpter;
           position=i;
           num=list.get(i).getNum();
           editCar.setText(num+"");
    }
    //全局变量
    CallBackLister mcallBackLister;
    //定义方法
    public void setOnClickLister(CallBackLister clickLister){
        this.mcallBackLister = clickLister;
    }
    //定义接口
    public interface  CallBackLister{
        void callBack();
    }
}
