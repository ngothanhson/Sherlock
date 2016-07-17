package ngothanhson95.dev.com.sherlock.sherlock.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.listener.PersonItemClickListener;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;
import ngothanhson95.dev.com.sherlock.sherlock.viewholder.PersonViewHolder;

/**
 * Created by ngothanhson95 on 7/16/16.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {
    public ArrayList<Person> personArrayList = new ArrayList<>();
    public ArrayList<Person> personArrayListCopy = new ArrayList<>();
    private PersonItemClickListener personItemClickListener;

    public PersonAdapter(ArrayList<Person> personArrayList) {
        this.personArrayList = personArrayList;
        personArrayListCopy.addAll(personArrayList);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        PersonViewHolder personViewHolder = new PersonViewHolder(v, personItemClickListener);
        return  personViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Person person  = personArrayList.get(position);
        holder.setUpWith(person);

    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    public void setOnItemClickListener(PersonItemClickListener listener){
        this.personItemClickListener = listener;
    }

    public void setOnItemLongClickListener(PersonItemClickListener listener){
        this.personItemClickListener = listener;
    }

    public void filter(String text){
        if(text.trim().isEmpty()){
            personArrayList.clear();
            personArrayList.addAll(personArrayListCopy);
        } else {
            ArrayList<Person> result = new ArrayList<>();
            text = text.toLowerCase();
            for(Person person : personArrayListCopy){
                if(person.getName().toLowerCase().contains(text)){
                    result.add(person);
                }
            }
            personArrayList.clear();
            personArrayList.addAll(result);
        }
        notifyDataSetChanged();
    }
}