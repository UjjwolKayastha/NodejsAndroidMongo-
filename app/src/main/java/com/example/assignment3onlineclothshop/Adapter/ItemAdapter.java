package com.example.assignment3onlineclothshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment3onlineclothshop.R;
import com.example.assignment3onlineclothshop.models.Item;
import com.example.assignment3onlineclothshop.ui.main.ItemDetailsActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    Context mContext;
    List<Item> itemList;
//    Item itemModel;
    Bitmap bitmap;
    public static final String BASE_URL = "http://10.0.2.2:4000/";


    public ItemAdapter(Context mContext, List<Item> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }



    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view,viewGroup,false);
        return new ItemViewHolder(view);
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int i) {

        final Item itemModel = itemList.get(i);


        itemViewHolder.itemname.setText(itemModel.getItemName());
        itemViewHolder.itemprice.setText(itemModel.getItemPrice());
        itemViewHolder.itemdescription.setText(itemModel.getItemDescription());

        StrictMode();
        String images = itemList.get(i).getItemImageName();
        final String path = BASE_URL+"/uploads"+"/"+images;
        Log.d("aaa", "onBindViewHolder: " + itemList.get(i).getItemImageName());
        System.out.println("Path: " +path);

        try {
            URL uri = new URL(path);
            bitmap = BitmapFactory.decodeStream((InputStream)uri.getContent());
            itemViewHolder.imgview.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ItemDetailsActivity.class);
                intent.putExtra("image", itemModel.getItemImageName());
                intent.putExtra("name", itemModel.getItemName());
                intent.putExtra("price", itemModel.getItemPrice());
                intent.putExtra("description", itemModel.getItemDescription());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
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

}
