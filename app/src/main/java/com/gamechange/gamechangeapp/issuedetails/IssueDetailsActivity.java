package com.gamechange.gamechangeapp.issuedetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;

import com.gamechange.gamechangeapp.R;
import com.gamechange.gamechangeapp.adapter.CommentsListAdapter;
import com.gamechange.gamechangeapp.application.GameChangeApplication;
import com.gamechange.gamechangeapp.databinding.ActivityIssueDetailsBinding;
import com.gamechange.gamechangeapp.model.Comments;
import com.gamechange.gamechangeapp.util.ViewModelFactory;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class IssueDetailsActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    ActivityIssueDetailsBinding binding;
    private CommentsViewModel commentsViewModel;
    private List<Comments> commentsList;
    private CommentsListAdapter adapter;
    private int number;
    private String title,body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        number = intent.getIntExtra("number",0);
        title = intent.getStringExtra("title");
        body = intent.getStringExtra("body");
        commentsList = new ArrayList<>();
        binding = DataBindingUtil.setContentView(IssueDetailsActivity.this, R.layout.activity_issue_details);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        binding.toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        binding.txtTitle.setText(Html.fromHtml(title));

        if(!StringUtils.isEmpty(body)) {
            if(body.length()> 140) {
                Spanned htmlAsSpanned = Html.fromHtml(body.substring(0, 140)+"...");
                binding.txtBody.setText(htmlAsSpanned);
            }
            else{
                Spanned htmlAsSpanned = Html.fromHtml(body);
                binding.txtBody.setText(htmlAsSpanned);
            }
        }

        adapter = new CommentsListAdapter(commentsList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                binding.recyclerView.getContext(),
                mLayoutManager.getOrientation()
        );
        mDividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider1));
        binding.recyclerView.addItemDecoration(mDividerItemDecoration);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

        ((GameChangeApplication) getApplication()).getAppComponent().doInjection(this);
        commentsViewModel = ViewModelProviders.of(this, viewModelFactory).get(CommentsViewModel.class);
        //commentsViewModel = ViewModelProviders.of(this).get(CommentsViewModel.class);
        commentsViewModel.init(number);
        commentsViewModel.getComments().observe(this, commentsDataObserver);
    }

    Observer<List<Comments>> commentsDataObserver = new Observer<List<Comments>>() {
        @Override
        public void onChanged(List<Comments> issueDataList) {
            if(issueDataList == null || issueDataList.isEmpty()){
                updateView(false);
            }
            else{
                IssueDetailsActivity.this.commentsList.addAll(issueDataList);
                adapter.notifyDataSetChanged();
                updateView(true);
            }

        }
    };

    Observer<String> messageObserver = new Observer<String>() {
        @Override
        public void onChanged(String message) {
            if(!StringUtils.isEmpty(message)){
                binding.recyclerView.setVisibility(View.GONE);
                binding.txtNoComment.setVisibility(View.VISIBLE);
            }
            else{
                binding.recyclerView.setVisibility(View.VISIBLE);
                binding.txtNoComment.setVisibility(View.GONE);
            }
        }
    };

    public void updateView(boolean bool){
        if(!bool){
            binding.recyclerView.setVisibility(View.GONE);
            binding.txtNoComment.setVisibility(View.VISIBLE);
        }
        else{
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.txtNoComment.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
