package com.nuryadincjr.jsontutorial;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nuryadincjr.jsontutorial.model.Users;
import com.nuryadincjr.jsontutorial.databinding.ActivityMainBinding;
import com.nuryadincjr.jsontutorial.network.ApiService;
import com.nuryadincjr.jsontutorial.network.NetworkClient;
import com.nuryadincjr.jsontutorial.pojo.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Users> usersList;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usersList = new ArrayList<>();
        adapter = new ContactAdapter(usersList, this);
        binding.rvItem.setLayoutManager(new LinearLayoutManager(this));
        binding.rvItem.setAdapter(adapter);
        getData();

        binding.srLayaout.setOnRefreshListener(() -> {
            getData();
            binding.srLayaout.setRefreshing(false);
        });
    }

    private void getData() {
        NetworkClient.getInstance().create(ApiService.class).getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if(response.code() == 200 && response.body() != null) {
                    usersList.clear();
                    usersList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
                Log.d("TAG", t.getMessage());
            }
        });
    }
}