package com.contactlist.tal.contatctslist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Tal on 28/04/2018.
 */
@Dao
public interface ContactRoomInterface {

    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Insert
    void addcontact (Contact contact);

    @Insert
    void insertAll(List<Contact> contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);
}
