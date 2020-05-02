package com.example.contactapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Contact_Adapter extends ArrayAdapter<Contact_Table> {

    private Context context;
    private List<Contact_Table> contacts;

    public Contact_Adapter(@NonNull Context context,  List<Contact_Table> list) {
        super(context,R.layout.row_contact,list);
        this.context = context;
        this.contacts = list;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row_contact,parent,false);

        TextView tvChar = convertView.findViewById(R.id.tvChar);
        TextView tvName = convertView.findViewById(R.id.tvName2);
        TextView tvMail = convertView.findViewById(R.id.tvMail2);

        tvChar.setText(contacts.get(position).getName().toUpperCase().charAt(0)+"");
        tvName.setText(contacts.get(position).getName());
        tvMail.setText(contacts.get(position).getMail());

        return convertView;
    }
}
