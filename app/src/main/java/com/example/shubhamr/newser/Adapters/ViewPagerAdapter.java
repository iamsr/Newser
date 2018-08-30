package com.example.shubhamr.newser.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shubhamr.newser.Views.Fragment.MainFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    // Number of tabs
    private int tabNum;

    // CATEGORY
    private final String CATEGORY = "CATEGORY";
    private final String SCIENCE= "science";
    private final String HEALTH= "health";
    private final String BUSINESS= "business";
    private final String SPORTS= "sports";
    private final String TECHNOLOGY= "technology";
    private final String ENTERTAINMENT= "entertainment";


    public ViewPagerAdapter(FragmentManager fm,int tabNum){
        super(fm);
        this.tabNum=tabNum;
    }



    // Return fragment according to selected tab
    @Override
    public Fragment getItem(int position) {

        // Fragment that has to be return with extra data inside bundle
        Fragment fragment;
        Bundle bundle = new Bundle();

        // Return fragment according to position of tab
       switch(position){

           // CASE 0 for HOME tab
           case 0:
               fragment = new MainFragment();
               break;

            // CASE 1 for SCIENCE tab
           case 1:
               fragment = new MainFragment();
               bundle.putString(CATEGORY,SCIENCE);
               break;

            // CASE 2 for HEALTH tab
            case 2 :
                fragment = new MainFragment();
                bundle.putString(CATEGORY,HEALTH);
                break;

            // CASE 3 for BUSINESS tab
           case 3 :
               fragment = new MainFragment();
               bundle.putString(CATEGORY,BUSINESS);
               break;

            // CASE 4 for SPORTS tab
           case 4 :
               fragment = new MainFragment();
               bundle.putString(CATEGORY,SPORTS);
               break;

            // CASE 5 for TECH tab
           case 5 :
               fragment = new MainFragment();
               bundle.putString(CATEGORY,TECHNOLOGY);
               break;

             // CASE 6 for ENTERTAINMENT tab
            case 6 :
               fragment = new MainFragment();
               bundle.putString(CATEGORY,ENTERTAINMENT);
               break;

              // By default return null
             default:
                 return null;
       }

       // Set argument for fragment and return
        fragment.setArguments(bundle);
        return fragment;
    }


    // Return number of tabs in tabLayout
    @Override
    public int getCount() {
        return tabNum;
    }
}
