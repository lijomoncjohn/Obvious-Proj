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
import com.lijo.obvious.nasa.viewholder.DetailsViewHolder;

import java.util.List;

public class DetailsAdapater extends RecyclerView.Adapter<DetailsViewHolder> {
    Context context;
    List<ImageResponse> dataList;

    public DetailsAdapater(Context context, List<ImageResponse> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_details,parent,false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder holder, final int position) {
        Glide.with(holder.image.getContext())
                .load(dataList.get(position).getUrl())
                .centerCrop()
                .override(300, 300)
                .placeholder(R.drawable.lottie_loading_image).into(holder.image);
        holder.title.setText(dataList.get(position).getTitle());
        holder.explanation.setText(dataList.get(position).getExplanation());
        holder.date.setText(dataList.get(position).getDate());
        holder.copyright.setText(dataList.get(position).getCopyright());
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

}
