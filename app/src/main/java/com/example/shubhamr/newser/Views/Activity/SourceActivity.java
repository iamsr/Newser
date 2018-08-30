package com.example.shubhamr.newser.Views.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.shubhamr.newser.R;
import com.example.shubhamr.newser.Views.Fragment.MainFragment;

import butterknife.ButterKnife;

public class SourceActivity extends AppCompatActivity {


    public String sourceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        ButterKnife.bind(this);

      // Source name selected from previous source list
        sourceName =   getIntent().getStringExtra("name");

      //Set source as title of action bar and back button on action bar
        getSupportActionBar().setTitle(sourceName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Initialize fragment for activity
      initializeFragment(sourceName);




    }

    // Initialization of fragment
    public void initializeFragment(String sourceName){

        // Load fragment which shows news by source name query
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();


        // If string have blank space replace it with '-'
        if(sourceName.contains(" ")){
            sourceName =  sourceName.replaceAll(" ","-");
        }

        bundle.putString("SOURCE",sourceName);
        fragment.setArguments(bundle);

        // creating a FragmentTransaction to begin the transaction and replace the Fragment
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);

        // save the changes
        fragmentTransaction.commit();



    }


    //Action bar back button pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
