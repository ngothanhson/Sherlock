package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.adapter.MovementAdapter;
import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;
import ngothanhson95.dev.com.sherlock.sherlock.database.DbHelper;
import ngothanhson95.dev.com.sherlock.sherlock.listener.MyClickListener;
import ngothanhson95.dev.com.sherlock.sherlock.model.Movement;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;

/**
 * Created by ngothanhson95 on 7/18/16.
 */
public class MovementFragment extends Fragment implements MyClickListener {
    public RecyclerView rvListMovement;
    public TextView tvNoMovement;
    public DbHelper dbHelper;
    public MovementAdapter adapter;
    public ArrayList<Movement> movementArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Person person = getArguments().getParcelable(Constant.PERSON_PARCE_KEY);

        View view = inflater.inflate(R.layout.fragment_movement, container, false);
        rvListMovement = (RecyclerView) view.findViewById(R.id.rvListMovement);
        tvNoMovement = (TextView) view.findViewById(R.id.tvNoMovement);
        dbHelper = new DbHelper(getContext());
        movementArrayList = new ArrayList<>(Arrays.asList(dbHelper.getAllMovement(person.getId())).size());
        movementArrayList.addAll(dbHelper.getAllMovement(person.getId()));
        adapter = new MovementAdapter(movementArrayList);
        rvListMovement.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvListMovement.setAdapter(adapter);
        if(movementArrayList.size() == 0){
            tvNoMovement.setVisibility(View.VISIBLE);
        } else {
            tvNoMovement.setVisibility(View.INVISIBLE);
        }
        this.adapter.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.MOVEMENT_PARCE_KEY, movementArrayList.get(position));
        Intent intent = new Intent(this.getContext(), MovementInformationActivity.class);
        intent.putExtra(Constant.BUNDLE_KEY, bundle);
        startActivity(intent);
    }

    @Override
    public void onLongClick(final View view, final int position) {
        AlertDialog.Builder deleteAlert = new AlertDialog.Builder(getContext());
        final Movement deletMovement = movementArrayList.get(position);
        deleteAlert.setMessage("Are you want to delete all information of this movement?");
        deleteAlert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper.deleMovementByID(deletMovement.getMovementId());
                dbHelper.deleMovementByID(deletMovement.getMovementId());
                movementArrayList.remove(position);
                adapter.notifyItemRemoved(position);
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
}
