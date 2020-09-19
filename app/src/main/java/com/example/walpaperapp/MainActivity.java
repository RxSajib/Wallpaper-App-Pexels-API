package com.example.walpaperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NativeActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.walpaperapp.Adapter.WalpaperAdapter;
import com.example.walpaperapp.Modeal.WalpaperModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WalpaperAdapter walpaperAdapter;
    private List<WalpaperModel> walpaperModelList;
    private static final int SPAN_COUNT = 2;

    private int current_iteams, total_iteams, scrollout_iteams;
    private int count = 1;
    private Boolean isscroll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.Recylerview);
        recyclerView.setHasFixedSize(true);

        walpaperModelList = new ArrayList<>();
        walpaperAdapter = new WalpaperAdapter(this, walpaperModelList);



        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(walpaperAdapter);
        getread_data();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                isscroll = true;

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                current_iteams = gridLayoutManager.getChildCount();
                total_iteams = gridLayoutManager.getItemCount();
                scrollout_iteams = gridLayoutManager.findFirstVisibleItemPosition();

                if(isscroll && (current_iteams+scrollout_iteams == total_iteams)){
                    getread_data();
                }

            }
        });


    }

    private void getread_data(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.pexels.com/v1/curated/?page="+count+"&per_page=80", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);


                    JSONArray jsonArray = jsonObject.getJSONArray(DataManager.photos_Array);
                    int size = jsonObject.length();
                    for(int i=0;  i<=size; i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        int _id = jsonObject1.getInt(DataManager.id);


                        JSONObject src_object = jsonObject1.getJSONObject(DataManager.src);

                        String originalimage = src_object.getString(DataManager.original);
                        String mediumimage = src_object.getString(DataManager.medium);


                        WalpaperModel walpaperModel = new WalpaperModel(_id, mediumimage, originalimage);
                        walpaperModelList.add(walpaperModel);

                    }

                    walpaperAdapter.notifyDataSetChanged();
                    count ++;

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put(DataManager.Authorization, DataManager.API);

                return header;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }
}