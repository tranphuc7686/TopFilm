package com.example.topfilm.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.topfilm.R;
import com.example.topfilm.model.Category;
import com.example.topfilm.rank.RankActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryAdapter.Callback {
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorBackGround));
        }
        setContentView(R.layout.activity_main);
        addControl();
    }

    private void addControl() {
        categories = createCategories();
        recyclerView = findViewById(R.id.listCategory);
        setUpRecyclerView();
    }

    private ArrayList<Category> createCategories() {
        ArrayList<Category> resulf = new ArrayList<>();
        resulf.add(new Category(1, "Phim Bộ", "https://i.ytimg.com/vi/rmPP-DRqEf0/maxresdefault.jpg"));
        resulf.add(new Category(1, "Phim Lẻ", "https://images3.alphacoders.com/746/746551.jpg"));
        return resulf;
    }
    private void runLayoutAnimation( RecyclerView recyclerView) {
         Context context = recyclerView.getContext();
         LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
    private void setUpRecyclerView() {
        categoryAdapter = new CategoryAdapter(categories, this);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        runLayoutAnimation(recyclerView);
    }

    @Override
    public void onClickItem(Category category) {
        Intent mainIntent = new Intent(CategoryActivity.this, RankActivity.class);
        mainIntent.putExtra("CTG_ID",category.getId());
        startActivity(mainIntent);

    }
}
