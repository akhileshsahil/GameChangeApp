package com.gamechange.gamechangeapp.issuedetails;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.gamechange.gamechangeapp.issuelist.IssueDataRepository;
import com.gamechange.gamechangeapp.model.Comments;
import com.gamechange.gamechangeapp.model.IssueData;

import java.util.List;

public class CommentsViewModel extends ViewModel {


    private MutableLiveData<List<Comments>> mutableLiveData;
    private IssueDataRepository issueDataRepository;
    private int number;
    private MutableLiveData<Boolean> emptyData = new MutableLiveData<Boolean>();

    public CommentsViewModel(IssueDataRepository repository) {
        this.issueDataRepository = repository;
    }
    public void init(int number){
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        this.number = number;
        //issueDataRepository = IssueDataRepository.getInstance();
        mutableLiveData = (MutableLiveData<List<Comments>>) issueDataRepository.getComments(number);
    }

    public MutableLiveData<List<Comments>> getComments(){

        return mutableLiveData;
    }

}
