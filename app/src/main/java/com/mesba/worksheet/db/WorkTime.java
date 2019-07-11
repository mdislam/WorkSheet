package com.mesba.worksheet.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mesba.worksheet.Constants;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Entity(tableName = "work_times")
public class WorkTime implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "date_text")
    private String dateText;

    @NonNull
    @ColumnInfo(name = "start_time")
    private Date startTime;

    @ColumnInfo(name = "end_time")
    private Date endTime;

    public WorkTime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(@NonNull Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @NonNull
    public String getDateText() {
        return dateText;
    }

    public void setDateText(@NonNull String dateText) {
        this.dateText = dateText;
    }
}
