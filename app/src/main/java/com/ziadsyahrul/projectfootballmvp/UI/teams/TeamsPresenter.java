package com.ziadsyahrul.projectfootballmvp.UI.teams;

import com.ziadsyahrul.projectfootballmvp.data.remote.ApiClient;
import com.ziadsyahrul.projectfootballmvp.data.remote.ApiInterface;
import com.ziadsyahrul.projectfootballmvp.model.TeamResponse;
import com.ziadsyahrul.projectfootballmvp.utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsPresenter implements TeamsContract.Presenter{

    private final TeamsContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public TeamsPresenter(TeamsContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListTeams() {
        view.showProgress();

        Call<TeamResponse> call = apiInterface.getAllTeams(Constant.S, Constant.C);
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                view.hideProgress();


                if (response.body() != null){
                    view.showDataList(response.body().getTeams());
                }else {
                    view.showFailureMessage("Data Kosong");
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                view.hideProgress();

                view.showFailureMessage(t.getMessage());
            }
        });

    }

    @Override
    public void getSearchTeams(String searchText) {
        // Mencek apakah inputan user ada isinya?
        if (!searchText.isEmpty()){
            // apabila ada isinya maka lakukan request ke api
            view.showProgress();

            Call<TeamResponse> call = apiInterface.getSearchTeams(searchText);
            call.enqueue(new Callback<TeamResponse>() {
                @Override
                public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                    view.hideProgress();

                    if (response.body() != null){
                        view.showDataList(response.body().getTeams());
                    }else {
                        view.showFailureMessage("Team tidak ada");
                    }
                }

                @Override
                public void onFailure(Call<TeamResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailureMessage(t.getMessage());
                }
            });

        }else {
            // Apabila kosong maka lakukan pengambilan data team tanpa search
            getDataListTeams();
        }
    }
}
