package com.example.nearbydiscosnevents.Retrofit;

import com.example.nearbydiscosnevents.Models.Request.LoginUser.LoginUserRequest;
import com.example.nearbydiscosnevents.Models.Response.ResponseDocumentType;
import com.example.nearbydiscosnevents.Models.Response.ResponseLoginUser;
import com.example.nearbydiscosnevents.Models.Event;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IAPI {



    @POST("usuario/login")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseLoginUser> LoginUser(@Body RequestBody request);

    @POST("documento/tipo/obtener")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseDocumentType> obtainDocumentTypes(@Body RequestBody request);

    @FormUrlEncoded
    @POST("eventos/destacados/obtener")
    Call<List<Event>> LoadOutstandingEvents();

    @FormUrlEncoded
    @POST("eventos/destalle/obtener")
    Call<List<Event>> LoadOutstandingEvents(@Field("idEvento") String eventID);



}
