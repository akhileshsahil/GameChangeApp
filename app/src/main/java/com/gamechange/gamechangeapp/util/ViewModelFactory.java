package com.gamechange.gamechangeapp.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.gamechange.gamechangeapp.issuedetails.CommentsViewModel;
import com.gamechange.gamechangeapp.issuelist.IssueDataRepository;
import com.gamechange.gamechangeapp.issuelist.ListViewModel;

import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private IssueDataRepository repository;
    @Inject
    public ViewModelFactory(IssueDataRepository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListViewModel.class)) {
            return (T) new ListViewModel(repository);
        }
        if (modelClass.isAssignableFrom(CommentsViewModel.class)) {
            return (T) new CommentsViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}