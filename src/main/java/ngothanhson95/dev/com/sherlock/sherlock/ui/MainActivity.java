package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.adapter.PersonAdapter;
import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;
import ngothanhson95.dev.com.sherlock.sherlock.database.DbHelper;
import ngothanhson95.dev.com.sherlock.sherlock.listener.MyClickListener;
import ngothanhson95.dev.com.sherlock.sherlock.model.Movement;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;

public class MainActivity extends AppCompatActivity implements MyClickListener, SearchView.OnQueryTextListener {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.rvListPerson) RecyclerView rvPerson;
    @Bind(R.id.tvNoperson)
    TextView tvNoperson;
    private PersonAdapter personAdapter;
    private ArrayList<Person> personArrayList;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_sherlock);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Sherlock");
    }

    private void init() {
        personArrayList = new ArrayList<>();
        dbHelper = new DbHelper(getApplicationContext());
        personArrayList = new ArrayList<>(Arrays.asList(dbHelper.getAllPerson()).size());
        personArrayList.addAll(dbHelper.getAllPerson());
        for(Person person : personArrayList){
            person.setMovementCount(dbHelper.getAllMovement(person.getId()).size());
        }
        personAdapter = new PersonAdapter(personArrayList);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvPerson.setLayoutManager(staggeredGridLayoutManager);
        rvPerson.setAdapter(personAdapter);
        this.personAdapter.setOnItemClickListener(this);

        if(personArrayList.size()==0){
            tvNoperson.setVisibility(View.VISIBLE);
        }else {
            tvNoperson.setVisibility(View.INVISIBLE);
        }
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
                Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
                startActivityForResult(intent, Constant.ADD_PERSON_REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, AboutPersonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.PERSON_PARCE_KEY, personArrayList.get(position));
        intent.putExtra(Constant.BUNDLE_KEY, bundle);
        startActivityForResult(intent, Constant.ADD_MOVEMENT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constant.ADD_MOVEMENT_REQUEST_CODE){
            if(resultCode == Constant.ADD_MOVEMENT_RESULT_CODE){
                Bundle bundle = data.getBundleExtra(Constant.BUNDLE_KEY);
                Movement move = bundle.getParcelable(Constant.MOVEMENT_PARCE_KEY);
                int size = personArrayList.size();
                for(int i = 0; i < size; i++) {
                    if(move.getPersonId() == personArrayList.get(i).getId()){
                        personArrayList.get(i).setMovementCount((dbHelper.getAllMovement(personArrayList.get(i).getId())).size());
                        personAdapter.notifyItemChanged(i);
                    }
                }
            }
        }
        if(requestCode == Constant.ADD_PERSON_REQUEST_CODE){
            if(resultCode == Constant.ADD_PERSON_RESULT_CODE){
                Person son = data.getBundleExtra(Constant.BUNDLE_KEY).getParcelable(Constant.PERSON_PARCE_KEY);
                personArrayList.add(son);
                personAdapter.notifyItemInserted(personArrayList.size());
            }
        }
    }

    @Override
    public void onLongClick(final View view, final int position) {
        AlertDialog.Builder deleteAlert = new AlertDialog.Builder(MainActivity.this);
        final Person deletePerson = personArrayList.get(position);
        deleteAlert.setMessage("Are you want to delete all information of " + deletePerson.getName() + "?");
        deleteAlert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper.deletePersonByID(deletePerson.getId());
                dbHelper.deleteAllMovementById(deletePerson.getId());
                personArrayList.remove(position);
                personAdapter.notifyItemRemoved(position);
                Snackbar.make(view, "Deleted", Snackbar.LENGTH_SHORT).show();
            }
        });
        deleteAlert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        deleteAlert.create().show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        personAdapter.filter(query);
        if(!query.isEmpty()){
            getSupportActionBar().setTitle("Result of " + query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        personAdapter.filter(newText);
        return true;
    }
}
