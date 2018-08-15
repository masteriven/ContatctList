package com.contactlist.tal.contatctslist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Tal on 27/04/2018.
 *
 *
 */

@Entity(tableName = "contact")

public class Contact {

    Contact(){

    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String names;

    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;


    Contact(String name, String phoneNumber){
        this.names = name;
        this.phoneNumber = phoneNumber;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
