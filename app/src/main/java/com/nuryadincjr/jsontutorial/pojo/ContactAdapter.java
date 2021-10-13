package com.nuryadincjr.jsontutorial.pojo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nuryadincjr.jsontutorial.contacts.Constaint;
import com.nuryadincjr.jsontutorial.contacts.Contact;
import com.nuryadincjr.jsontutorial.databinding.ListItemBinding;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(@NonNull Context context, ArrayList<Contact> contact) {
        super(context, 0, contact);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);
        try {
            ContactViewHolder holder = null;
            if (convertView == null) {
                ListItemBinding binding = ListItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false);
                holder = new ContactViewHolder(binding);

                holder.setDataToView(contact.getName(),
                        contact.getEmail(), contact.getPhone());
            }
            return holder.view;
        } catch (Exception e) {
            Log.e(Constaint.TAG, e.toString());
        }
        return convertView;
    }

}
