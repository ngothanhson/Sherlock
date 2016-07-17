package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;

public class AboutPersonActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_person);
        ButterKnife.bind(this);
        initData();
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_sherlock);
        getSupportActionBar().setTitle("About " + person.getName());
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_left));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutPersonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_about_person_actions, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_movement:
                Intent intent = new Intent(AboutPersonActivity.this, AddMovementActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Constant.BUNDLE_KEY);
        person = new Person();
        person.setId(bundle.getInt(Constant.ID_KEY));
        person.setName(bundle.getString(Constant.NAME_KEY));
    }
}
