package com.ziadsyahrul.projectfootballmvp.UI.detail;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ziadsyahrul.projectfootballmvp.R;
import com.ziadsyahrul.projectfootballmvp.model.TeamsItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTeamActivity extends AppCompatActivity implements DetailTeamContract.View {


    @BindView(R.id.img_logo_detail)
    ImageView imgLogoDetail;
    @BindView(R.id.txt_name_team_detail)
    TextView txtNameTeamDetail;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.card_view_detail)
    CardView cardViewDetail;
    @BindView(R.id.sv_detail)
    ScrollView svDetail;

    private Menu menu;
    private TeamsItem teamsItem;

    private DetailTeamPresenter detailTeamPresenter = new DetailTeamPresenter(this);
    private Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Detail Team");

        Bundle bundle = getIntent().getExtras();
        detailTeamPresenter.getDetailTeam(bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;

        getMenuInflater().inflate(R.menu.favorite, menu);
        setFavorite();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_favorite:
                if (isFavorite){
                    detailTeamPresenter.removeFavorite(this, teamsItem);
                }else{
                    detailTeamPresenter.addToFavorite(this, teamsItem);
                }
                isFavorite = detailTeamPresenter.checkFavorite(this, teamsItem);
                setFavorite();
                break;

            case android.R.id.home:
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }

        return true;
    }

    private void setFavorite() {
        if (isFavorite){
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_star_black_24dp));
        }else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
        }
    }

    @Override
    public void showDetailTeam(TeamsItem teamsItem) {
        this.teamsItem = teamsItem;

        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image_black_24dp).placeholder(R.drawable.ic_broken_image_black_24dp);
        Glide.with(this).load(teamsItem.getStrTeamBadge()).apply(options).into(imgLogoDetail);
        txtNameTeamDetail.setText(teamsItem.getStrTeam());
        txtDesc.setText(teamsItem.getStrDescriptionEN());

        // Mencek favorite atau bukan
        isFavorite = detailTeamPresenter.checkFavorite(this, teamsItem);
    }

    @Override
    public void showFailureMessage(String msg) {
        Snackbar.make(svDetail, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String msg) {
        Snackbar.make(svDetail, msg, Snackbar.LENGTH_SHORT).show();
    }
}
