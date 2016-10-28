package amit.chefling_amit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by amit on 10/28/2016.
 */

public class ItemTypeDialog extends DialogFragment {

    public interface PickTypeFragmentListener {
        void onTypePicked(String type);
    }

    String[] items= {"Dessert","Breakfast", "Main Courses", "Salad", "Soup", "Appetizers", "Drinks"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//       for(int i=0;i<FetchTypeData.types.size();i++)
//       {
//           items[i]=FetchTypeData.types.get(i);
//       }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_type)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        PickTypeFragmentListener listener = (PickTypeFragmentListener) getActivity();
                        listener.onTypePicked(items[which]);
                    }
                });
        return builder.create();
    }
}
