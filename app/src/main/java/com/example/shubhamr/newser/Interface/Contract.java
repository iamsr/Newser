package com.example.shubhamr.newser.Interface;

import android.content.Context;

import com.example.shubhamr.newser.ModelClass.News;
import com.example.shubhamr.newser.ModelClass.Source;

import java.util.List;

public class Contract {

    //----------------------------> VIEW

    public interface BaseView{
        void showProgressBar();
        void hideProgressBar();
        void showError();
        Context getContext();
    }


    public interface MainFragmentView extends BaseView{

        void updateHomeRecyclerView(List<Source> sourceList);
        void updateNewsRecyclerView(List<News> newsList);

    }



    //-------------------------------------


    //----------------------------> PRESENTER

    public interface MainFragmentPresenter {

        void getSources();
        void getNewsByCategory(String category);
        void getNewsBySource(String sourceName);

    }


    //-------------------------------------



    //----------------------------> MODEL

    public interface NewsAPI{

        void getSourceList(OnFinishedListener listener);

        void getNews(String query,String value,OnFinishedListener listener);

    }


    //-------------------------------------


    public interface OnFinishedListener{
      void onFinishedSourceList(List<Source> list);
      void onFinishedNewsList(List<News> newsList);
    }




}
