package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;
import ngothanhson95.dev.com.sherlock.sherlock.database.DbHelper;
import ngothanhson95.dev.com.sherlock.sherlock.model.Movement;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;
import ngothanhson95.dev.com.sherlock.sherlock.network.GPSTracker;

public class AddMovementActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.etInputLocation)
    EditText etInputLocation;
    @Bind(R.id.etInputMovementNote)
    EditText etInputMovementNote;
    @Bind(R.id.imgLocation)
    ImageView imgLocation;
    @Bind(R.id.imgWarningLocation)
    ImageView imgWarningLocation;
    @Bind(R.id.imgWarningNote)
    ImageView imgWarningNote;
    @Bind(R.id.btnSaveLocation)
    Button btnSaveLocation;
    @Bind(R.id.btnReturnLocation)
    Button btnReturnLocation;

    Movement movement = new Movement();
    DbHelper db;
    String namePlace;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movement);
        ButterKnife.bind(this);
        btnReturnLocation.setOnClickListener(this);
        btnSaveLocation.setOnClickListener(this);
        imgLocation.setOnClickListener(this);
        initToolbar();
        initData();
    }


    private void initData() {
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(Constant.BUNDLE_KEY);
            Person per = bundle.getParcelable(Constant.PERSON_PARCE_KEY);
            movement.setPersonName(per.getName());
            movement.setPersonId(per.getId());
        } catch (Exception e) {
            Log.d("TAG", "NullPointerException");
        }

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_sherlock);
        getSupportActionBar().setTitle("Add movement");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_left));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMovementActivity.this.finish();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSaveLocation: {
                if (checkRequiredText()) {
                    addMovement();
                    db = new DbHelper(getApplicationContext());
                    db.insertMovement(movement);
                    Snackbar.make(view, "Successful", Snackbar.LENGTH_SHORT).show();

                    //put movement back to
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Constant.MOVEMENT_PARCE_KEY, movement);
                    Intent in = new Intent(AddMovementActivity.this, AboutPersonActivity.class);
                    in.putExtra(Constant.BUNDLE_KEY, bundle);
                    AddMovementActivity.this.setResult(Constant.ADD_MOVEMENT_RESULT_CODE, in);
                    AddMovementActivity.this.finish();
                } else {
                    return;
                }
                break;
            }

            case R.id.btnReturnLocation: {
                AddMovementActivity.this.finish();
                break;
            }
            case R.id.imgLocation: {
                final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    AlertDialog.Builder confirmGPS = new AlertDialog.Builder(AddMovementActivity.this);
                    confirmGPS.setTitle("GPS is settings");
                    confirmGPS.setMessage("GPS is not enable. Do you want to go to settings menu?");
                    confirmGPS.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                    confirmGPS.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    });
                    confirmGPS.create().show();
                    break;
                } else {
                    Intent intent = new Intent(this, LoadNearPlaceActivity.class);
                    startActivityForResult(intent, Constant.ADD_NAME_PLACE_REQUEST_CODE);
                    break;
                }
            }
        }
    }


    private boolean checkRequiredText() {
        if (etInputLocation.length() == 0 || etInputMovementNote.length() == 0) {
            if (etInputLocation.length() == 0) {
                imgWarningLocation.setVisibility(View.VISIBLE);
            } else {
                imgWarningLocation.setVisibility(View.INVISIBLE);
            }

            if (etInputMovementNote.length() == 0) {
                imgWarningNote.setVisibility(View.VISIBLE);
            } else {
                imgWarningNote.setVisibility(View.INVISIBLE);
            }
            return false;
        } else {
            imgWarningLocation.setVisibility(View.INVISIBLE);
            imgWarningNote.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    private void addMovement() {
        movement.setMovementLocation(etInputLocation.getText().toString());
        movement.setMovementNote(etInputMovementNote.getText().toString());
        Calendar c = Calendar.getInstance();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
        String localTime = date.format(currentLocalTime);
        movement.setMovementTime(localTime);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.ADD_NAME_PLACE_REQUEST_CODE) {
            if (resultCode == Constant.ADD_NAME_PLACE_RESULT_CODE) {
                namePlace = data.getStringExtra(Constant.NAME_PLACE_KEY);
            }
            if (namePlace != null) {
                etInputLocation.setText(namePlace);
            }
        }


    }
}
