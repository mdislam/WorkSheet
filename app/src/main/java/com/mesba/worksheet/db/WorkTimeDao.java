package com.mesba.worksheet.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface WorkTimeDao {
    @Query("SELECT * FROM work_times WHERE end_time IS NOT NULL")
    List<WorkTime> getAll();

    @Query("SELECT * FROM work_times WHERE start_time BETWEEN :from AND :to")
    List<WorkTime> findWorkTimesBetweenDates(Date from, Date to);

    @Query("SELECT * FROM work_times WHERE date_text = :date AND end_time IS NULL LIMIT 1")
    WorkTime getWorkEntry(String date);

    @Query("SELECT COUNT(*) FROM work_times WHERE date_text = :date AND end_time IS NULL")
    int getWorkEntryCount(String date);

    @Insert
    void insert(WorkTime workTime);

    @Delete
    void delete(WorkTime workTime);

    @Update
    void update(WorkTime workTime);
}
