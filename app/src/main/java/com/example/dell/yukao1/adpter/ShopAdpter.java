package com.example.dell.yukao1.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.dell.yukao1.R;
import com.example.dell.yukao1.bean.ShopBean;

import java.util.ArrayList;
import java.util.List;

public class ShopAdpter extends RecyclerView.Adapter<ShopAdpter.MyViewHolder> {
  private List<ShopBean.DataBean> mList = new ArrayList<>();
  private Context mContext;

    public ShopAdpter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmList(List<ShopBean.DataBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext,R.layout.item_shopcar,null);
       MyViewHolder myViewHolder = new MyViewHolder(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
              //设置商家的名字
        myViewHolder.mSellerName.setText(mList.get(i).getSellerName());
        //商品
        final ProductsAdpter productsAdpter = new ProductsAdpter(mContext,mList.get(i).getList());
        myViewHolder.mCheck.setChecked(mList.get(i).isCheck());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        myViewHolder.mrecyclerView.setLayoutManager(linearLayoutManager);

        myViewHolder.mrecyclerView.setAdapter(productsAdpter);

        productsAdpter.setListenter(new ProductsAdpter.ShopCallBackLister() {
            @Override
            public void callBack() {
                 if (mshopCallBackLister!=null){
                     mshopCallBackLister.callback(mList);
                 }
                List<ShopBean.DataBean.ListBean> listBeans  = mList.get(i).getList();
                boolean isAllChecked = true;
                for(ShopBean.DataBean.ListBean bean : listBeans){
                   if (!bean.isCheck()){
                       isAllChecked=false;
                       break;
                   }
                }
                myViewHolder.mCheck.setChecked(isAllChecked);
                mList.get(i).setCheck(isAllChecked);
            }

        });
        //监听checkbox的点击事件
        //目的是改变旗下所有商品的选中的状态
        myViewHolder.mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先改变自己的标志位
                mList.get(i).setCheck(myViewHolder.mCheck.isChecked());
               //调用产品adpter的方法
                productsAdpter.selectOrRemoveAll(myViewHolder.mCheck.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
      RecyclerView mrecyclerView;
      TextView mSellerName;
        CheckBox mCheck;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mSellerName = itemView.findViewById(R.id.tv_shop);
             mCheck =  itemView.findViewById(R.id.check_shop);
            mrecyclerView  = itemView.findViewById(R.id.recy_shop);
        }
    }
    //定义全局变量
    private ShopCallBackLister mshopCallBackLister;
    public void setLister(ShopCallBackLister lister){
        this.mshopCallBackLister =lister;
    }
    //创建接口
    public interface ShopCallBackLister{
        void callback(List<ShopBean.DataBean> list);
    }
}
