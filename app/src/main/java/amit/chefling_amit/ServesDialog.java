package amit.chefling_amit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by amit on 10/28/2016.
 */

public class ServesDialog extends DialogFragment {
    public interface ServesDialogFragmentListener {
        void onNumberPicked(String number);
    }
    String[] items = {"1","2","3","4","5","6","7","8","9","10+"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_type)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        ServesDialogFragmentListener listener = (ServesDialogFragmentListener) getActivity();
                        listener.onNumberPicked(items[which]);
                    }
                });
        return builder.create();
    }

}
