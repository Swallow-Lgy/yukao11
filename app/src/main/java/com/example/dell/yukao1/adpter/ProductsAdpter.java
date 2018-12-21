package com.example.dell.yukao1.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.yukao1.R;
import com.example.dell.yukao1.bean.ShopBean;
import com.example.dell.yukao1.view.CustomCunterView;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdpter extends RecyclerView.Adapter<ProductsAdpter.MyViewHolder> {
    private Context mContext;
    private List<ShopBean.DataBean.ListBean> mList = new ArrayList<>();

    public ProductsAdpter(Context mContext, List<ShopBean.DataBean.ListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_goods,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        String url = mList.get(i).getImages().split("\\|")[0].replace("https","http");
        Glide.with(mContext).load(url).into(myViewHolder.mImage);
        myViewHolder.mTitle.setText(mList.get(i).getTitle());
        myViewHolder.mPrice.setText(mList.get(i).getPrice()+"");
        myViewHolder.mCheckBox.setChecked(mList.get(i).isCheck());
        //根据商家的不同，商品添加了选中改变

         myViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 mList.get(i).setCheck(isChecked);
                 if (mshopCallBackLister!=null){

                     mshopCallBackLister.callBack();
                 }
             }
         });
         //设置自定义view的edit
         myViewHolder.mcustomCunterView.setData(this,mList,i);
         myViewHolder.mcustomCunterView.setOnClickLister(new CustomCunterView.CallBackLister() {
             @Override
             public void callBack() {
                 if (mshopCallBackLister!=null){
                     mshopCallBackLister.callBack();
                 }
             }
         });
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CustomCunterView mcustomCunterView;
        TextView mTitle,mPrice;
        ImageView mImage;
        CheckBox mCheckBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.check_product);
            mImage = itemView.findViewById(R.id.iv_product);
            mTitle = itemView.findViewById(R.id.tv_product_title);
            mPrice = itemView.findViewById(R.id.tv_product_price);
            mcustomCunterView=itemView.findViewById(R.id.custom_product_count);
        }
    }
    //做商品的全选
    public void selectOrRemoveAll(boolean isSelectAll){
         for (ShopBean.DataBean.ListBean listBean:mList){
             listBean.setCheck(isSelectAll);
         }
         notifyDataSetChanged();
    }
    private ShopCallBackLister mshopCallBackLister;
    public void setListenter(ShopCallBackLister listenter){
        mshopCallBackLister = listenter;
    }

    interface ShopCallBackLister{
        void callBack();
    }


}
