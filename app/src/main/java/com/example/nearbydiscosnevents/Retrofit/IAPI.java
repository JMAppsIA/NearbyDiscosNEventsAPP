package com.example.nearbydiscosnevents.Retrofit;

import com.example.nearbydiscosnevents.Models.Response.ResponseLoginUser;
import com.example.nearbydiscosnevents.Models.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IAPI {

    @FormUrlEncoded
    @POST("usuario/obtener")
    Call<ResponseLoginUser> LoginUser (@Field("username") String userName,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("eventos/destacados/obtener")
    Call<List<Event>> LoadOutstandingEvents();

    @FormUrlEncoded
    @POST("eventos/destalle/obtener")
    Call<List<Event>> LoadOutstandingEvents(@Field("idEvento") String eventID);

}
