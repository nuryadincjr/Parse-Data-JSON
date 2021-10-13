package com.nuryadincjr.jsontutorial;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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
            getData();
            binding.srLayaout.setRefreshing(false);
        });

        ContactAdapter adapter = new ContactAdapter(contactList, this);
        binding.lvItem.setLayoutManager(new LinearLayoutManager(this));
        binding.lvItem.setAdapter(adapter);
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constaint.URL,
                response -> {
                    try {
                        JSONArray contacts = new JSONArray(response);

                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject objContact = contacts.getJSONObject(i);
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

                            Contact contact = new Contact();
                            contact.setId(id);
                            contact.setName(name);
                            contact.setUsername(username);
                            contact.setEmail(email);

                            Address address = new Address();
                            address.setStreet(street);
                            address.setSuite(suite);
                            address.setCity(city);
                            address.setZipcode(zipcode);

                            Geo geo = new Geo();
                            geo.setLat(lat);
                            geo.setLng(lng);
                            address.setGeo(geo);

                            contact.setAddress(address);
                            contact.setPhone(phone);
                            contact.setWebsite(website);

                            Company company = new Company();
                            company.setName(comName);
                            company.setCatchPhrase(catchPhrase);
                            company.setBs(bs);

                            contact.setCompany(company);
                            contactList.add(contact);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(Constaint.TAG, e.toString());
                    }
                }, error -> Log.e(Constaint.TAG, error.toString()));
        queue.add(stringRequest);
    }
}