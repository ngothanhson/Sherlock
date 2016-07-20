package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.adapter.AboutPersonPagerAdapter;
import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;

public class AboutPersonActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.vpAboutPerson)
    ViewPager vpAboutPerson;
    @Bind(R.id.sliding_tabs)
    TabLayout slingTab;

    private Person person;
    public PersonFragment personFragment;
    public MovementFragment movementFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_person);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        setupViewPager();
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
               AboutPersonActivity.this.finish();
            }
        });
    }

    private void setupViewPager() {
        AboutPersonPagerAdapter adapter = new AboutPersonPagerAdapter(getSupportFragmentManager());
        personFragment = new PersonFragment();
        movementFragment = new MovementFragment();
        adapter.addFragment(movementFragment, "Movement List");
        adapter.addFragment(personFragment, "Person Information");

        //put person to PersonInfomation Fragment
        Bundle bundle =  getIntent().getBundleExtra(Constant.BUNDLE_KEY);
        person = bundle.getParcelable(Constant.PERSON_PARCE_KEY);
        personFragment.setArguments(bundle);
        ////put person to Movement Fragment
        movementFragment.setArguments(bundle);
        vpAboutPerson.setAdapter(adapter);
        slingTab.setupWithViewPager(vpAboutPerson);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_about_person_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_movement:
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constant.PERSON_PARCE_KEY, person);
                Intent intent = new Intent(AboutPersonActivity.this, AddMovementActivity.class);
                intent.putExtra(Constant.BUNDLE_KEY, bundle);
                startActivityForResult(intent, Constant.ADD_MOVEMENT_REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //receive movement insert in list movement, reload viewPager
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.ADD_MOVEMENT_REQUEST_CODE){
            if(resultCode == Constant.ADD_MOVEMENT_RESULT_CODE){
                setupViewPager();
                Bundle bundle = data.getBundleExtra(Constant.BUNDLE_KEY);
                // notify MainActivity that there are new movement
                data = new Intent(AboutPersonActivity.this, MainActivity.class);
                data.putExtra(Constant.BUNDLE_KEY, bundle);
                AboutPersonActivity.this.setResult(Constant.ADD_MOVEMENT_RESULT_CODE, data);
            }
        }
    }
}
