package com.contactlist.tal.contatctslist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tal on 27/04/2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.myViewHolder> {

    List<Contact> arrayList = new ArrayList();
    Context context;
    String phoneNumbers;

    public ContactAdapter(@NonNull Context context, List<Contact> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.customrawlayout,parent,false));

    }
    public ContactAdapter(Context context, String phoneNumber) {

        this.context = context;
        this.phoneNumbers = phoneNumber;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        final Contact contact = arrayList.get(position);

        holder.name.setText(contact.getNames());
        holder.phoneNumber.setText(contact.getPhoneNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(Intent.ACTION_DIAL);
                  intent.setData(Uri.parse("tel:" + contact.getPhoneNumber()));
                   context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class  myViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView phoneNumber;


        public myViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.contactName);
            phoneNumber = (TextView)itemView.findViewById(R.id.contactPhone);
        }

    }
}
