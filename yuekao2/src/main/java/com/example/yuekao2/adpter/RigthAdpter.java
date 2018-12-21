package com.example.yuekao2.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuekao2.R;
import com.example.yuekao2.bean.RiagthBean;

import java.util.ArrayList;
import java.util.List;

public class RigthAdpter extends RecyclerView.Adapter<RigthAdpter.MyViewHolder> {
    private List<RiagthBean.DataBean> mList ;
    private Context mContext;

    public RigthAdpter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<RiagthBean.DataBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.rigthitem,null);
        RigthAdpter.MyViewHolder holder = new RigthAdpter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
             myViewHolder.textView.setText(mList.get(i).getName());
             GoodsAdpter goodsAdpter = new GoodsAdpter(mContext);

             GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
             myViewHolder.recyclerView.setLayoutManager(gridLayoutManager);
             myViewHolder.recyclerView.setAdapter(goodsAdpter);
             myViewHolder.recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
             goodsAdpter.setmList(mList.get(i).getList());

    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RecyclerView recyclerView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.rigthtitle);
            recyclerView = itemView.findViewById(R.id.goodsrecycle);
        }
    }
}
