package com.ziadsyahrul.projectfootballmvp.UI.favorite;

import android.content.Context;

import com.ziadsyahrul.projectfootballmvp.model.TeamsItem;

import java.util.List;

public interface FavoriteContract {

    interface View{
        void showDataList(List<TeamsItem> teamsItemList);
        void showFailureMessage(String msg);
        void hideRefresh();
    }

    interface Presenter{
        void getDataListUser(Context context);
    }
}
