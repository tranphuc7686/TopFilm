package com.example.topfilm.rank;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.topfilm.R;
import com.example.topfilm.model.Film;
import com.example.topfilm.model.Film;
import com.squareup.picasso.Picasso;

import java.util.List;

//public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
//
//    private List<Film> films;
//    private RankAdapter.Callback onClickCallback;
//
//    public RankAdapter(List<Film> films, RankAdapter.Callback callback) {
//        this.films = films;
//        this.onClickCallback = callback;
//    }
//
//    @NonNull
//    @Override
//    public RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View heroView = inflater.inflate(R.layout.item_rank, parent, false);
//        RankAdapter.ViewHolder viewHolder = new RankAdapter.ViewHolder(heroView);
//
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RankAdapter.ViewHolder holder, int position) {
//        final Film film = films.get(position);
//        holder.mImageHero.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClickCallback.onClickItem(film);
//            }
//        });
//        Picasso.get()
//                .load(film.getUrl())
//                .placeholder(R.drawable.splash_sreen)
//                .into(holder.mImageHero);
//        Picasso.get()
//                .load(film.getUrl())
//                .placeholder(R.drawable.splash_sreen)
//                .into(holder.imgThumbnail);
//        holder.mTextName.setText(film.getName());
//        holder.mTextNameThumbnail.setText(film.getName());
//        holder.mPoint.setText(film.getPoint()+"");
//        holder.mRank.setText(position+1+"");
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return films.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private ImageView mImageHero,imgThumbnail;
//        private TextView mTextName,mTextNameThumbnail,mPoint,mRank;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            mImageHero = itemView.findViewById(R.id.imgFilm);
//            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
//            mTextName = itemView.findViewById(R.id.txtNameFilm);
//            mTextNameThumbnail = itemView.findViewById(R.id.txtNameFilmThubnail);
//            mPoint = itemView.findViewById(R.id.txtPoint);
//            mRank = itemView.findViewById(R.id.txtRank);
//        }
//
//    }
//
//    interface Callback {
//        void onClickItem(Film Film);
//    }
//}

public class RankAdapter extends PagerAdapter {
    private LayoutInflater mInflater;
    private List<Film> films;
    private Callback callback;

    public RankAdapter(List<Film> films,Callback callback) {
        this.films = films;
        this.callback = callback;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        mInflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.item_rank, container, false);
        final Film film = films.get(position);
        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //this will log the page number that was click
                callback.onClickItem(film);
            }
        });
        Picasso.get()
                .load(film.getUrl())
                .placeholder(R.drawable.splash_sreen)
                .into((ImageView) view.findViewById(R.id.imgFilm));
        Picasso.get()
                .load(film.getUrl())
                .placeholder(R.drawable.splash_sreen)
                .into((ImageView) view.findViewById(R.id.imgThumbnail));
        String nameFilm = film.getName().length() > 17 ? film.getName().substring(0,17) +"..." : film.getName();
        ((TextView)view.findViewById(R.id.txtNameFilm)).setText(nameFilm);
        ((TextView)view.findViewById(R.id.txtNameFilmThubnail)).setText(nameFilm);
        ((TextView)view.findViewById(R.id.txtPoint)).setText(film.getPoint() + "");
        ((TextView)view.findViewById(R.id.txtRank)).setText(position + 1 + "");

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return films.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    interface Callback {
        void onClickItem(Film Film);
    }
}