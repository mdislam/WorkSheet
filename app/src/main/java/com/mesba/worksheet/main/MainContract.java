package com.mesba.worksheet.main;

public interface MainContract {
    interface View {
        void showTimes(String timeToDisplay);

        void updateTimeTrackerVisual(boolean started);
    }
}
