package com.jason.manongapp.diary.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.diary.R;
import com.jason.manongapp.diary.R2;
import com.jason.manongapp.diary.bean.DiaryBean;
import com.jason.manongapp.diary.bean.EmojiBean;
import com.jason.manongapp.diary.utils.ExpressionUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe: 表情的Adapter
 * Author: Created by Went_Gone on 2016/10/26.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>{
    private List<DiaryBean.ResultsBean> diaryBeans;
    private Context context;

    public DiaryAdapter(List<DiaryBean.ResultsBean> diaryBeans, Context context) {
        this.diaryBeans = diaryBeans;
        this.context = context;
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.diary_item,null);
        DiaryViewHolder holder = new DiaryViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(DiaryViewHolder holder, final int position) {
        DiaryBean.ResultsBean resultsBean = diaryBeans.get(position);
        String imageUrl;

        if (!resultsBean.getDiary_content().contains("img src=")) {
            Logger.i("没有图片");
            imageUrl = "https://i.kfs.io/playlist/global/47130192v1/fit/500x500.jpg";
        }else {
            int index = resultsBean.getDiary_content().indexOf("http://bmob");
            int end = resultsBean.getDiary_content().indexOf(".jpg") + 4;
            imageUrl = resultsBean.getDiary_content().substring(index,end);
        }

        String date = resultsBean.getCreatedAt().substring(0,7);
        String dayMonth = resultsBean.getCreatedAt().substring(8,10);
        String time = resultsBean.getCreatedAt().substring(11,16);
        holder.tvDate.setText(date);
        holder.tvDayMonth.setText(dayMonth);
        holder.tvDayWeek.setText(resultsBean.getWeek());
        holder.tvTime.setText(time);
        holder.tvTitle.setText(resultsBean.getDiary_title());
        holder.tvCityAndWeather.setText(resultsBean.getCity() + " "+resultsBean.getWeather());
        holder.simpleDraweeView.setImageURI(Uri.parse(imageUrl));
    }


    @Override
    public int getItemCount() {
        return diaryBeans.size();
    }

    class DiaryViewHolder extends RecyclerView.ViewHolder{
        /*@BindView(R2.id.item_expression_layout_TV)
        TextView tv;*/

        @BindView(R2.id.diary_item_day_month)
        TextView tvDayMonth;

        @BindView(R2.id.diary_item_day_week)
        TextView tvDayWeek;

        @BindView(R2.id.diary_item_date)
        TextView tvDate;

        @BindView(R2.id.diary_item_image)
        SimpleDraweeView simpleDraweeView;

        @BindView(R2.id.diary_item_time)
        TextView tvTime;

        @BindView(R2.id.diary_item_title)
        TextView tvTitle;

        @BindView(R2.id.diary_item_city_and_weather)
        TextView tvCityAndWeather;

        public DiaryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
//            tv = (TextView) itemView.findViewById(R.id.item_expression_layout_TV);
        }
    }

    private ItemChildClickListener itemChildClickListener;

    public void setItemChildClickListener(ItemChildClickListener itemChildClickListener) {
        this.itemChildClickListener = itemChildClickListener;
    }

    public interface ItemChildClickListener {
        void onChildClick(int position);
    }

}
