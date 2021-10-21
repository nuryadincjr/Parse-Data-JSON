package com.nuryadincjr.jsontutorial.pojo;

import androidx.recyclerview.widget.RecyclerView;

import com.nuryadincjr.jsontutorial.databinding.ListItemBinding;
import com.nuryadincjr.jsontutorial.model.Users;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private ListItemBinding binding;

    public ContactViewHolder(ListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setDataToView(Users item) {
        binding.tvName.setText(item.getName());
        binding.tvEmail.setText(item.getEmail());
        binding.tvPhone.setText(item.getPhone());
    }
}
