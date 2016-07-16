package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.adapter.PersonAdapter;
import ngothanhson95.dev.com.sherlock.sherlock.database.PersonDbHelper;
import ngothanhson95.dev.com.sherlock.sherlock.listener.PersonItemClickListener;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;

public class MainActivity extends AppCompatActivity implements PersonItemClickListener, SearchView.OnQueryTextListener {

    private RecyclerView rvPerson;
    private PersonAdapter personAdapter;
    private ArrayList<Person> personArrayList;
    private PersonDbHelper personDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_sherlock);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        init();
    }


    private void init() {
        personArrayList = new ArrayList<>();
        personDbHelper = new PersonDbHelper(getApplicationContext());
        personArrayList = new ArrayList<>(Arrays.asList(personDbHelper.getAllPerson()).size());
        personArrayList.addAll(personDbHelper.getAllPerson());
        personAdapter = new PersonAdapter(personArrayList);
        rvPerson = (RecyclerView) findViewById(R.id.rvListPerson);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvPerson.setLayoutManager(staggeredGridLayoutManager);
        rvPerson.setAdapter(personAdapter);
        this.personAdapter.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);

        SearchManager  searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =     (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:

                return true;
            case R.id.action_cloud:
                return true;
            case R.id.action_personaladd:
                addPerson();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addPerson() {
        Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Da chon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        personAdapter.filter(newText);
        return true;
    }
}
