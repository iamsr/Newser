package com.example.shubhamr.newser.Model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shubhamr.newser.Interface.Contract;
import com.example.shubhamr.newser.Interface.Contract.OnFinishedListener;
import com.example.shubhamr.newser.ModelClass.News;
import com.example.shubhamr.newser.ModelClass.Source;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsAPIImpl implements Contract.NewsAPI {

    private Context context;
    private RequestQueue requestQueue;

    private final String API_KEY = "ed3d65722e6b43cd9d6867fb8d3bd1db";



    public NewsAPIImpl(Context context){
        this.context=context;
    }



    @Override
    public void getSourceList(final OnFinishedListener listener) {

        final List<Source> sourceList = new ArrayList<Source>();

        // RequestQueue instance created
        requestQueue = Volley.newRequestQueue(context);


        // URL from where source data has to be fetch
        String URL = "https://newsapi.org/v2/sources?apiKey="+API_KEY;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,URL,null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    // Get sources array from response
                    JSONArray sourceArray = response.getJSONArray("sources");

                    // Accessing single source from aray
                    for(int i=0;i<sourceArray.length();i++){

                        JSONObject sourceObject = sourceArray.getJSONObject(i);

                        // Making source object from response by retrieving (ID, NAME, DESCRIPTION)
                        Source source = new Source(sourceObject.getString("id"),sourceObject.getString("name"),
                                sourceObject.getString("description"));

                        sourceList.add(source);

                    }

                    // Returning retrieved source list to calling presenter
                    listener.onFinishedSourceList(sourceList);

                }

                catch (JSONException e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Returning null if some error occurred
                 listener.onFinishedSourceList(null);
            }
        });

        // Adding request in queue
        requestQueue.add(jsonObjectRequest);

    }


    @Override
    public void getNews(final String query, String value, final OnFinishedListener listener) {

        final List<News> newsList = new ArrayList<News>();

        // RequestQueue instance created
        requestQueue = Volley.newRequestQueue(context);


        // URL from where source data has to be fetch
        String URL = "https://newsapi.org/v2/top-headlines?"+query+"="+value+"&apiKey="+API_KEY;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,URL,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            // Get articles array from response
                            JSONArray newsArray = response.getJSONArray("articles");

                            // Accessing single news from array
                            for(int i=0;i<newsArray.length();i++){

                                JSONObject sourceObject = newsArray.getJSONObject(i);

                                // Making news object from response by retrieving (author,title,description,url,image url, published time)
                               News news = new News(sourceObject.getString("author"),sourceObject.getString("title"),
                                       sourceObject.getString("description"),sourceObject.getString("url"),
                                       sourceObject.getString("urlToImage"),sourceObject.getString("publishedAt"));

                               newsList.add(news);

                            }



                            // If query is for category news then call presenter method that handle category and pass it list
                            if(query=="category") {
                                listener.onFinishedNewsList(newsList);
                            }

                            // else query is for source news so call presenter method that handle source news and pass it list
                            else{
                                listener.onFinishedNewsList(newsList);
                            }

                        }

                        catch (JSONException e){
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Returning null if some error occurred
                listener.onFinishedNewsList(null);
            }
        });

        // Adding request in queue
        requestQueue.add(jsonObjectRequest);


    }

}
