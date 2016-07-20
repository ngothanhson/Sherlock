package ngothanhson95.dev.com.sherlock.sherlock.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.listener.MyClickListener;
import ngothanhson95.dev.com.sherlock.sherlock.model.Movement;
import ngothanhson95.dev.com.sherlock.sherlock.viewholder.MovementViewHolder;

/**
 * Created by ngothanhson95 on 7/18/16.
 */
public class MovementAdapter extends RecyclerView.Adapter<MovementViewHolder> {

    public ArrayList<Movement> movementArrayList = new ArrayList<>();

    public MovementAdapter(ArrayList<Movement> movementArrayList) {
        this.movementArrayList = movementArrayList;
    }

    private MyClickListener myClickListener;


    @Override
    public MovementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movement, parent, false);
        MovementViewHolder movementViewHolder = new MovementViewHolder(v, myClickListener);
        return  movementViewHolder;
    }

    @Override
    public void onBindViewHolder(MovementViewHolder holder, int position) {
        Movement movement = movementArrayList.get(position);
        holder.setupWith(movement);
    }

    @Override
    public int getItemCount() {
        return movementArrayList.size();
    }

    public void setOnItemClickListener(MyClickListener listener){
        this.myClickListener = listener;
    }

    public void setOnItemLongClickListener(MyClickListener listener){
        this.myClickListener = listener;
    }

}
