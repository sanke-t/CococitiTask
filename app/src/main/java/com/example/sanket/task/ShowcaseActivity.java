package com.example.sanket.task;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.sanket.task.adpater.ShowcaseAdapter;
import com.example.sanket.task.models.Res;
import com.example.sanket.task.models.Showcase;
import com.example.sanket.task.request.APIUtils;
import com.example.sanket.task.request.RetroService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowcaseActivity extends AppCompatActivity {
    private RetroService service;
    private ShowcaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Showcases");
        final RecyclerView showcases = (RecyclerView)findViewById(R.id.showcase_recycler);
        service = APIUtils.getRetroService();Map<String, String> map = new HashMap<>();
        map.put("X-ACCESS-TOKEN",APIUtils.getFromSharedPreferences(getApplicationContext(),"token"));
        map.put("X-USER-EMAIL",APIUtils.getFromSharedPreferences(getApplicationContext(),"email"));
        map.put("Accept","application/json");
        service.getTasks(map).enqueue(new Callback<Res>() {
            @Override
            public void onResponse(Call<Res> call, Response<Res> response) {
                adapter = new ShowcaseAdapter(getApplicationContext(),response.body().getShowcases());
                showcases.setAdapter(adapter);
                showcases.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


            }

            @Override
            public void onFailure(Call<Res> call, Throwable t) {
                String err = "error";
            }
        });

    }

}
