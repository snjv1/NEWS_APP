package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LoaderManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsListAdapter mnewsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find recycler view layout
        // set type of layout on screen
        recyclerView=findViewById(R.id.recylerView123);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // api run krke data fetch krlo
        fetchdata();
        mnewsListAdapter = new NewsListAdapter(MainActivity.this);

        // recycler view m abb adapter daal diya or recyclwer view us adaoter ko dikhaeyga
        recyclerView.setAdapter(mnewsListAdapter);


    }

    private  void fetchdata()
    {
        String url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {



                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray newsJsonArray = response.getJSONArray("articles");

                            ArrayList<News> newsArray = new ArrayList<>();
                            for(int i=0; i<newsJsonArray.length(); i++)
                            {
                                JSONObject newsJsonObject = newsJsonArray.getJSONObject(i);
                                News news = new News(
                                        newsJsonObject.getString("title"),
                                newsJsonObject.getString("author"),
                                newsJsonObject.getString("url"),
                                newsJsonObject.getString("urlToImage")
                                );
                                newsArray.add(news);
                            }

                            mnewsListAdapter.updateNews(newsArray);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();


                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

}