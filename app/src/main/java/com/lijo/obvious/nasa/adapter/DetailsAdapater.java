package com.lijo.obvious.nasa.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lijo.obvious.nasa.R;
import com.lijo.obvious.nasa.fragment.DetailsFragment;
import com.lijo.obvious.nasa.model.ImageResponse;

import java.util.List;

public class DetailsAdapater extends RecyclerView.Adapter<DetailsAdapater.MyviewHolder> {
    Context context;
    List<ImageResponse> dataList;

    private static final String IMAGE_URL = "https://api.pubburps.com/v1/api/home/fetch/events/";

    public DetailsAdapater(Context context, List<ImageResponse> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setDataList(List<ImageResponse> exclusives) {
        this.dataList = exclusives;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailsAdapater.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_details,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapater.MyviewHolder holder, final int position) {
        Glide.with(holder.image.getContext())
                .load(dataList.get(position).getUrl())
                .centerCrop()
                .override(300, 300)
                .placeholder(R.drawable.lottie_loading_image).into(holder.image);
        holder.title.setText(dataList.get(position).getTitle());
        holder.explanation.setText(dataList.get(position).getExplanation());
        holder.date.setText(dataList.get(position).getDate());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("data", dataList.toString());
                bundle.putString("position", String.valueOf(position));
                Fragment fragment = new DetailsFragment();
                fragment.setArguments(bundle);
                ((FragmentActivity)v.getContext())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_main, fragment)
                        .addToBackStack("details_fragment")
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
       ImageView image;
       TextView title, explanation, date;

        public MyviewHolder(View itemView) {
            super(itemView);
            image = (itemView).findViewById(R.id.img_pic_details);
            title = (itemView).findViewById(R.id.tv_detail_title);
            date = (itemView).findViewById(R.id.tv_detail_date);
            explanation = (itemView).findViewById(R.id.tv_detail_explanation);
        }
    }

}
