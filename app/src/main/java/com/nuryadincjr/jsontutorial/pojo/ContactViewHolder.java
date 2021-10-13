package com.nuryadincjr.jsontutorial.pojo;

import android.view.View;

import com.nuryadincjr.jsontutorial.databinding.ListItemBinding;

public class ContactViewHolder {
    public View view;
    private ListItemBinding binding;

    public ContactViewHolder(ListItemBinding binding) {
        this.view = binding.getRoot();
        this.binding = binding;
    }

    public void setDataToView(String name, String email, String phone) {
        binding.tvName.setText(name);
        binding.tvEmail.setText(email);
        binding.tvPhone.setText(phone);
    }
}
