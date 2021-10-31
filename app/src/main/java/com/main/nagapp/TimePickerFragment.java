package com.main.nagapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.widget.TimePicker;
import android.widget.Toast;




import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private static int hour;
    private static int minute;

    @NonNull
    @Override

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),this,
                hour, minute, true);
    }
    public void onTimeSet(TimePicker view, int h, int m) {
        hour = h;
        minute = m;
    }

    public static int getHour() {
        return hour;
    }

    public static int getMinute() {
        return minute;
    }
}
