package com.vinay.wizdem.vnytimes.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vinay.wizdem.vnytimes.R;
import com.vinay.wizdem.vnytimes.eventbus.MessageEvent;
import com.vinay.wizdem.vnytimes.models.articles.Result;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by vinay_1 on 10/25/2017.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder> {

    private List<Result> results;
    private Context mContext;

    public ArticleListAdapter(List<Result> results,Context context){
        this.results = results;
        this.mContext = context;
    }
    @Override
    public ArticleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_item,parent,false);
        return new ArticleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleListViewHolder holder, final int position) {
        Uri uri;
        if(results.get(position).getMultimedia().isEmpty()){
            uri = null;
        }else uri = Uri.parse(results.get(position).getMultimedia().get(1).getUrl());

        holder.article_title.setText(results.get(position).getTitle());
        holder.article_by.setText(results.get(position).getByline());
        holder.thumb_nail.setImageURI(uri);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!results.get(position).getUrl().isEmpty()){
                    EventBus.getDefault().post(new MessageEvent(results.get(position).getUrl()));
                }else Toast.makeText(mContext,"Article source not found!!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ArticleListViewHolder extends RecyclerView.ViewHolder {

        LinearLayout article_list_layout;
        ImageView thumb_nail;
        TextView article_title;
        TextView article_by;
        public ArticleListViewHolder(View itemView) {
            super(itemView);

            article_list_layout = (LinearLayout)itemView.findViewById(R.id.article_list_layout);
            thumb_nail = (ImageView)itemView.findViewById(R.id.article_thumb_nail);
            article_title = (TextView)itemView.findViewById(R.id.article_title);
            article_by = (TextView)itemView.findViewById(R.id.article_by);
        }
    }
}
