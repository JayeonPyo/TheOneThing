package com.example.mochon.Retrofit;

import android.widget.TextView;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkInterface {
    @FormUrlEncoded
    @POST("/register")
    Call<RegisterUserModel> register(@Field("name") String name,
                                     @Field("email") String email,
                                     @Field("password")String  password,
                                     @Field("passwordConfirm") String passwordConfirm,
                                     @Field("mbti") String mbti

    ) ;

    @FormUrlEncoded
    @POST("/login")
    Call<String> login(@Field("email") String email,
                                  @Field("password")String  password
    ) ;


}
