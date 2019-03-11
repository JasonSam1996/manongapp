package com.jason.manongapp.find.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.http.RxHttpUtils;
import com.jason.manongapp.base.http.interceptor.Transformer;
import com.jason.manongapp.base.http.observer.CommonObserver;
import com.jason.manongapp.find.R;
import com.jason.manongapp.find.R2;
import com.jason.manongapp.find.bean.CommentBean;
import com.jason.manongapp.find.bean.ZhiHuNewNewsBean;
import com.jason.manongapp.find.fragment.ApiService;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<ZhiHuNewNewsBean.StoriesBean> dataList;
    private AdapterCallBack callBack;


    public RecyclerViewAdapter(Context context, List<ZhiHuNewNewsBean.StoriesBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public List<ZhiHuNewNewsBean.StoriesBean> getDataList() {
        return dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.find_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final ZhiHuNewNewsBean.StoriesBean zhiHuNewNewsBean = dataList.get(i);
        if (zhiHuNewNewsBean.getImages().size() == 0) {
            Logger.i("onBindViewHolder: images空");
        }
        String url = zhiHuNewNewsBean.getImages().get(0);
        Uri uri = Uri.parse(url);
        viewHolder.find_news_image.setImageURI(uri);
        viewHolder.find_news_title.setText(zhiHuNewNewsBean.getTitle());
        RxHttpUtils.createApi(ApiService.class)
                .getComments(String.valueOf(zhiHuNewNewsBean.getId()))
                .compose(Transformer.<CommentBean>switchSchedulers())
                .subscribe(new CommonObserver<CommentBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        Logger.i("onError: " + errorMsg);
                    }

                    @Override
                    protected void onSuccess(CommentBean commentBean) {
//                        Logger.i("onSuccess: " + commentBean.toString());
                        viewHolder.tvGoodNum.setText(String.valueOf(commentBean.getPopularity()));
                        viewHolder.tvCommentNum.setText(String.valueOf(commentBean.getComments()));
                    }
                });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.getIdCallBack(zhiHuNewNewsBean.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.find_news_title_image)
        SimpleDraweeView find_news_image;

        @BindView(R2.id.find_news_title)
        TextView find_news_title;

        @BindView(R2.id.find_news_goodsnum)
        TextView tvGoodNum;

        @BindView(R2.id.find_news_commentnum)
        TextView tvCommentNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public void getNewsIDCallBack(AdapterCallBack callBack) {
        this.callBack = callBack;
    }


    public interface AdapterCallBack {
        void getIdCallBack(int newsId);
    }



    public void addData(ZhiHuNewNewsBean.StoriesBean storiesBean){
        if (dataList != null && dataList.size()>0) {
            dataList.add(storiesBean);
        }
    }

}
