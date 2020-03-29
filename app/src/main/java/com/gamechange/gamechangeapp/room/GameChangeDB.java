package com.gamechange.gamechangeapp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.databinding.adapters.Converters;

import com.gamechange.gamechangeapp.model.IssueData;
import com.gamechange.gamechangeapp.model.User;
import com.gamechange.gamechangeapp.room.IssueDataDao;

@Database(entities = {IssueData.class, User.class}, version = 1, exportSchema = false)

public abstract class GameChangeDB extends RoomDatabase {

    public abstract IssueDataDao issueDataDao();
}
