package com.lijo.obvious.nasa.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lijo.obvious.nasa.R;

public class GalleryViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            image = (itemView).findViewById(R.id.iv_pic);
            title = (itemView).findViewById(R.id.tv_title);
        }
}
