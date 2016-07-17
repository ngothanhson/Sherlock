package ngothanhson95.dev.com.sherlock.sherlock.viewholder;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.listener.PersonItemClickListener;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;

/**
 * Created by ngothanhson95 on 7/16/16.
 */
public class PersonViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnLongClickListener{

    @Bind(R.id.imgMainPhoto)
    ImageView imgMainPhoto;
    @Bind(R.id.tvPersonName)
    TextView tvPersonName;
    @Bind(R.id.tvPersonMovement)
    TextView tvPersonMovement;

    private PersonItemClickListener personItemClickListener;
    public PersonViewHolder(View itemView, PersonItemClickListener itemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.personItemClickListener = itemClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setUpWith(Person person){
        tvPersonName.setText(person.getName());
        tvPersonMovement.setText(person.getAge());
        ByteArrayInputStream input = new ByteArrayInputStream(person.getImage());
        if(input!=null) {
            imgMainPhoto.setImageBitmap(BitmapFactory.decodeStream(input));
        }
    }

    @Override
    public void onClick(View view) {
        if(personItemClickListener != null){
            personItemClickListener.onItemClick(view, getPosition());
        }
    }


    @Override
    public boolean onLongClick(View view) {
        if(personItemClickListener != null){
            personItemClickListener.onLongClick(view, getPosition());
            return  true;
        }
        return false;
    }
}
