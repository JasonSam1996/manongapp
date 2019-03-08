package com.jason.manongapp.more.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.manongapp.more.R;
import com.jason.manongapp.more.bean.SettingItemBean;

import java.util.List;


public class RecyclerviewLinearAdapter extends RecyclerView.Adapter<RecyclerviewLinearAdapter.ViewHolder> {

    private List<SettingItemBean> userItemBeans;

    public RecyclerviewLinearAdapter(List<SettingItemBean> UserItemBeans) {
        this.userItemBeans = UserItemBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_linearitem_2,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SettingItemBean UserItemBean = userItemBeans.get(position);
        holder.imageView.setBackgroundResource(UserItemBean.itemImage);
        holder.textView.setText(UserItemBean.itemText);
    }

    @Override
    public int getItemCount() {
        return userItemBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.more_settingitem_image);
            textView = itemView.findViewById(R.id.more_settingitem_text);
        }
    }

}
