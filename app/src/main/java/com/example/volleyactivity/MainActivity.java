package com.example.volleyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Context context;
    ArrayList<String> featuredImageLocation=new ArrayList<>();
    ArrayList<String> featuredImage=new ArrayList<>();
    ArrayList<String> featuredImageAlt=new ArrayList<>();
    ArrayList<String> featuredImageTitle=new ArrayList<>();
    ArrayList<String> title=new ArrayList<>();
    ArrayList<String> slug=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setText("");
        context=getApplicationContext();
        String url = "http://ricky.eygb.me/blog";

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
       JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {
               try
               {
                   for(int i=0;i<response.length();i++)
                   {
                       JSONObject jsonObject=response.getJSONObject(i);
                       featuredImageLocation.add(jsonObject.getString("featured_image_location"));
                       featuredImage.add(jsonObject.getString("featured_image"));
                       featuredImageAlt.add(jsonObject.getString("featured_image_alt"));
                       featuredImageTitle.add(jsonObject.getString("featured_image_title"));
                       title.add(jsonObject.getString("title"));
                       slug.add(jsonObject.getString("slug"));
                       String featuredImageLocation=jsonObject.getString("featured_image_location");
                       String featuredImage=jsonObject.getString("featured_image");
                       String featuredImageAlt=jsonObject.getString("featured_image_alt");
                       String featuredImageTitle=jsonObject.getString("featured_image_title");
                       String title=jsonObject.getString("title");
                       String slug=jsonObject.getString("slug");
                       textView.append("Featured Image Location\n"+featuredImageLocation+"\nFeatured Image\n"+featuredImage+"\nFeatured Image Alt\n"+featuredImageAlt+"\nFeatured Image Title\n"+featuredImageTitle+"\nTitle\n"+title+"\nSlug\n"+slug);
                       textView.append("\n\n");
                   }
                   for(int i=0;i<featuredImageLocation.size();i++)
                   {
                       Log.d("FeaturedImageLocation",featuredImageLocation.get(i));
                       Log.d("FeaturedImage",featuredImage.get(i));
                       Log.d("FeaturedImageAlt",featuredImageAlt.get(i));
                       Log.d("FeaturedImageTitle",featuredImageTitle.get(i));
                       Log.d("title",title.get(i));
                       Log.d("slug",slug.get(i));
                   }
               }
               catch (Exception e)
               {
                   Log.d("exception",e.toString());
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.d("erroris",error.toString());
           }
       });
        requestQueue.add(jsonArrayRequest);
    }
    }
