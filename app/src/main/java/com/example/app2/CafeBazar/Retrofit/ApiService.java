package com.example.app2.CafeBazar.Retrofit;

import com.example.app2.CafeBazar.CafeModel.App;
import com.example.app2.CafeBazar.CafeModel.Banner;
import com.example.app2.CafeBazar.CafeModel.Cat;
import com.example.app2.CafeBazar.CafeModel.CommentApp;
import com.example.app2.CafeBazar.CafeModel.Slider;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("bins/o8j2g")
    Call<List<Slider>> getSliders();

    @GET("bins/yqm6g")
    Call<List<Banner>> getBanners();

    @GET("bins/11gpw4")
    Call<List<App>> getNewApps();

    @GET("bins/eu8wk")
    Call<List<App>> getUpdatedApps();

    @GET("bins/ffoic")
    Call<App> getUniqueApp(@Query("id") String id);

    @GET("bins/10d2l2")
    Call<App> getCommentApps(@Query("id") String id);

//    @GET("get")
//    Call<ResponseBody> addComment(@Query("app_id") String appId,
//                                  @Query("user_id") String userId,
//                                  @Query("star") int star,
//                                  @Query("comment") String comment);

    @FormUrlEncoded
    @POST("post")
    Call<ResponseBody> addComment(@Field("app_id") String appId,
                                  @Field("user_id") String userId,
                                  @Field("star") int star,
                                  @Field("comment") String comment);

    @FormUrlEncoded
    @POST("post")
    Call<ResponseBody> sendNumber(@Field("to") String phoneNumber);

    @FormUrlEncoded
    @POST("validation.php")
    Call<ResponseBody> sendValidationCode(
            @Field("code") String validationCode,
            @Field("phone_number") String phoneNumber);

    @FormUrlEncoded
    @POST("bins/1da6eu")
    Call<List<CommentApp>> getAppComments(@Field("id") String appId);

    @FormUrlEncoded
    @POST("post")
    Call<ResponseBody> setVote(@Field("vote") String vote,
                               @Field("user_id") String userId,
                               @Field("comment_id") String commentId);

    @GET("bins/1cvmki")
    Call<List<Cat>> getCats();

    @GET("bins/rtlam")
    Call<List<App>> search(@Query("search") String search);


//    we should send package name with POST method, because GET request has limitation in size;
    @FormUrlEncoded
    @POST("post")
    Call<ResponseBody> sendInstallApp(@Field("install_app") String installApp);
}
