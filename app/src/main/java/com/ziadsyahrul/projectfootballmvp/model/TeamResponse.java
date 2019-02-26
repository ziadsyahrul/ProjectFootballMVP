package com.ziadsyahrul.projectfootballmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamResponse {

    @SerializedName("teams")
    private List<TeamsItem> teams;


    public List<TeamsItem> getTeams() {
        return teams;
    }
}
