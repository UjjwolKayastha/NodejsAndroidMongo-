package com.example.assignment3onlineclothshop.interfaces;

import com.example.assignment3onlineclothshop.models.ImageResponse;
import com.example.assignment3onlineclothshop.models.Item;
import com.example.assignment3onlineclothshop.models.User;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ItemInterface {
    @POST("items")
    Call<Void> addItem(@Body Item item);

//    @FormUrlEncoded
//    @POST("items")
//    Call<Void> addItem(@Field("name") String name, @Field())

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

}
