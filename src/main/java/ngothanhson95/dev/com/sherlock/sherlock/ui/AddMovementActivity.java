package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movement);
        ButterKnife.bind(this);
        btnReturnLocation.setOnClickListener(this);
        btnSaveLocation.setOnClickListener(this);
        imgLocation.setOnClickListener(this);
        initToolbar();
    }

    private void initToolbar(){
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
                comeBack();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSaveLocation:{
                if(checkRequiredText()){
                    Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                } else {
                    return;
                }
                break;
            }

            case R.id.btnReturnLocation:{
                comeBack();
                break;
            }
            case R.id.imgLocation: {
                final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    Toast.makeText(this, "Chua bat", Toast.LENGTH_SHORT).show();
                    final AlertDialog.Builder confirmGPS = new AlertDialog.Builder(AddMovementActivity.this);
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
                }else{
                    Toast.makeText(this, "Da bat", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }

    private boolean checkRequiredText() {
        if(etInputLocation.length()==0||etInputMovementNote.length()==0){
            if(etInputLocation.length()==0){
                imgWarningLocation.setVisibility(View.VISIBLE);
            } else {
                imgWarningLocation.setVisibility(View.INVISIBLE);
            }

            if(etInputMovementNote.length()==0){
                imgWarningNote.setVisibility(View.VISIBLE);
            }else {
                imgWarningNote.setVisibility(View.INVISIBLE);
            }
            return false;
        }else {
            imgWarningLocation.setVisibility(View.INVISIBLE);
            imgWarningNote.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    private void comeBack(){
        Intent intent = new Intent(AddMovementActivity.this, AboutPersonActivity.class);
        startActivity(intent);
    }
}
