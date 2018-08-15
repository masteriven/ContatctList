package com.contactlist.tal.contatctslist;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Tal on 28/04/2018.
 */
@Database(entities = {Contact.class}, version = 2)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract  ContactRoomInterface contactDao();

}
