package amit.chefling_amit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView rName;
    TextView rType;
    TextView rServes;
    TextView rCooktime;
    TextView rNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        rName = (TextView)findViewById(R.id.rSName);
        rType = (TextView)findViewById(R.id.rSType);
        rServes = (TextView)findViewById(R.id.rSServes);
        rCooktime = (TextView)findViewById(R.id.rSCookTime);
        rNote = (TextView)findViewById(R.id.rSNote);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            rName.setText("Your recipe: "+extras.getString("RNAME"));
            rType.setText("Recipe type: "+extras.getString("RTYPE"));
            rServes.setText(extras.getString("RSERVES"));
            rCooktime.setText(extras.getString("RCOOKTIME"));
            rNote.setText("Notes: "+extras.getString("RNOTE"));
            //The key argument here must match that used in the other activity
        }
    }
}
