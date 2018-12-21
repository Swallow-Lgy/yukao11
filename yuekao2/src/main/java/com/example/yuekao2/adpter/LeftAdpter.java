package com.example.yuekao2.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuekao2.R;
import com.example.yuekao2.bean.LeftBean;

import java.util.ArrayList;
import java.util.List;

public class LeftAdpter extends RecyclerView.Adapter<LeftAdpter.MyViewHolder> {
    private List<LeftBean.DataBean> mList = new ArrayList<>();
    private Context mContext;

    public LeftAdpter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmList(List<LeftBean.DataBean> mList) {
        this.mList = mList;
        //？？？？？？？？？？？？？？刷新
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext,R.layout.leftitem,null);
        LeftAdpter.MyViewHolder holder = new LeftAdpter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
             myViewHolder.textView.setText(mList.get(i).getName());
             myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (monClickListener!=null){
                         monClickListener.onClick(i,mList.get(i).getCid());
                     }
                 }
             });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
         TextView textView;
         LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.typetitle);
            linearLayout = itemView.findViewById(R.id.linear);
        }
    }
    //全局变量
    private onClickListener monClickListener;
    public void setOnClickListener(onClickListener onClickListener){
        monClickListener = onClickListener;
    }
    //接口
    public interface  onClickListener{
        void onClick(int position,String cid);
    }

}
