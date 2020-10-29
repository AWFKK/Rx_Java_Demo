package com.example.rx_java_demo.Api;



import com.example.rx_java_demo.Model.Hero;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface Api {

    String BASE_URL = "https://simplifiedcoding.net/demos/";

    /**
     * The return type is important here
     * The class structure that you've defined in Call<T>
     * should exactly match with your json response
     * If you are not using another api, and using the same as mine
     * then no need to worry, but if you have your own API, make sure
     * you change the return type appropriately
     *
     * @return*/
    @GET("marvel")
    Observable<List<Hero>> getHeroes();

}
