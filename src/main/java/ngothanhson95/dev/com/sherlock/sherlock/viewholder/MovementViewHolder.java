package ngothanhson95.dev.com.sherlock.sherlock.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.listener.MyClickListener;
import ngothanhson95.dev.com.sherlock.sherlock.model.Movement;

/**
 * Created by ngothanhson95 on 7/18/16.
 */
public class MovementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    @Bind(R.id.tvMovementNote)
    TextView tvMovementNote;
    @Bind(R.id.tvMovementLocation)
    TextView tvMovementLocation;
    @Bind(R.id.tvMovementTime)
    TextView tvMovementTime;
    private MyClickListener myClickListener;


    public MovementViewHolder(View itemView, MyClickListener myClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.myClickListener = myClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setupWith(Movement movemet){
        tvMovementNote.setText(movemet.getMovementNote());
        tvMovementLocation.setText(movemet.getMovementLocation());
        tvMovementTime.setText(movemet.getMovementTime());
    }


    @Override
    public void onClick(View view) {
        if(myClickListener != null){
            myClickListener.onItemClick(view, getPosition());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if(myClickListener != null){
            myClickListener.onLongClick(view, getPosition());
            return  true;
        }
        return false;
    }
}
