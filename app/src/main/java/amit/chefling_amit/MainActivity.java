package amit.chefling_amit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.enrique.stackblur.StackBlurManager;
import com.mvc.imagepicker.ImagePicker;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, PickerDialogFragment.PickerDialogFragmentListener
,ServesDialog.ServesDialogFragmentListener, ItemTypeDialog.PickTypeFragmentListener {

    Button b1;
    Button b2;
    Button b3;
    TextView rServes;
    TextView rCookTime;
    EditText rName;
    EditText rNote;
    Button next;
    ImageView rCamera;
    ImageView rPoster;
    TextView rType;
    private StackBlurManager _stackBlurManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.level_button1);
        b2 = (Button)findViewById(R.id.level_button2);
        b3 = (Button)findViewById(R.id.level_button3);
        rCamera = (ImageView)findViewById(R.id.CameraButton);
        rPoster = (ImageView)findViewById(R.id.posterImageView);
        next = (Button)findViewById(R.id.rNextbutton);
        rServes = (TextView) findViewById(R.id.rServesTextView);
        rCookTime = (TextView) findViewById(R.id.rCookingTimetextView);
        rName = (EditText) findViewById(R.id.rNameEditText);
        rNote = (EditText) findViewById(R.id.editText3);
        rType = (TextView) findViewById(R.id.rTypeEditText);
        b1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        rCamera.setOnClickListener(this);
        rServes.setOnClickListener(this);
        rCookTime.setOnClickListener(this);
        rType.setOnClickListener(this);
        next.setOnClickListener(this);

        ImagePicker.setMinQuality(600, 600);
        getBlurEffect(rPoster);

//        FetchTypeData task = new FetchTypeData(this);
//        task.execute();
    }

    private void getBlurEffect(ImageView rPoster) {
        _stackBlurManager = new StackBlurManager(BitmapFactory.decodeResource(getResources(),
                R.drawable.image1));
        rPoster.setImageBitmap( _stackBlurManager.process(100) );
    }
    public Bitmap fastblur(Bitmap sentBitmap, float scale, int radius) {

        int width = Math.round(sentBitmap.getWidth() * scale);
        int height = Math.round(sentBitmap.getHeight() * scale);
        sentBitmap = Bitmap.createScaledBitmap(sentBitmap, width, height, false);

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        return bitmap;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.level_button1:
                b1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                b2.setBackgroundColor(Color.WHITE);
                b3.setBackgroundColor(Color.WHITE);
                break;
            case R.id.level_button2:
                b2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                b1.setBackgroundColor(Color.WHITE);
                b3.setBackgroundColor(Color.WHITE);
                break;
            case R.id.level_button3:
                b3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                b1.setBackgroundColor(Color.WHITE);
                b2.setBackgroundColor(Color.WHITE);
                break;
            case R.id.rServesTextView:
                DialogFragment servdialog = new ServesDialog();
                servdialog.show(getSupportFragmentManager(), "ServesDialogFragment");
                break;
            case R.id.rCookingTimetextView:
                new PickerDialogFragment().show(getFragmentManager(), "dialog");
                break;
            case R.id.rNextbutton: {
//                FetchTypeData task = new FetchTypeData(this);
//                task.execute();
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("RNAME", rName.getText().toString());
                intent.putExtra("RTYPE", rType.getText());
                intent.putExtra("RSERVES", rServes.getText());
                intent.putExtra("RCOOKTIME", rCookTime.getText());
                intent.putExtra("RNOTE", rNote.getText().toString());
                startActivity(intent);
                break;
            }
            case R.id.CameraButton:
                onPickImage(rCamera);
                break;
            case R.id.rTypeEditText:
                DialogFragment dialog = new ItemTypeDialog();
                dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
                break;
            default:
                Toast.makeText(this,"Button action is not defined!",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        if (bitmap != null) {
            rPoster.setImageBitmap(bitmap);
        }
    }

    public void onPickImage(View view) {
        // Click on image button
        ImagePicker.pickImage(this, "Select your image:");
    }


    @Override
    public void onDurationPicked(Long duaration) {
        rCookTime.setText("Cooking time: " + Long.toString(duaration/60000) + " min.");
    }

    @Override
    public void onNumberPicked(String number) {
        rServes.setText("Serves " + number);
    }

    @Override
    public void onTypePicked(String type) {
        rType.setText(type);
    }
}
