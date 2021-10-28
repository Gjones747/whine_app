package com.main.nagapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.widget.TimePicker;




import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),this,
                hour, minute, false);
    }
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Bundle result = new Bundle();
        result.putString("h", Integer.toString(hourOfDay));
        result.putString("m", Integer.toString(minute));
        //getParentFragmentManager().setFragmentResult("request key", result);

    }
}
