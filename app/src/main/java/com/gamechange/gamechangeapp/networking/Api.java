package com.gamechange.gamechangeapp.networking;


import com.gamechange.gamechangeapp.model.Comments;
import com.gamechange.gamechangeapp.model.IssueData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("issues")
    Call<List<IssueData>> getIssueList();

    @GET("issues/{number}/comments")
    Call<List<Comments>> getComments(@Path("number") int number);

}

