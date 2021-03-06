package com.nihad.filim_app.Utils;

import com.google.gson.JsonObject;
import com.nihad.filim_app.model.FilimModel;
import com.nihad.filim_app.model.ResponseClass;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    //
    @FormUrlEncoded
    @GET("filimlist")
    Call<JsonObject> filimlist12(
    );

    @GET("films")
    Single<ResponseClass> filimlist();


    @FormUrlEncoded
    @POST("read_data.php")
    Call<JsonObject> getData(
            @Field("api_data") String api_data
    );


    @GET("/{input}")
    Call<JsonObject> getlistdatas(@Path(value = "input", encoded = true) String input);
//    @GET("profiledata/get_home_data")
//    Call<JsonObject> getHomeData(
//            @Header("token_valid") String token_valid,
//            @Header("access_token") String access_token
//    );
}
