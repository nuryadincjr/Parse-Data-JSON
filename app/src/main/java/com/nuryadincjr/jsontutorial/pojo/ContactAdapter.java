package com.nuryadincjr.jsontutorial.pojo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nuryadincjr.jsontutorial.model.Users;
import com.nuryadincjr.jsontutorial.databinding.ListItemBinding;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<Users> data;
    private Context context;

    public ContactAdapter(List<Users> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Users item = data.get(position);
        holder.setDataToView(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
