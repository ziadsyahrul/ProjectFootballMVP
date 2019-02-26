package com.ziadsyahrul.projectfootballmvp.UI.favorite;

import android.content.Context;

import com.ziadsyahrul.projectfootballmvp.data.local.FootballDatabase;



public class FavoritePresenter implements FavoriteContract.Presenter{

    private final FavoriteContract.View view;
    private FootballDatabase footballDatabase;

    public FavoritePresenter(FavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListUser(Context context) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        if (footballDatabase.footballDao().selectFacorite() != null){
            view.showDataList(footballDatabase.footballDao().selectFacorite());
        }else {
            view.showFailureMessage("Tidak ada data di favorite");
        }
        view.hideRefresh();
    }
}
