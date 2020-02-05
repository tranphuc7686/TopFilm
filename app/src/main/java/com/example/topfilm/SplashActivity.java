package com.example.topfilm;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.topfilm.category.CategoryActivity;

public class SplashActivity extends AppCompatActivity {
    /** Thời gian để xuất hiện màn hình chính **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBackGround));
        }
        setContentView(R.layout.activity_splash);
        /* Hàm đếm thời gian để xuất hiện màn hình chính*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /*Tạo intent để chuyển màn hình */
                Intent mainIntent = new Intent(SplashActivity.this, CategoryActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
