package com.awt.jobstreamers.rest;


import com.awt.jobstreamers.models.LoginResponse;
import com.awt.jobstreamers.models.Logout;
import com.awt.jobstreamers.models.OtpResponse;
import com.awt.jobstreamers.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

//    @GET("movie/top_rated")
//    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
//
//    @GET("movie/{id}")
//    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

//    @GET("/posts")
//    Call<List<Post>> getPosts();

    @POST("login/")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("username") String user_id);

    @POST("signup/")
    @FormUrlEncoded
    Call<RegisterResponse> register(@Field("fname") String firstName,
                                    @Field("lname") String lastName,
                                    @Field("photo_upload") String photo,
                                    @Field("username") String userName);

    @POST("login/otp/")
    @FormUrlEncoded
    Call<OtpResponse> otp(@Field("username") String user_id,
                          @Field("otp_code") String otp);

    @POST("logout/")
    @FormUrlEncoded
    Call<Logout> logout(@Field("token_id") String token);
}
