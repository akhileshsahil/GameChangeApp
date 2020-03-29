package com.gamechange.gamechangeapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.support.annotation.NonNull;

import com.gamechange.gamechangeapp.room.UserConverter;

import java.io.Serializable;

@Entity
public class IssueData implements Serializable {

    private String comments;


    private String created_at;

    private String title;

    private String body;

    private String url;


    private int number;


    private String updated_at;

    @PrimaryKey
    @NonNull
    private String id;

    private String state;

   // private User user;


    public String getComments ()
    {
        return comments;
    }

    public void setComments (String comments)
    {
        this.comments = comments;
    }


//    public String[] getAssignees ()
//    {
//        return assignees;
//    }
//
//    public void setAssignees (String[] assignees)
//    {
//        this.assignees = assignees;
//    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }


    public int getNumber ()
    {
        return number;
    }

    public void setNumber (int number)
    {
        this.number = number;
    }


    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }



    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }


//    public User getUser ()
//    {
//        return user;
//    }
//
//    public void setUser (User user)
//    {
//        this.user = user;
//    }


    @Override
    public String toString()
    {
        return "ClassPojo [comments = "+comments+", created_at = "+created_at+", title = "+title+", body = "+body+", url = "+url+", number = "+number+", updated_at = "+updated_at+", id = "+id+", state = "+state+", user = "+"]";
    }
}


