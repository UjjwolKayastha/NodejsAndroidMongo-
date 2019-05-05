package com.example.assignment3onlineclothshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment3onlineclothshop.R;
import com.example.assignment3onlineclothshop.models.Item;
import com.example.assignment3onlineclothshop.ui.main.ItemDetailsActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    Context mContext;
    List<Item> itemList;
//    Item itemModel;


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
        final Item itemModel = itemList.get(i);

        itemViewHolder.imgview.setImageResource(itemModel.getImgId());
        itemViewHolder.itemname.setText(itemModel.getItemName());
        itemViewHolder.itemprice.setText(itemModel.getItemPrice());
        itemViewHolder.itemdescription.setText(itemModel.getItemDescription());

        itemViewHolder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ItemDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("image",itemModel.getImgId());
                intent.putExtra("name",itemModel.getItemName());
                intent.putExtra("price",itemModel.getItemPrice());
                intent.putExtra("description",itemModel.getItemDescription());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
