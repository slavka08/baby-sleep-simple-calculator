package by.slavintodron.sleepcalc;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.util.Calendar.getInstance;

public class TimeCalc {
    private static final String TAG = "BABY_SLEEP";
    private Calendar wakeUpTimer;
    private Calendar playTime;

    public TimeCalc(){
        wakeUpTimer = getInstance();//(TimeZone.getTimeZone("UTC"));
        playTime = getInstance();
        playTime.setTime(new Date(0));
    }

    public void SetWakeUpCalendar(Calendar cal) {
        wakeUpTimer = cal;
    }

    public void SetTimeOfPlay(Calendar cal) {
        playTime = cal;
    }

    public String timeWhenGoToBed() {
        long tmpLongTime = wakeUpTimer.getTime().getTime() + playTime.getTime().getTime();
        Calendar resultTimer = Calendar.getInstance();
        resultTimer.clear();
        resultTimer.setTime(new Date(tmpLongTime));
        Log.i(TAG, "wakeUpTimer =\t" + String.valueOf(resultTimer.getTime().getTime()));
        //Log.i(TAG, "playTime =\t" + String.valueOf(playTime.getTime().getTime()));
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        //formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        //Log.i(TAG, "result time =\t" + resultTimer.getTime().toString());
        return formatter.format (resultTimer.getTime());
    }

}
