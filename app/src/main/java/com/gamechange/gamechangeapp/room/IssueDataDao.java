package com.gamechange.gamechangeapp.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gamechange.gamechangeapp.model.IssueData;
import java.util.List;

@Dao
public interface IssueDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertTask(IssueData issueData);

    @Query("SELECT * FROM IssueData ORDER BY updated_at desc")
    List<IssueData> getAllIssueData();
}
