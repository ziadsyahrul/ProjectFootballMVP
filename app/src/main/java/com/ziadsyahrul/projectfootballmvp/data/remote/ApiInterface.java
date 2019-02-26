package com.ziadsyahrul.projectfootballmvp.data.remote;

import com.ziadsyahrul.projectfootballmvp.model.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/v1/json/1/search_all_teams.php")
    Call<TeamResponse> getAllTeams(
            @Query("s") String s,
            @Query("c") String c
    );

    // endpoint untuk melakukan search berdasarkan nama team
    @GET("/api/v1/json/1/searchteams.php")
    Call<TeamResponse> getSearchTeams(@Query("t") String t);
}
