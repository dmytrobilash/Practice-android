package com.hfad.techtask1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.techtask1.adapters.CustomAdapter;
import com.hfad.techtask1.model.Data;
import com.hfad.techtask1.network.APIclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {


    GridView gridView;
    EditText editText;
    TextView textView;
    ArrayList<HashMap<String, String>> nameList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.input_edit_text);
        gridView = findViewById(R.id.grid);

        Call<List<Data>> call = APIclient.apIinterface().getData();

        call.enqueue(new Callback<List<Data>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                //todo if there is not internet connection
                if (response.isSuccessful()) {
                    customAdapter = new CustomAdapter(response.body(), MainActivity.this);
                    gridView.setAdapter(customAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Failure (On response)", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}