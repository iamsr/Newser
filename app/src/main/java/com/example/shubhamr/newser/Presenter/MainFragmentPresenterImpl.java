package com.example.shubhamr.newser.Presenter;

import com.example.shubhamr.newser.Interface.Contract;
import com.example.shubhamr.newser.Interface.Contract.MainFragmentPresenter;
import com.example.shubhamr.newser.Interface.Contract.MainFragmentView;
import com.example.shubhamr.newser.Interface.Contract.NewsAPI;
import com.example.shubhamr.newser.ModelClass.News;
import com.example.shubhamr.newser.ModelClass.Source;

import java.util.List;

public class MainFragmentPresenterImpl implements MainFragmentPresenter,Contract.OnFinishedListener {

    // Creating instance of view and model for accessing their methods
    private MainFragmentView mainFragmentView;
    private NewsAPI newsAPI;

    private final String CATEGORY = "category";
    private final String SOURCE = "sources";


    // Passed form view
    public MainFragmentPresenterImpl(MainFragmentView mainFragmentView, NewsAPI newsAPI){
     this.mainFragmentView = mainFragmentView;
     this.newsAPI = newsAPI;
    }



    /*

       These three methods calls method in model for

       network query according to their types

     */

    // Retrieving list of sources from model
    @Override
    public void getSources() {

        // Show Progress Bar and invoke model method for retrieving source list in model
        mainFragmentView.showProgressBar();
        newsAPI.getSourceList(this);

    }



    // Retrieving list of news by category  from model
    @Override
    public void getNewsByCategory(String category) {

        mainFragmentView.showProgressBar();

        // calling model method with query = "category" and value equals to argument passed from view
        newsAPI.getNews(CATEGORY,category,this);

    }



    // Retrieving list of news by source name from model
    @Override
    public void getNewsBySource(String sourceName) {

        mainFragmentView.showProgressBar();

        // calling model method with query = "source" and value equals to argument passed from view
        newsAPI.getNews(SOURCE,sourceName,this);

    }



    /*

       When Networking task finished following methods called by model to tell presenter about it

       the respective method called by model according to query


     */


    // When sourceList retrieved
    @Override
    public void onFinishedSourceList(List<Source> sourceList) {

        // When source retrieving task finished
        mainFragmentView.hideProgressBar();

        // Check that network operation  was successful or not
        if(sourceList==null){

            // If failed show error on screen
            mainFragmentView.showError();
        }

        else{

            // If successful update UI
            mainFragmentView.updateHomeRecyclerView(sourceList);

        }
    }



    // When newsList by category retrieved
    @Override
    public void onFinishedNewsList(List<News> newsList) {

        // When source retrieving task finished
        mainFragmentView.hideProgressBar();

        // Check that network operation  was successful or not
        if(newsList==null){

            // If failed show error on screen
            mainFragmentView.showError();
        }

        else{

            // If successful update UI
            mainFragmentView.updateNewsRecyclerView(newsList);

        }



    }



}
