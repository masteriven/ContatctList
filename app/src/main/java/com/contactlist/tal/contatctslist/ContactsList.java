package com.contactlist.tal.contatctslist;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ContactsList extends AppCompatActivity {

    RecyclerView recyclerView;
    ContactAdapter contactAdapter;
    JSONObject jsonObject;
    JSONArray jsonArray;
    private String FETCH_STRING;
    private String name;
    private String phoneNumber;
    private List<Contact> arrayList;
    Context context;
    private static final String DATABASE_NAME = "contacts_db";
    private ContactDatabase contactDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        boolean firstTime = getSharedPreferences("FirstTime", MODE_PRIVATE).getBoolean("isFirstRun",true);

        contactDatabase = Room.databaseBuilder(getApplicationContext(),
                ContactDatabase.class,DATABASE_NAME).allowMainThreadQueries().build();
        arrayList = new ArrayList<>();

        if (firstTime) {

            getSharedPreferences("FirstTime", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();

            try {

                Toast.makeText(this,"firsttime",Toast.LENGTH_SHORT).show();
                FETCH_STRING = getIntent().getStringExtra("json_data");
                jsonObject = new JSONObject(FETCH_STRING);
                jsonArray = jsonObject.getJSONArray("Employees");
                int count = 0;
                while (count <= jsonArray.length()) {
                    JSONObject jsonObj = jsonArray.getJSONObject(count);
                    name = jsonObj.getString("firstName");
                    phoneNumber = jsonObj.getString("phoneNumber");

                    contactAdapter = new ContactAdapter(this, phoneNumber);
                    Contact contact = new Contact(name, phoneNumber);

                    arrayList.add(contact);

                    contactDatabase.contactDao().addcontact(contact);


                    count++;

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        arrayList = contactDatabase.contactDao().getAll();
        recyclerView = findViewById(R.id.contactsListId);
        contactAdapter = new ContactAdapter(this, arrayList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(contactAdapter);
      //  contactAdapter.notifyDataSetChanged();

    }


    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Contact contact = (Contact) arrayList.get(position);
            arrayList.remove(contact);
            contactDatabase.contactDao().delete(contact);


        }

    };


}



