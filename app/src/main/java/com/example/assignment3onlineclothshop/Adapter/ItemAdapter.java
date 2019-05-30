package com.example.assignment3onlineclothshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment3onlineclothshop.R;
import com.example.assignment3onlineclothshop.models.Item;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    Context mContext;
    List<Item> itemList;
//    Item itemModel;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:3000/";


    public ItemAdapter(Context mContext, List<Item> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgview;
        TextView itemname,itemprice,itemdescription;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgview=itemView.findViewById(R.id.imgview);
            itemname=itemView.findViewById(R.id.name);
            itemprice=itemView.findViewById(R.id.price);
            itemdescription=itemView.findViewById(R.id.description);

        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view,viewGroup,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int i) {

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
