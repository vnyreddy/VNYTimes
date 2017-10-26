package com.vinay.wizdem.vnytimes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vinay.wizdem.vnytimes.R;
import com.vinay.wizdem.vnytimes.models.books.AuthorHistoryResult;

import java.util.List;

/**
 * Created by vinay_1 on 10/26/2017.
 */

public class AuthorHistoryListAdapter extends
        RecyclerView.Adapter<AuthorHistoryListAdapter.AuthorHistoryListViewHolder> {
    private String selected_book_title;
    private String selected_book_author;
    private List<AuthorHistoryResult> authorHistoryResult;
    private Context mContext;

    public AuthorHistoryListAdapter(){

    }

    public AuthorHistoryListAdapter(List<AuthorHistoryResult> authorHistoryResultList, String title, String author, Context context) {
        this.authorHistoryResult = authorHistoryResultList;
        this.selected_book_title = title;
        this.selected_book_author = author;
        this.mContext = context;
    }

    @Override
    public AuthorHistoryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_history_list,parent,true);

        return new AuthorHistoryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AuthorHistoryListViewHolder holder, int position) {
        holder.author_book_title.setText(authorHistoryResult.get(position).getTitle());
      //  holder.author_book_description.setText(authorHistoryResult.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return authorHistoryResult.size();
    }

    public class AuthorHistoryListViewHolder extends RecyclerView.ViewHolder {
        LinearLayout author_history_layout;
        TextView author_book_title;
        TextView author_book_description;
        public AuthorHistoryListViewHolder(View itemView) {
            super(itemView);
            author_history_layout = (LinearLayout) itemView.findViewById(R.id.author_history_layout);
            author_book_title = (TextView) itemView.findViewById(R.id.author_book_title);
            author_book_description = (TextView)itemView.findViewById(R.id.author_book_description);

        }
    }
}
