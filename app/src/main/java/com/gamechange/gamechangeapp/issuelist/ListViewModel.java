package com.gamechange.gamechangeapp.issuelist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.gamechange.gamechangeapp.model.IssueData;

import java.util.List;

public class ListViewModel extends ViewModel {


    private MutableLiveData<List<IssueData>> mutableLiveData;
    private IssueDataRepository issueDataRepository;

    public ListViewModel(IssueDataRepository repository) {
        this.issueDataRepository = repository;
    }
    public void init(){
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        //issueDataRepository = IssueDataRepository.getInstance();
        mutableLiveData = (MutableLiveData<List<IssueData>>) issueDataRepository.getIssueData();
    }

    public MutableLiveData<List<IssueData>> getIssueData(){

        return mutableLiveData;
    }

}
