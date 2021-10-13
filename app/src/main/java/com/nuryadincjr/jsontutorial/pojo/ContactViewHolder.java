package com.nuryadincjr.jsontutorial.pojo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nuryadincjr.jsontutorial.databinding.ListItemBinding;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private ListItemBinding binding;

    public ContactViewHolder(@NonNull ListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setDataToView(String name, String email, String phone) {
        binding.tvName.setText(name);
        binding.tvEmail.setText(email);
        binding.tvPhone.setText(phone);
    }

}
