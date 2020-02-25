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
import com.lijo.obvious.nasa.viewholder.GalleryViewHolder;

import java.util.List;

public class ImageAdapater extends RecyclerView.Adapter<GalleryViewHolder> {
    Context context;
    List<ImageResponse> dataList;

    public ImageAdapater(Context context, List<ImageResponse> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_gallery,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, final int position) {
        Glide.with(holder.image.getContext())
                .load(dataList.get(position).getUrl())
                .centerCrop()
                .override(300, 300)
                .placeholder(R.drawable.lottie_loading_image)
                .into(holder.image);
        holder.title.setText(dataList.get(position).getTitle());
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
