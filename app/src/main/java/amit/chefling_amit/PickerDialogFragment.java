package amit.chefling_amit;

import android.widget.Toast;

import mobi.upod.timedurationpicker.TimeDurationPicker;
import mobi.upod.timedurationpicker.TimeDurationPickerDialogFragment;

/**
 * Created by amit on 10/28/2016.
 */

public class PickerDialogFragment extends TimeDurationPickerDialogFragment {

    public interface PickerDialogFragmentListener {
        void onDurationPicked(Long duaration);
    }

    @Override
    protected long getInitialDuration() {
        return 15 * 60 * 1000;
    }

    @Override
    public void onDurationSet(TimeDurationPicker view, long duration) {
        Toast.makeText(getActivity(), Long.toString(duration),Toast.LENGTH_SHORT).show();
//        SharedPreferences cook_time;
//        SharedPreferences.Editor editor;
//        cook_time = getActivity().getSharedPreferences("COOKING_TIME", Context.MODE_PRIVATE); //1
//        editor = cook_time.edit(); //2
//
//        editor.putLong("COOKING_TIME_LONG", duration); //3
//        editor.commit();

        PickerDialogFragmentListener listener = (PickerDialogFragmentListener) getActivity();
        listener.onDurationPicked(duration);

    }
}
