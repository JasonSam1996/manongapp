package com.jason.manongapp.find.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import me.relex.photodraweeview.PhotoDraweeView;

public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private List<SimpleDraweeView> photoDraweeViews;

    public ImagePagerAdapter(Context context, List<SimpleDraweeView> photoDraweeViews) {
        this.context = context;
        this.photoDraweeViews = photoDraweeViews;
    }

    @Override
    public int getCount() {
        return photoDraweeViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position) {
        SimpleDraweeView photoDraweeView = photoDraweeViews.get(position);
        container.addView(photoDraweeView);
        return photoDraweeView;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
//        super.destroyItem(container, position, object);
    }
}
