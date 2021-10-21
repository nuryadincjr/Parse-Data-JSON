package com.nuryadincjr.jsontutorial.network;

import com.nuryadincjr.jsontutorial.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    public Call<List<Users>> getUsers();
}
