package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);
        return new RvAdapter.MyViewHolder(view);
    }

    /*将所创建的RecyclerView控件中Item视图与相关数据绑定起来*/
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        /*为奇数项Item视图和偶数项Item视图分别绑定不同的图片数据*/
        if (position % 2 == 0) {
            holder.imageView.setBackgroundResource(R.drawable.pic_one);
        } else {
            holder.imageView.setBackgroundResource(R.drawable.pic_two);
        }

    }

    @Override
    public int getItemCount() {
        return 20;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.stagger);
        }
    }
}
