package com.ziadsyahrul.projectfootballmvp.UI.detail;

import android.content.Context;
import android.os.Bundle;

import com.ziadsyahrul.projectfootballmvp.data.local.FootballDatabase;
import com.ziadsyahrul.projectfootballmvp.model.TeamsItem;
import com.ziadsyahrul.projectfootballmvp.utils.Constant;

public class DetailTeamPresenter implements DetailTeamContract.Presenter {

    private final DetailTeamContract.View view;
    private FootballDatabase footballDatabase;

    public DetailTeamPresenter(DetailTeamContract.View view) {
        this.view = view;
    }

    @Override
    public void getDetailTeam(Bundle bundle) {
        if (bundle != null){
            TeamsItem teamsItem = (TeamsItem) bundle.getSerializable(Constant.KEY_DATA);
            view.showDetailTeam(teamsItem);
        }else {
            view.showFailureMessage("Data kosong");
        }
    }

    @Override
    public void addToFavorite(Context context, TeamsItem teamsItem) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        footballDatabase.footballDao().insertItem(teamsItem);

        view.showSuccessMessage("Tersimpan");
    }

    @Override
    public void removeFavorite(Context context, TeamsItem teamsItem) {
        footballDatabase = FootballDatabase.getFootballDatabase(context);
        footballDatabase.footballDao().delete(teamsItem);
        view.showSuccessMessage("Terhapus");
    }

    @Override
    public Boolean checkFavorite(Context context, TeamsItem teamsItem) {
        Boolean bFavorite = false;

        footballDatabase = FootballDatabase.getFootballDatabase(context);
        return bFavorite = footballDatabase.footballDao().selectItem(teamsItem.getIdTeam()) != null;
    }
}
