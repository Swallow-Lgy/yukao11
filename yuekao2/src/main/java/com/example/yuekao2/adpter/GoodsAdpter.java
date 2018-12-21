package com.example.yuekao2.adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuekao2.R;
import com.example.yuekao2.bean.RiagthBean;

import java.util.ArrayList;
import java.util.List;

public class GoodsAdpter extends RecyclerView.Adapter<GoodsAdpter.MyViewHolder> {
    private List<RiagthBean.DataBean.ListBean> mList = new ArrayList<>();
    private Context mContext;

    public GoodsAdpter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmList(List<RiagthBean.DataBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view = View.inflate(mContext,R.layout.gooditem,null);
         GoodsAdpter.MyViewHolder holder = new GoodsAdpter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Glide.with(mContext).load(mList.get(i).getIcon()).into(myViewHolder.imageView);
        myViewHolder.textView.setText(mList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.goodstitle);
            imageView = itemView.findViewById(R.id.goodsimage);
        }
    }
}
