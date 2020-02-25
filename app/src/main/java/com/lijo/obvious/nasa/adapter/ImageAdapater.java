package com.lijo.obvious.nasa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lijo.obvious.nasa.R;
import com.lijo.obvious.nasa.model.ImageResponse;

import java.util.List;

public class ImageAdapater extends RecyclerView.Adapter<ImageAdapater.MyviewHolder> {
    Context context;
    List<ImageResponse> dataList;

    private static final String IMAGE_URL = "https://api.pubburps.com/v1/api/home/fetch/events/";

    public ImageAdapater(Context context, List<ImageResponse> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setDataList(List<ImageResponse> exclusives) {
        this.dataList = exclusives;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageAdapater.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_gallery,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapater.MyviewHolder holder, final int position) {
        Glide.with(context).load(dataList.get(position).getUrl()).placeholder(R.drawable.no_image).into(holder.image);
        holder.title.setText(dataList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
       ImageView image;
       TextView title;

        public MyviewHolder(View itemView) {
            super(itemView);
            image = (itemView).findViewById(R.id.iv_pic);
            title = (itemView).findViewById(R.id.tv_title);
        }
    }

}
