package com.gamechange.gamechangeapp.issuelist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.gamechange.gamechangeapp.R;
import com.gamechange.gamechangeapp.adapter.IssueListAdapter;
import com.gamechange.gamechangeapp.application.GameChangeApplication;
import com.gamechange.gamechangeapp.databinding.ActivityMainBinding;
import com.gamechange.gamechangeapp.model.IssueData;
import com.gamechange.gamechangeapp.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    ActivityMainBinding binding;
    ListViewModel listViewModel;
    List<IssueData> issueDataList;
    IssueListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        issueDataList = new ArrayList<>();
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("Issues");
        binding.toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        adapter = new IssueListAdapter(issueDataList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(mLayoutManager);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                binding.recyclerView.getContext(),
                mLayoutManager.getOrientation()
        );
        mDividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        binding.recyclerView.addItemDecoration(mDividerItemDecoration);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

        ((GameChangeApplication) getApplication()).getAppComponent().doInjection(this);
        //listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);
        listViewModel.init();
        listViewModel.getIssueData().observe(this, issueDataObserver);
    }

    Observer<List<IssueData>> issueDataObserver = new Observer<List<IssueData>>() {
        @Override
        public void onChanged(List<IssueData> issueDataList) {
            MainActivity.this.issueDataList.addAll(issueDataList);
            adapter.notifyDataSetChanged();
        }
    };
}
