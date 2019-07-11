package com.mesba.worksheet.db;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@androidx.room.Database(entities = {WorkTime.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {
    public abstract WorkTimeDao workTimeDao();
}
