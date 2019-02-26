package com.ziadsyahrul.projectfootballmvp.UI.favorite;

import android.content.Context;

import com.ziadsyahrul.projectfootballmvp.data.local.FootballDatabase;
import com.ziadsyahrul.projectfootballmvp.model.TeamsItem;

import java.util.ArrayList;
import java.util.List;


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


    @Override
    public void searchTeams(Context context, String searchText) {
        if (!searchText.isEmpty()){
            // Apabila inputan user ada maka yg pertama kali kita lakukan adalah menyiapkan database nya
            footballDatabase = FootballDatabase.getFootballDatabase(context);
            // Memasukkan semua data favorite ke dalam variable list
            List<TeamsItem> teamsItemList = footballDatabase.footballDao().selectFacorite();
            // Membuat penampung untuk menampung data yang sudah kita seleksi berdasarkan inputan user
            List<TeamsItem> mTeamsItemList = new ArrayList<>();

            if (teamsItemList != null){
                // Jika tidak null maka kita melakukan perulangan untuk mengecek data yang ada di dalam table favorite
                for (TeamsItem data: teamsItemList){
                    // Pengecekan teams berdasarkan dengan permintaan user
                    String namaTeam = data.getStrTeam().toLowerCase();
                    // contains adalah method untuk membandingkan
                    if (namaTeam.contains(searchText.toLowerCase())){
                        // Memasukkan team yang sama dengan inputan user ke dalam wadah kedua jika ia true
                        mTeamsItemList.add(data);
                    }
                }
                // Mengirimkan wadah kedua ke view
                view.showDataList(mTeamsItemList);
            }
        }else {
            // Apabila inputan user kosong maka ambil data tanpa keyword
            getDataListUser(context);
        }
    }
}
