package com.example.topfilm.rank;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.topfilm.NetworkHelper;
import com.example.topfilm.R;
import com.example.topfilm.category.CategoryActivity;
import com.example.topfilm.details.DetailActivity;
import com.example.topfilm.model.Film;

import java.util.List;

public class RankActivity extends AppCompatActivity implements RankAdapter.Callback {
    private ViewPager viewPager;
    private RankAdapter rankAdapter;
    private List<Film> films;
    private final int PHIM_BO = 0;
    private final int PHIM_LE = 1;
    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBackGround));
        }
        setContentView(R.layout.activity_rank);
        addControl();
        addListener();
    }

    private void addListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControl() {
        getDataFilms();
        viewPager = findViewById(R.id.viewPaperRank);
        btnBack = findViewById(R.id.btnBack);


    }

    private int getIntentRedirect() {
        Bundle extras = getIntent().getExtras();
        return extras.getInt("CTG_ID");
    }

    private void getDataFilms() {
        switch ( getIntentRedirect()){
            case PHIM_BO :{
                NetworkHelper.GetJsonData("https://firebasestorage.googleapis.com/v0/b/light-sleep.appspot.com/o/categoryphimbo.json?alt=media&token=4568cc98-d237-48a0-8539-13ad34914929", new NetworkHelper.Callback() {
                    @Override
                    public void getStringJson(String json) {
                        films = RankHepler.parseJsonToFilm(json);
                        setUpViewPager();
                    }

                    @Override
                    public void getStringJsonError(String msg) {
                        System.out.println(msg);
                    }
                });
                break;
            }
            case PHIM_LE:{
                NetworkHelper.GetJsonData("https://firebasestorage.googleapis.com/v0/b/light-sleep.appspot.com/o/categoryphimle.json?alt=media&token=aa4b644e-d1ab-48e5-84aa-8998b5ba1bac", new NetworkHelper.Callback() {
                    @Override
                    public void getStringJson(String json) {
                        films = RankHepler.parseJsonToFilm(json);
                        setUpViewPager();
                    }

                    @Override
                    public void getStringJsonError(String msg) {
                        System.out.println(msg);
                    }
                });
                break;
            }
        }


    }

    private void setUpViewPager() {
        rankAdapter = new RankAdapter(films,this);
        viewPager.setAdapter(rankAdapter);
        runLayoutAnimation(viewPager);

    }

    private void runLayoutAnimation(ViewPager viewPager) {
        Context context = viewPager.getContext();
        LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_right);

        viewPager.setLayoutAnimation(controller);
        viewPager.getAdapter().notifyDataSetChanged();
        viewPager.scheduleLayoutAnimation();
    }

    @Override
    public void onClickItem(Film Film) {
        Intent mainIntent = new Intent(RankActivity.this, DetailActivity.class);
        mainIntent.putExtra("FILM_NAME",Film.getName());
        mainIntent.putExtra("FILM_TRAILLER",Film.getIntroduce());
        startActivity(mainIntent);
    }


}

