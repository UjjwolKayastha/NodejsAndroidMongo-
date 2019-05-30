package com.example.assignment3onlineclothshop.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.CircularPropagation;
import android.widget.TextView;

import com.example.assignment3onlineclothshop.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemDetailsActivity extends AppCompatActivity {

    TextView name, description, price;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        name = findViewById(R.id.detailName);
        description = findViewById(R.id.detailDescription);
        price = findViewById(R.id.detailPrice);
        circleImageView = findViewById(R.id.detailImgView);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String image = bundle.getString("image");
            Picasso.with(this).load("http://10.0.2.2:4000/uploads/" + image).into(circleImageView);
            name.setText(bundle.getString("name"));
            price.setText(bundle.getString("description"));
            description.setText(bundle.getString("price"));
        }
    }
}
