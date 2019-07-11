package com.mesba.worksheet.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mesba.worksheet.Helpers;
import com.mesba.worksheet.R;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter presenter;

    @BindView(R.id.btn_track_time) Button btnTrackTime;

    @BindView(R.id.btn_show) Button btnShow;
    @BindView(R.id.time_text) TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this, getApplicationContext());
        presenter.onCreate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @OnClick(R.id.btn_track_time)
    void onTimeTrackClicked() {
        Date now = Calendar.getInstance().getTime();
        String currentDateTimeText = Helpers.getCurrentDate(now) + " " + Helpers.getCurrentTime(now);
        Toast.makeText(this, "Work Ended at: " + currentDateTimeText, Toast.LENGTH_SHORT).show();
        presenter.saveWorkTime();
    }

    @OnClick(R.id.btn_show)
    void onDisplayTimeClicked() {
        presenter.showTimes();
    }

    @Override
    public void showTimes(String timeToDisplay) {
        timeText.setText(timeToDisplay);
    }

    @Override
    public void updateTimeTrackerVisual(boolean started) {
        if(started) {
            btnTrackTime.setText("End");
            btnTrackTime.setBackground(getDrawable(R.drawable.button_bg_round_red));
        }
        else {
            btnTrackTime.setText("Start");
            btnTrackTime.setBackground(getDrawable(R.drawable.button_bg_round));
        }
    }
}
