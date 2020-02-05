package com.example.topfilm.rank;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.topfilm.R;
import com.example.topfilm.model.Film;

import java.util.ArrayList;
import java.util.List;

public class RankActivity extends AppCompatActivity implements RankAdapter.Callback {
    private ViewPager viewPager;
    private RankAdapter rankAdapter;
    private List<Film> films;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestAppPermissions();
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBackGround));
        }
        setContentView(R.layout.activity_rank);
        addControl();
    }

    private void addControl() {
        films = createFilms();
        viewPager = findViewById(R.id.viewPaperRank);

        setUpViewPager();
    }

    private int getIntentRedirect() {
        Bundle extras = getIntent().getExtras();
        return extras.getInt("CTG_ID");
    }

    private ArrayList<Film> createFilms() {
        ArrayList<Film> resulf = new ArrayList<>();
        resulf.add(new Film(1, 1, "Cô Dâu 8 tuổi", "", "https://i.ytimg.com/vi/iO4MaSmuxTs/maxresdefault.jpg"));
        resulf.add(new Film(1, 2, "Cô Dâu 8 tuổi", "", "https://i.ytimg.com/vi/0BJMwNHLexU/maxresdefault.jpg"));
        resulf.add(new Film(1, 3, "Cô Dâu 8 tuổi", "", "https://i.pinimg.com/originals/0c/8a/64/0c8a64202094667fcefe38ba9e65f6f4.jpg"));
        resulf.add(new Film(1, 4, "Cô Dâu 8 tuổi", "", "https://i.ytimg.com/vi/iO4MaSmuxTs/maxresdefault.jpg"));
        resulf.add(new Film(1, 5, "Cô Dâu 8 tuổi", "", "https://i.ytimg.com/vi/0BJMwNHLexU/maxresdefault.jpg"));
        resulf.add(new Film(1, 6, "Cô Dâu 8 tuổi", "", "https://i.pinimg.com/originals/0c/8a/64/0c8a64202094667fcefe38ba9e65f6f4.jpg"));
        resulf.add(new Film(1, 7, "Cô Dâu 8 tuổi", "", "https://i.ytimg.com/vi/iO4MaSmuxTs/maxresdefault.jpg"));
        resulf.add(new Film(1, 8, "Cô Dâu 8 tuổi", "", "https://i.ytimg.com/vi/0BJMwNHLexU/maxresdefault.jpg"));
        resulf.add(new Film(1, 9, "Cô Dâu 8 tuổi", "", "https://i.pinimg.com/originals/0c/8a/64/0c8a64202094667fcefe38ba9e65f6f4.jpg"));
        resulf.add(new Film(1, 10, "Cô Dâu 8 tuổi", "", "https://i.pinimg.com/originals/0c/8a/64/0c8a64202094667fcefe38ba9e65f6f4.jpg"));
        return resulf;
    }

    private void setUpViewPager() {
        rankAdapter = new RankAdapter(films);
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

    }

    private void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasReadPermissions() && hasWritePermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 777); // your request code
    }

    private boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }
}

