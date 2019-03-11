package com.jason.manongapp.diary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jason.manongapp.diary.R;
import com.jason.manongapp.diary.R2;
import com.jason.manongapp.diary.bean.EmojiBean;
import com.jason.manongapp.diary.utils.ExpressionUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe: 表情的Adapter
 * Author: Created by Went_Gone on 2016/10/26.
 */

public class ExpressionAdapter extends RecyclerView.Adapter<ExpressionAdapter.ExpressionViewHolder>{
    private List<EmojiBean> emojiBeens;
    private Context context;

    public ExpressionAdapter(List<EmojiBean> emojiBeens, Context context) {
        this.emojiBeens = emojiBeens;
        this.context = context;
    }

    @Override
    public ExpressionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.diary_item_expression_layout,null);
        ExpressionViewHolder holder = new ExpressionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ExpressionViewHolder holder, final int position) {
        String emojiString = ExpressionUtil.getEmojiStringByUnicode(emojiBeens.get(position).getUnicodeInt());
        holder.tv.setText(emojiString);
        if (itemChildClickListener!=null){
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemChildClickListener.onChildClick(position);
                }
            });
        }
    }

    public EmojiBean getItem(int position){
        return emojiBeens.get(position);
    }

    @Override
    public int getItemCount() {
        return emojiBeens.size();
    }

    class ExpressionViewHolder extends RecyclerView.ViewHolder{
        @BindView(R2.id.item_expression_layout_TV)
        TextView tv;
        public ExpressionViewHolder(View itemView) {
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
