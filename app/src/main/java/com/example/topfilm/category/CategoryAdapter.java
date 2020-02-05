package com.example.topfilm.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topfilm.R;
import com.example.topfilm.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> mCategories;
    private Callback onClickCallback;

    public CategoryAdapter(List<Category> mCategories, Callback callback) {
        this.mCategories = mCategories;
        this.onClickCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View heroView = inflater.inflate(R.layout.item_category, parent, false);
        // chia tỉ lệ
        //int height = parent.getMeasuredHeight();
        int height = parent.getMeasuredHeight() / mCategories.size();
        heroView.getLayoutParams().height = height;

        ViewHolder viewHolder = new ViewHolder(heroView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Category category = mCategories.get(position);
        holder.mImageHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCallback.onClickItem(category);
            }
        });
        Picasso.get()
                .load(category.getUrl())
                .placeholder(R.drawable.splash_sreen)
                .into(holder.mImageHero);
        holder.mTextName.setText(category.getName());

    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageHero;
        private TextView mTextName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageHero = itemView.findViewById(R.id.imgCategory);
            mTextName = itemView.findViewById(R.id.nameCategory);
        }

    }

    interface Callback {
        void onClickItem(Category category);
    }
}
