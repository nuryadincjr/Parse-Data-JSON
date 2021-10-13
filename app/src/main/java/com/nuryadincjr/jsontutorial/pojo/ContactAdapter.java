package com.nuryadincjr.jsontutorial.pojo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nuryadincjr.jsontutorial.contacts.Contact;
import com.nuryadincjr.jsontutorial.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private final ArrayList<Contact> data;
    private final Context context;

    public ContactAdapter(ArrayList<Contact> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        String name = data.get(position).getName();
        String email = data.get(position).getEmail();
        String phone = data.get(position).getPhone();
        holder.setDataToView(name, email, phone);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
