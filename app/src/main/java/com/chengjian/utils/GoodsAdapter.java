package com.chengjian.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.fatfish.chengjian.gorubbish.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class GoodsAdapter extends RecyclerView.Adapter {

    private ArrayList<GoodsEntity> mGoods = null;
    private Context mContext = null;

    public interface onItemClickListener {
        public void onClick(View view, GoodsEntity goodsEntity);
    }

    private onItemClickListener listener = null;

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public GoodsAdapter(Context context, ArrayList<GoodsEntity> entities) {
        this.mContext = context;
        this.mGoods = entities;
    }

    //inflate the predefined layout
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //create the view
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.goods_items_layout, viewGroup, false);
        MyHolder myHolder = new MyHolder(rootView);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder myHolder = (MyHolder) viewHolder;
        //update the value
        GoodsEntity goodsEntity = mGoods.get(i);
        myHolder.imageView.setImageDrawable(goodsEntity.getImage());
        myHolder.textView_price.setText("¥ " + goodsEntity.getPrice());
        myHolder.textView_des.setText(goodsEntity.getDescription());
        //add the click listener
        myHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, goodsEntity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGoods.size();
    }


    static class MyHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView_price;
        private TextView textView_des;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.good_image);
            textView_price = itemView.findViewById(R.id.good_price);
            textView_des = itemView.findViewById(R.id.good_des);
        }
    }
}
