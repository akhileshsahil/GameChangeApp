package com.gamechange.gamechangeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamechange.gamechangeapp.R;
import com.gamechange.gamechangeapp.databinding.CommentListItemBinding;
import com.gamechange.gamechangeapp.databinding.IssueListItemBinding;
import com.gamechange.gamechangeapp.issuedetails.IssueDetailsActivity;
import com.gamechange.gamechangeapp.model.Comments;
import com.gamechange.gamechangeapp.model.IssueData;

import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class CommentsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    List<Comments> issueDataList;

    public class CommentsViewHolder extends RecyclerView.ViewHolder {

        CommentListItemBinding binding;

        public CommentsViewHolder(CommentListItemBinding binding)
        {
            super(binding.getRoot());
            this.binding=binding;

        }
    }


    public CommentsListAdapter(List<Comments> issueDataList, Context context) {
        this.issueDataList = issueDataList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            CommentListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.comment_list_item, parent, false);
            return new CommentsViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {

         CommentsViewHolder holder = (CommentsViewHolder) viewHolder;
         holder.binding.txtUserName.setText("By:"+issueDataList.get(position).getUser().getLogin());
         String body = issueDataList.get(position).getBody();
         if(!StringUtils.isEmpty(body)) {
             if(body.length()> 140) {
                 Spanned htmlAsSpanned = Html.fromHtml(body.substring(0, 140)+"...");
                 holder.binding.txtBody.setText(htmlAsSpanned);
             }
             else{
                 Spanned htmlAsSpanned = Html.fromHtml(body);
                 holder.binding.txtBody.setText(htmlAsSpanned);
             }
         }


    }

    @Override
    public int getItemCount() {

        try {
            return issueDataList.size();
        }
        catch (Exception e)
        {
            return 0;
        }

    }



}