package com.example.dell.yukao1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dell.yukao1.R;
import com.example.dell.yukao1.adpter.ShopAdpter;
import com.example.dell.yukao1.bean.ShopBean;
import com.example.dell.yukao1.presenter.IPermentImpl;
import com.example.dell.yukao1.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
     private ShopAdpter mshopAdpter;
     private CheckBox mclicle;
     private List<ShopBean.DataBean> mList = new ArrayList<>();
     private TextView mAllPriceTex,mSumPrice;
     private IPermentImpl miPerment;
     private String url="http://www.zhaoapi.cn/product/getCarts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         miPerment = new IPermentImpl(this);
         mclicle =  findViewById(R.id.iv_cricle);
         mclicle.setOnClickListener(this);
         mAllPriceTex = findViewById(R.id.all_price);
         mSumPrice  = findViewById(R.id.sum_price);
         getData();
         initView();
    }

    private void initView() {
        RecyclerView mrecyclerView = findViewById(R.id.recy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(linearLayoutManager);
        mshopAdpter = new ShopAdpter(this);
        mrecyclerView.setAdapter(mshopAdpter);
        mshopAdpter.setLister(new ShopAdpter.ShopCallBackLister() {
            @Override
            public void callback(List<ShopBean.DataBean> list) {
               double totallPrice =0;
               int num=0;
               int totalNum=0;
               for (int a=0;a<list.size();a++){
                   List<ShopBean.DataBean.ListBean> listAll = list.get(a).getList();
                 for (int i=0;i<listAll.size();i++){
                     totalNum = totalNum+listAll.get(i).getNum();
                     if (listAll.get(i).isCheck()){
                         totallPrice = totallPrice+(listAll.get(i).getPrice()*listAll.get(i).getNum());
                          num = num+listAll.get(i).getNum();
                     }
                 }
               }
               if (num<totalNum){
                    mclicle.setChecked(false);
               }
               else {
                   mclicle.setChecked(true);
               }
               mAllPriceTex.setText("合计:"+totallPrice);
               mSumPrice .setText("去结算("+num+")");
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.iv_cricle:
            checkSeller(mclicle.isChecked());
            mshopAdpter.notifyDataSetChanged();
            break;
            default:
                break;
        }
    }
    //全选框的选中和不选中的继承
    public void checkSeller(boolean bool){
        double totaPrice = 0;
        int num=0;
        for (int a=0;a<mList.size();a++){
            //遍历商家
            ShopBean.DataBean dataBean  =  mList.get(a);
            dataBean.setCheck(bool);
            List<ShopBean.DataBean.ListBean> listAll = mList.get(a).getList();
            for (int i=0;i<listAll.size();i++){
                //遍历商品改变状态
                listAll.get(i).setCheck(bool);
                totaPrice = totaPrice+(listAll.get(i).getPrice()*listAll.get(i).getNum());
                num = num+listAll.get(i).getNum();
            }
        }
        if (bool){
            mAllPriceTex.setText("合计:"+totaPrice);
            mSumPrice .setText("去结算("+num+")");
        }
        else {
            mAllPriceTex.setText("合计:0.0000");
            mSumPrice .setText("去结算(0)");
        }
    }
    public  void getData(){
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",99+"");
        miPerment.requestData(url,map,ShopBean.class);
        }
    @Override
    public void success(Object data) {
        if (data instanceof  ShopBean){
            ShopBean shopBean  = (ShopBean) data;
            mList = shopBean.getData();
            if (mList!=null){
                mList.remove(0);
                mshopAdpter.setmList(mList);
            }
        }
    }
}
