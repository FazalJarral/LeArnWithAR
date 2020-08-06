package com.leARn.arcore.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.leARn.arcore.ItemClick;
import com.leARn.arcore.R;
import com.leARn.arcore.bean.Asset;
import com.bumptech.glide.Glide;

import java.util.List;

public class AssetAdapter extends RecyclerView.Adapter<AssetAdapter.ViewHolder>  {
    Context context;
    List<Asset> assetList;
    ItemClick mlistner;


    public AssetAdapter(Context context, List<Asset> assetList , ItemClick listner) {
        this.context = context;
        this.assetList = assetList;
        this.mlistner = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item_asset , parent , false);
        ViewHolder viewHolder = new ViewHolder(v , mlistner);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Asset reference = assetList.get(position);
        holder.textView.setText(reference.getDisplayName());

//
        Log.e("png", reference.getThumbnail().getUrl());
        Glide.with(context)
                .load(reference.getThumbnail().getUrl())
                .into(holder.imageView);



    }



    @Override
    public int getItemCount() {
        return assetList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       ImageView imageView;
        TextView textView;
        ItemClick listner;
        public ViewHolder(@NonNull View itemView , ItemClick listner) {
            super(itemView);
            imageView = itemView.findViewById(R.id.assetImage);
            textView = itemView.findViewById(R.id.assetName);
            this.listner = listner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e("item " , "item is clicked");
            listner.ItemClick(getAdapterPosition());
        }
    }


}
