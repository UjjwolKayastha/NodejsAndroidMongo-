package com.example.assignment3onlineclothshop.interfaces;

import com.example.assignment3onlineclothshop.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserInterface {
    @POST("users/signup")
    Call<ResponseBody> userRegistration(@Body User user);

    @POST("users/login")
    Call<ResponseBody> userLogin(@Body User user);


}