package com.vinay.wizdem.vnytimes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vinay.wizdem.vnytimes.R;
import com.vinay.wizdem.vnytimes.eventbus.BooksListToDetailsEvent;
import com.vinay.wizdem.vnytimes.models.books.BooksListResult;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by vinay_1 on 10/26/2017.
 */

public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.BooksListViewHolder> {

    private List<BooksListResult> booksListResults;
    private Context mContext;
    public BooksListAdapter(List<BooksListResult> booksListResults, Context context){
        this.booksListResults = booksListResults;
        this.mContext = context;
    }

    @Override
    public BooksListAdapter.BooksListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_list_item,parent,false);
        return new BooksListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BooksListViewHolder holder, int position) {
        final String title = booksListResults.get(position).getBookDetails().get(0).getTitle();
        final String author = "By "+booksListResults.get(position).getBookDetails().get(0).getAuthor();
        holder.book_title.setText(title);
        holder.book_author.setText(author);
        holder.book_description.setText(booksListResults.get(position).getBookDetails().get(0).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new BooksListToDetailsEvent(title,author));
            }
        });

    }

    @Override
    public int getItemCount() {
        return booksListResults.size();
    }

    public class BooksListViewHolder extends RecyclerView.ViewHolder {
        LinearLayout books_list_layout;
        TextView book_title;
        TextView book_author;
        TextView book_description;
        public BooksListViewHolder(View itemView) {
            super(itemView);
            books_list_layout = (LinearLayout)itemView.findViewById(R.id.books_list_layout);
            book_title = (TextView)itemView.findViewById(R.id.book_title);
            book_author = (TextView)itemView.findViewById(R.id.book_author);
            book_description = (TextView)itemView.findViewById(R.id.book_description);
        }
    }
}
