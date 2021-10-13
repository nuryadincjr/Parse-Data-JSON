package com.nuryadincjr.jsontutorial;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nuryadincjr.jsontutorial.contacts.Address;
import com.nuryadincjr.jsontutorial.contacts.Company;
import com.nuryadincjr.jsontutorial.contacts.Constaint;
import com.nuryadincjr.jsontutorial.contacts.Contact;
import com.nuryadincjr.jsontutorial.contacts.Geo;
import com.nuryadincjr.jsontutorial.databinding.ActivityMainBinding;
import com.nuryadincjr.jsontutorial.pojo.ContactAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Contact> contactList;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        contactList = new ArrayList<>();
        getData();

        binding.srLayaout.setColorSchemeResources(android.R.color.holo_orange_dark);
        binding.srLayaout.setOnRefreshListener(() -> {
            adapter.clear();
            getData();
            binding.srLayaout.setRefreshing(false);
        });
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constaint.URL,
                response -> {
                    try {
                        JSONArray getPost = new JSONArray(response);

                        for (int i = 0; i < getPost.length(); i++) {
                            JSONObject objContact = getPost.getJSONObject(i);
                            String name, username, email, phone, website;
                            int id = objContact.getInt("id");
                            name = objContact.getString("name");
                            username = objContact.getString("username");
                            email = objContact.getString("email");

                            JSONObject objAddress = objContact.getJSONObject("address");
                            String street, suite, city, zipcode;
                            street = objAddress.getString("street");
                            suite = objAddress.getString("suite");
                            city = objAddress.getString("city");
                            zipcode = objAddress.getString("zipcode");

                            JSONObject objGeo = objAddress.getJSONObject("geo");
                            String lat, lng;
                            lat = objGeo.getString("lat");
                            lng = objGeo.getString("lng");

                            phone = objContact.getString("phone");
                            website = objContact.getString("website");

                            JSONObject obCompany = objContact.getJSONObject("company");
                            String comName, catchPhrase, bs;
                            comName = obCompany.getString("name");
                            catchPhrase = obCompany.getString("catchPhrase");
                            bs = obCompany.getString("bs");

                            Geo geo = new Geo(lat, lng);
                            Address address = new Address(street, suite, city, zipcode, geo);
                            Company company = new Company(comName, catchPhrase, bs);
                            Contact contact = new Contact(id, name, username, email,
                                    address, phone, website, company);

                            contactList.add(contact);
                        }

                        adapter = new ContactAdapter(this, contactList);
                        binding.lvItem.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(Constaint.TAG, e.toString());
                    }
                }, error -> Log.e(Constaint.TAG, error.toString()));
        queue.add(stringRequest);
    }
}