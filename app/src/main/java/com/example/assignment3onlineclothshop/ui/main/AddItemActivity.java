package com.example.assignment3onlineclothshop.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignment3onlineclothshop.R;

import java.io.IOException;
import java.io.PrintStream;

public class AddItemActivity extends AppCompatActivity {

    private EditText name, price, imageName, description;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        name = findViewById(R.id.itemName);
        price = findViewById(R.id.itemPrice);
        imageName = findViewById(R.id.itemImageName);
        description = findViewById(R.id.itemDescription);
        add = findViewById(R.id.btnAddItem);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    public void addItem(){
        if (nullValidation()) {
            try {
                PrintStream printStream = new PrintStream(openFileOutput("items.txt", MODE_PRIVATE | MODE_APPEND));
                printStream.println(name.getText().toString() + "->" + price.getText().toString()
                        + "->" + imageName.getText().toString() + "->" + description.getText().toString());
                Toast.makeText(this, "Saved to " + getFilesDir(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ShowItemsActivity.class));
            } catch (IOException e) {
                Log.d("check", "Error: " + e.toString());
                e.printStackTrace();
            }
        }
    }

    public boolean nullValidation(){
        if (TextUtils.isEmpty(name.getText().toString())){
            name.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(price.getText().toString())){
            price.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(description.getText().toString())){
            description.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(imageName.getText().toString())){
            imageName.setError("Required Field");
            return false;
        }
        return true;
    }
}
