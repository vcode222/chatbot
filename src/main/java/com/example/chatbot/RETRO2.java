package com.example.chatbot;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Url;

public interface RETRO2 {

    @GET
     Call<msgmodal> getMessage(@Url String url) ;
    }


