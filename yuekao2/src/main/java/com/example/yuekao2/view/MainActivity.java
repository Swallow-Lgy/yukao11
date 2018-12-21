package com.example.yuekao2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yuekao2.R;
import com.example.yuekao2.adpter.LeftAdpter;
import com.example.yuekao2.adpter.RigthAdpter;
import com.example.yuekao2.bean.LeftBean;
import com.example.yuekao2.bean.RiagthBean;
import com.example.yuekao2.presenter.IPrersenterImpl;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
      private RecyclerView leftRecycle,rigthRecycle;
      private LeftAdpter leftAdpter;
      private RigthAdpter rigthAdpter;
      private IPrersenterImpl miPrersenter;
      private String urlleft="http://www.zhaoapi.cn/product/getCatagory";
      private String urlRigth="http://www.zhaoapi.cn/product/getProductCatagory";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftRecycle =  findViewById(R.id.leftrecycle);
        rigthRecycle = findViewById(R.id.rigthrecycle);
        leftAdpter = new LeftAdpter(this);
        rigthAdpter = new RigthAdpter(this);
        miPrersenter = new IPrersenterImpl(this);

        initLeft();
        initRigth();
    }



    private void initLeft() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        leftRecycle.setLayoutManager(linearLayoutManager);
        leftRecycle.setAdapter(leftAdpter);
        HashMap<String,String>map = new HashMap<>();
        miPrersenter.requestData(urlleft,map,LeftBean.class);
         leftAdpter.setOnClickListener(new LeftAdpter.onClickListener() {
             @Override
             public void onClick(int position, String cid) {
                 getData(cid);
             }
         });
    }
       public void  getData(String uid){
            HashMap<String,String>map = new HashMap<>();
            map.put("cid",uid);
            miPrersenter.requestData(urlRigth,map,RiagthBean.class);
       }
    private void initRigth() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rigthRecycle.setLayoutManager(linearLayoutManager);
        rigthRecycle.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rigthRecycle.setAdapter(rigthAdpter);

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(Object data) {
       if (data instanceof  LeftBean){
           LeftBean leftBean = (LeftBean) data;
           leftAdpter.setmList(leftBean.getData());
       }
       if (data instanceof  RiagthBean){
           RiagthBean riagthBean = (RiagthBean) data;
           rigthAdpter.setmList(riagthBean.getData());
       }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        miPrersenter.des();
    }


}
