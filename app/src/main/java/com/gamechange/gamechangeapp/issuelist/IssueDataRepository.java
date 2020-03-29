package com.gamechange.gamechangeapp.issuelist;


import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.gamechange.gamechangeapp.model.Comments;
import com.gamechange.gamechangeapp.model.IssueData;
import com.gamechange.gamechangeapp.networking.Api;
import com.gamechange.gamechangeapp.networking.RetrofitService;
import com.gamechange.gamechangeapp.room.GameChangeDB;
import com.gamechange.gamechangeapp.util.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssueDataRepository {


    private Api api;
    private Context context;

    private GameChangeDB gameChangeDB;

    public IssueDataRepository(Api api,Context context){
        this.api = api;
        this.context = context;
        gameChangeDB = Room.databaseBuilder(context, GameChangeDB.class, Constant.DB_NAME).build();
    }

    //  getting issue list
    public MutableLiveData<List<IssueData>> getIssueData(){
        final MutableLiveData<List<IssueData>> issueDataList = new MutableLiveData<>();
        getAllData(issueDataList);

        return issueDataList;
    }


    //  getting comments
    public MutableLiveData<List<Comments>> getComments(int number){
        final MutableLiveData<List<Comments>> commentsDataList = new MutableLiveData<>();
        api.getComments(number).enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {

                if (response.isSuccessful()){
                    commentsDataList.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                System.out.println("failure "+t.toString());
                commentsDataList.setValue(null);
            }
        });
        return commentsDataList;
    }

    public void getIssueDataFromAPI(MutableLiveData<List<IssueData>> issueDataList){
        api.getIssueList().enqueue(new Callback<List<IssueData>>() {
            @Override
            public void onResponse(Call<List<IssueData>> call, Response<List<IssueData>> response) {

                for(IssueData issueData: response.body()) {
                    System.out.println(issueData.toString());
                    insertTask(issueData);
                }
                if (response.isSuccessful()){
                    issueDataList.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<IssueData>> call, Throwable t) {
                System.out.println("failure "+t.toString());
                // issueDataList.setValue(null);
            }
        });
    }

    public void insertTask(final IssueData issueData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                gameChangeDB.issueDataDao().insertTask(issueData);
                return null;
            }
        }.execute();
    }

    public void getAllData(MutableLiveData<List<IssueData>> issueDataList) {

        new AsyncTask<Void, Void, List<IssueData> >() {
            @Override
            protected List<IssueData> doInBackground(Void... voids) {
                gameChangeDB.issueDataDao().getAllIssueData();
                return gameChangeDB.issueDataDao().getAllIssueData();
            }

            @Override
            protected void onPostExecute(List<IssueData> issueData) {
                super.onPostExecute(issueData);
                if(issueData == null || issueData.isEmpty()) {

                    getIssueDataFromAPI(issueDataList);
                }
                else{
                    issueDataList.setValue(issueData);
                }
            }
        }.execute();
    }

}
