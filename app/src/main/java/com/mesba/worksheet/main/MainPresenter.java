package com.mesba.worksheet.main;

import android.content.Context;

import com.mesba.worksheet.Helpers;
import com.mesba.worksheet.db.Converters;
import com.mesba.worksheet.db.Database;
import com.mesba.worksheet.db.DatabaseClient;
import com.mesba.worksheet.db.WorkTime;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainPresenter {
    private MainContract.View view;
    private Context mCtx;
    private Database appDatabase;

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.mCtx = context;
    }

    protected void onStop() {
        view = null;
        mCtx = null;
    }

    protected void onCreate() {
        appDatabase = DatabaseClient.getInstance(mCtx).getAppDatabase();

        Date now = Calendar.getInstance().getTime();
        boolean started = isWorkTimeStarted(now);

        view.updateTimeTrackerVisual(started);

//        view.updateStartButton(!started);
//        view.updateEndButton(started);
    }

    protected boolean isWorkTimeStarted(Date date) {
        return appDatabase.workTimeDao().getWorkEntryCount(Helpers.getCurrentDate(date)) == 1;
    }

    protected void saveWorkTime() {
        Date now = Calendar.getInstance().getTime();
        boolean started = isWorkTimeStarted(now);
        if(started) {
            endWorkTime(now);
        }
        else {
            startWorkTime(now);
        }

        view.updateTimeTrackerVisual(!started);
    }

    private void startWorkTime(Date date) {
        WorkTime workTime = new WorkTime();
        workTime.setStartTime(date);
        workTime.setDateText(Helpers.getCurrentDate(date));
        appDatabase.workTimeDao().insert(workTime);
    }

    private void endWorkTime(Date date) {
        WorkTime workTime = appDatabase.workTimeDao().getWorkEntry(Helpers.getCurrentDate(date));
        workTime.setEndTime(date);
        appDatabase.workTimeDao().update(workTime);
    }

    protected void showTimes() {
        List<WorkTime> data = appDatabase.workTimeDao().getAll();
        StringBuilder dataToDisplay = new StringBuilder();
        for (WorkTime workTime: data) {
            String startedAt = Helpers.getCurrentTime(workTime.getStartTime());
            String endedAt = Helpers.getCurrentTime(workTime.getEndTime());
            String dateText = workTime.getDateText();

            long diff = workTime.getEndTime().getTime() - workTime.getStartTime().getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;

            dataToDisplay.append("Date: ").append(dateText)
                    .append(" - ")
                    .append("start: ").append(startedAt)
                    .append(" - ")
                    .append("end: ").append(endedAt)
                    .append(" - ")
                    .append("duration: ").append(diffHours).append("H ").append(diffMinutes).append("M")
                    .append(System.getProperty("line.separator"));
        }
        view.showTimes(dataToDisplay.toString());
    }
}
