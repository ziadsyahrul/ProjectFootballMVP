package com.ziadsyahrul.projectfootballmvp.UI.detail;

import android.content.Context;
import android.os.Bundle;

import com.ziadsyahrul.projectfootballmvp.model.TeamsItem;

public interface DetailTeamContract {

    interface View{
        void showDetailTeam(TeamsItem teamsItem);
        void showFailureMessage(String msg);
        void showSuccessMessage(String msg);
    }

    interface Presenter{
        void getDetailTeam(Bundle bundle);
        void addToFavorite(Context context, TeamsItem teamsItem);
        void removeFavorite(Context context, TeamsItem teamsItem);
        Boolean checkFavorite(Context context, TeamsItem teamsItem);
    }

}
