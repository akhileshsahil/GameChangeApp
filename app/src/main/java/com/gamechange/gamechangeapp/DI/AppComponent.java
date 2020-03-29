package com.gamechange.gamechangeapp.DI;

import com.gamechange.gamechangeapp.issuedetails.IssueDetailsActivity;
import com.gamechange.gamechangeapp.issuelist.MainActivity;

import javax.inject.Singleton;
import dagger.Component;


@Component(modules = {AppModule.class, ApiModule.class})
@Singleton
public interface AppComponent {

    void doInjection(MainActivity mainActivity);
    void doInjection(IssueDetailsActivity issueDetailsActivity);

}