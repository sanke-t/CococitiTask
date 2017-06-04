package com.example.sanket.task.request;

import com.example.sanket.task.models.Res;
import com.example.sanket.task.models.Showcase;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by sanket on 01/06/17.
 */

public interface RetroService {
    @POST("/users/sign_in")
    @Headers({"Content-Type:application/json","accept:application/json"})
        void login(
            @Field("email") String email,
            @Field("password") String password,
            Callback<Response> callback);

    @POST("/users/sign_in")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<ResponseBody> login(@Body RequestBody params);

    @GET("/get_feeds")
    Call<Res> getTasks(
            @HeaderMap Map<String, String> headers
    );
}

