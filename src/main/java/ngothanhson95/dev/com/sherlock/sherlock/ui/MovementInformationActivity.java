package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;
import ngothanhson95.dev.com.sherlock.sherlock.model.Movement;

public class MovementInformationActivity extends AppCompatActivity {
    Movement movement;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tvMoveInfoPersonName)
    TextView tvMoveInfoPersonName;
    @Bind(R.id.tvMoveInfoLocation)
    TextView tvMoveInfoLocation;
    @Bind(R.id.tvMoveInfoNote)
    TextView tvMoveInfoNote;
    @Bind(R.id.tvMoveInfoTime)
    TextView tvMoveInfoTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movement_infomation);
        ButterKnife.bind(this);
        initToolbar();
        Bundle bundle = getIntent().getBundleExtra(Constant.BUNDLE_KEY);
        movement = bundle.getParcelable(Constant.MOVEMENT_PARCE_KEY);
        tvMoveInfoPersonName.setText(movement.getPersonName());
        tvMoveInfoLocation.setText(movement.getMovementLocation());
        tvMoveInfoNote.setText(movement.getMovementNote());
        tvMoveInfoTime.setText(movement.getMovementTime());
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_sherlock);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Movement Information");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_left));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovementInformationActivity.this.finish();
            }
        });
    }
}
