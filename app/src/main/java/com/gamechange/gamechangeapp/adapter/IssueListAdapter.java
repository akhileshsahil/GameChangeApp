package com.gamechange.gamechangeapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamechange.gamechangeapp.R;
import com.gamechange.gamechangeapp.databinding.IssueListItemBinding;
import com.gamechange.gamechangeapp.issuedetails.IssueDetailsActivity;
import com.gamechange.gamechangeapp.model.IssueData;

import org.apache.commons.lang3.StringUtils;

import java.util.List;



public class IssueListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    List<IssueData> issueDataList;

    public class IssueViewHolder extends RecyclerView.ViewHolder {

        IssueListItemBinding binding;

        public IssueViewHolder(IssueListItemBinding binding)
        {
            super(binding.getRoot());
            this.binding=binding;

        }
    }


    public IssueListAdapter(List<IssueData> issueDataList, Context context) {
        this.issueDataList = issueDataList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            IssueListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.issue_list_item, parent, false);
            return new IssueViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {

        int number = issueDataList.get(position).getNumber();
         IssueViewHolder holder = (IssueViewHolder) viewHolder;
         String title = issueDataList.get(position).getTitle();
         holder.binding.txtTitle.setText(Html.fromHtml(title));
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

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context, IssueDetailsActivity.class);
                 intent.putExtra("number",number);
                 intent.putExtra("title",title);
                 intent.putExtra("body",body);
                 context.startActivity(intent);
             }
         });

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