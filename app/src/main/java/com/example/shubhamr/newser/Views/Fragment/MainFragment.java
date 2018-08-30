package com.example.shubhamr.newser.Views.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shubhamr.newser.Adapters.NewsRecyclerViewAdapter;
import com.example.shubhamr.newser.Adapters.SourceRecyclerViewAdapter;
import com.example.shubhamr.newser.Interface.ClickListenerInterface;
import com.example.shubhamr.newser.Interface.Contract.MainFragmentPresenter;
import com.example.shubhamr.newser.Interface.Contract.MainFragmentView;
import com.example.shubhamr.newser.Model.NewsAPIImpl;
import com.example.shubhamr.newser.ModelClass.News;
import com.example.shubhamr.newser.ModelClass.Source;
import com.example.shubhamr.newser.Presenter.MainFragmentPresenterImpl;
import com.example.shubhamr.newser.R;
import com.example.shubhamr.newser.Views.Activity.NewsDisplayActivity;
import com.example.shubhamr.newser.Views.Activity.SourceActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainFragment extends Fragment implements MainFragmentView, ClickListenerInterface {

    @BindView(R.id.fragmentRecyclerView)RecyclerView fragmentRecyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.retryButton) Button retryButton;
    @BindView(R.id.errorTextView) TextView errorText;

    // Presenter instance for communicating with presenter
    public MainFragmentPresenter mainFragmentPresenter;

    // News and Source RecyclerView Adapters
    public SourceRecyclerViewAdapter sourceListRecyclerViewAdapter;
    public NewsRecyclerViewAdapter newsRecyclerViewAdapter;


    public String CATEGORY;
    public String SOURCE;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieving argument passed to fragment while creating
        if (getArguments() != null) {
            CATEGORY = getArguments().getString("CATEGORY");
            SOURCE = getArguments().getString("SOURCE");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);

        // Presenter implementation object
        mainFragmentPresenter = new MainFragmentPresenterImpl(this,new NewsAPIImpl(getContext()));

       // Find type of fragment and query accordingly
       getTypeOfFragment();


        return view;

    }

    // Method find type of fragment and query data from presenter accordingly
    public void getTypeOfFragment(){

         /* Retrieve list of data according to selected fragment
          As same fragment is being used in whole application
          we are deciding the method of data retrieving according to type under which fragment called

          Three situation for fragment :

          1. Under category where news are being displayed according to category
          2. Under source where news are being displayed according to source
          3. Under home tab here only list of sources being displayed

        These if else statement check under which situation the fragment is initiated by checking argument
        passed to fragment by calling activity

        */


        // Telling presenter to retrieve news list for category tab;
        if(CATEGORY!=null) {

            mainFragmentPresenter.getNewsByCategory(CATEGORY);

        }

        // Tell presenter to retrieve news according to source name for sourceActivity
        else if(SOURCE!=null){

            mainFragmentPresenter.getNewsBySource(SOURCE);

        }


        // Telling presenter to retrieve source list for home tab;
        else{

            mainFragmentPresenter.getSources();

        }


    }



    // Initialize and update Source List Recycler View for Home tab
    @Override
    public void updateHomeRecyclerView(List<Source> sourceList) {

        //Setting up recycler view and adapter
        fragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        sourceListRecyclerViewAdapter  =new SourceRecyclerViewAdapter(sourceList);
        sourceListRecyclerViewAdapter.setClickListeners(this);
        fragmentRecyclerView.setAdapter(sourceListRecyclerViewAdapter);

    }



    /*
       since we are using same recycler view for all news whether its from source or category
       so for any type of news list this recycler view will e shown
     */
    @Override
    public void updateNewsRecyclerView(List<News> newsList) {

        //Setting up recycler view and adapter
        fragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(newsList);
        newsRecyclerViewAdapter.setClickListeners(this);
        fragmentRecyclerView.setAdapter(newsRecyclerViewAdapter);

    }



    @Override
    public void showProgressBar() {
      progressBar.setVisibility(View.VISIBLE);
    }



    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }



    @Override
    public void showError() {
        errorText.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.VISIBLE);
    }



    // For sending context to model for performing network operation
    @Override
    public Context getContext(){
        return getActivity().getApplicationContext();
    }




    // When retry button clicked
    @OnClick(R.id.retryButton)
    public void onRetry(){

        // Hide button and text when retry clicked and repeat networking process
        errorText.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
        getTypeOfFragment();
    }




    // When recycler view item clicked
    @Override
    public void itemClicked(View view, int position) {


        /*
          If Current fragment is of category type or source type then
          we just have to take url from selected item and pass it to
          news display activity

          else if it is item of source list where name of source are listed then
          we have to pass name of source to the source activity

         */


        // Fragment is type of category news or source news
        if(CATEGORY!=null||SOURCE!=null){

            // Retrieving nes object and passing url from it to the NewsDisplayActivity
            News news = newsRecyclerViewAdapter.getNewsFromList(position);
            Intent i = new Intent(getActivity(), NewsDisplayActivity.class);
            i.putExtra("URL",news.getURL());
            getActivity().startActivity(i);
        }


        // Fragment is type of home where source list is displayed
        else {
            // Get clicked source from the list
            Source source = sourceListRecyclerViewAdapter.getSourceFromList(position);

            //Create intent and put source name as extra
            Intent i = new Intent(getActivity(), SourceActivity.class);
            i.putExtra("name", source.getName());
            getActivity().startActivity(i);
        }

    }
}
