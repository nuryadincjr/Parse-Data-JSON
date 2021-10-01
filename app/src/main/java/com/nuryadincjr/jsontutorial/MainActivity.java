package com.nuryadincjr.jsontutorial;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nuryadincjr.jsontutorial.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
//    private final String URL = "https://api.androidhive.info/contacts/";
    private final String URL = "https://app.fakejson.com/q/QlRaybY9?token=EwuNZ5kIloYxNoVml1K86g";
    private ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        contactList = new ArrayList<>();

        getData();
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                response -> {
                    try {
                        JSONObject jsonPost = new JSONObject(response);
                        JSONArray contacts = jsonPost.getJSONArray("contacts");

                        String id, name = null, email = null, address, gender, mobile, home, office;
                        for (int i = 0; i < contacts.length(); i++) {

                            JSONObject object1 = contacts.getJSONObject(i);
                            id = object1.getString("id");
                            name = object1.getString("name");
                            email = object1.getString("email");
                            address = object1.getString("address");
                            gender = object1.getString("gender");

                            JSONObject phone = object1.getJSONObject("phone");
                            mobile = phone.getString("mobile");
                            home = phone.getString("home");
                            office = phone.getString("office");

                            HashMap<String, String> contact = new HashMap<>();

                            contact.put("id", id);
                            contact.put("name", name);
                            contact.put("email", email);
                            contact.put("mobile", mobile);

                            contactList.add(contact);
                        }

                        ListAdapter adapter = new SimpleAdapter(
                                MainActivity.this,
                                contactList, R.layout.list_item,
                                new String[]{"name", "email", "mobile"},
                                new int[]{R.id.tvName, R.id.tvEmail, R.id.tvMobile});

                        binding.rvList.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Log.e("Error Response", error.toString()));
        queue.add(stringRequest);
    }
}