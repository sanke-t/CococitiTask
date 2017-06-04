package com.example.sanket.task;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sanket.task.request.APIUtils;
import com.example.sanket.task.request.RetroService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RetroService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = APIUtils.getRetroService();
        final EditText email = (EditText)findViewById(R.id.mail);
        final EditText pass = (EditText)findViewById(R.id.pass);
        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Map<String, Object> keys = new HashMap<>();
                Map<String, Object> mapBody = new HashMap<>();
                keys.put("email",email.getText().toString());
                keys.put("password",pass.getText().toString());
                mapBody.put("user",keys);
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(mapBody)).toString());
                service.login(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()) {
                            String jsonStr = "{\"a\": \"A\"}";
                            try {
                                Gson gson = new Gson();
                                JsonElement element = gson.fromJson(response.body().string(), JsonElement.class);
                                JsonObject result = element.getAsJsonObject();
                                if(result.get("status").getAsString().equals("success")) {
                                    JsonObject data = result.getAsJsonObject("data");
                                    JsonObject user  = data.getAsJsonObject("user");
                                    String email = user.get("email").getAsString();
                                    String accessToken = user.get("access_token").getAsString();
                                    APIUtils.updateSharedPrefs(getApplicationContext(),"email",email);
                                    APIUtils.updateSharedPrefs(getApplicationContext(),"token",accessToken);
                                    Intent intent = new Intent(MainActivity.this,ShowcaseActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Snackbar snackbar = Snackbar
                                            .make(v, "Invalid credentials", Snackbar.LENGTH_LONG);

                                    snackbar.show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {
                            int statusCode = response.code();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("MainActivity",  "error loading from API");

                    }
                });


            }
        });
    }

}
