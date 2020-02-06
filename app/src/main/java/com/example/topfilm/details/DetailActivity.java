package com.example.topfilm.details;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.topfilm.NetworkHelper;
import com.example.topfilm.R;
import com.example.topfilm.model.Detail;

public class DetailActivity extends AppCompatActivity {
    private VideoView videoView;
    private TextView actors,plot;
    private ImageView btnback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBackGround));
        }
        setContentView(R.layout.activity_detail);
        addControl();
        addListener();
    }

    private void addListener() {
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControl() {
        videoView = findViewById(R.id.videoViewContent);
        actors = findViewById(R.id.textActors);
        plot = findViewById(R.id.textPlot);
        btnback = findViewById(R.id.btnBack);
        // Nhận data từ màn trước
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("FILM_NAME");
        String urlTrailler = extras.getString("FILM_TRAILLER");
        initScreen(name,urlTrailler);
    }

    private void initScreen(String name, final String urlTrailler){
        NetworkHelper.GetJsonData("http://www.omdbapi.com/?t="+name+"&apikey=d5e301b0", new NetworkHelper.Callback() {
            @Override
            public void getStringJson(String json) {
                Detail data = DetailHelper.parseJsonToDetail(json,urlTrailler);
                actors.setText(data.getActors());
                plot.setText(data.getPlot());
                videoView.setVideoURI(Uri.parse(urlTrailler));
                videoView.start();
            }

            @Override
            public void getStringJsonError(String msg) {
                System.out.println(msg);
            }
        });
    }
}
