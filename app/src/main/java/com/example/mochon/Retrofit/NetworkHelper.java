package com.example.mochon.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {
    final static String url = "http://3.19.223.78";
    final static int port = 8080;
    private static Retrofit retrofit;

    public static NetworkInterface getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url+":"+port)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit.create(NetworkInterface.class);
    }



}
