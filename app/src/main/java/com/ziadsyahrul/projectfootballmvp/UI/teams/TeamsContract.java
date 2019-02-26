package com.ziadsyahrul.projectfootballmvp.UI.teams;

import com.ziadsyahrul.projectfootballmvp.model.TeamsItem;

import java.util.List;

public interface TeamsContract {

    interface View{
        void showProgress();
        void  hideProgress();
        void showDataList(List<TeamsItem> teamsItemList);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getDataListTeams();
        void getSearchTeams(String searchText);
    }

}
