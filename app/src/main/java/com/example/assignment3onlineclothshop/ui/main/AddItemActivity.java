package com.example.assignment3onlineclothshop.ui.main;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3onlineclothshop.R;
import com.example.assignment3onlineclothshop.interfaces.ItemInterface;
import com.example.assignment3onlineclothshop.models.ImageResponse;
import com.example.assignment3onlineclothshop.models.Item;
import com.example.assignment3onlineclothshop.models.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddItemActivity extends AppCompatActivity {

    public EditText name, price, description;
    ImageView imageView;
    public Button add, confirm;
    TextView imagename;
    Uri uri;
    Bitmap bitmap;
    Retrofit retrofit;
    ItemInterface itemInterface;
    private static final int PICK_IMAGE = 1;



    public  String BASE_URL = "http://10.0.2.2:4000/";
    
    String imagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        name = findViewById(R.id.itemName);
        price = findViewById(R.id.itemPrice);
        imageView = findViewById(R.id.imgPhoto);

        imagename = findViewById(R.id.tvImagename);
        
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        
        description = findViewById(R.id.itemDescription);

        confirm = findViewById(R.id.btnConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage(bitmap);
            }
        });

        add = findViewById(R.id.btnAddItem);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void BrowseImage() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);
    }

    private void addImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bytes = stream.toByteArray();
        try {
            File file = new File(this.getCacheDir(), "image.jpeg");
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();

            RequestBody requestBody = RequestBody.
                    create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.
                    createFormData("imageFile", file.getName(), requestBody);


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            itemInterface = retrofit.create(ItemInterface.class);
            Call<ImageResponse> imgCall = itemInterface.uploadImage(body);
            imgCall.enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                    imagename.setText(response.body().getFilename());
                }

                @Override
                public void onFailure(Call<ImageResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "error is" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){


        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap
                        (getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }








    public void addItem(){
        if (nullValidation()) {

            String sname = name.getText().toString();
            String sprice = price.getText().toString();
            String simagename = imagename.getText().toString();
            String sdesc = description.getText().toString();

            Item item = new Item(sname,sdesc, simagename, sprice);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ItemInterface itemInterface = retrofit.create(ItemInterface.class);

            Call<Void> call = itemInterface.addItem(item);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("VAL", "success ");

                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "ITEM ADDED", Toast.LENGTH_SHORT).show();

                        Log.d("VAL", "success response ");

                        name.setText("");
                        price.setText("");
                        description.setText("");
                    }else {
                        try{
                            Log.d("VAL", response.toString());

                            Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

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

        return true;
    }



}


