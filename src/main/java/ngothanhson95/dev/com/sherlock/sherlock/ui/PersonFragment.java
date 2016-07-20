package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;
import ngothanhson95.dev.com.sherlock.sherlock.database.DbHelper;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;

/**
 * Created by ngothanhson95 on 7/17/16.
 */
public class PersonFragment extends Fragment{

    @Bind(R.id.etInputAge)
    EditText etInputAge;
    @Bind(R.id.etInputHeight)
    EditText etInputHeight;
    @Bind(R.id.etInputName)
    EditText etInputName;
    @Bind(R.id.etInputHairColour)
    EditText etInputHairColor;
    @Bind(R.id.etInputAdress)
    EditText etInputAdress;
    @Bind(R.id.etAdditionalComment)
    EditText etInputAdditionalComment;
    @Bind(R.id.imgWarningName)
    ImageView imgWarningName;
    @Bind(R.id.imgWarningAge)
    ImageView imgWarningAge;
    @Bind(R.id.imgWarningHeight)
    ImageView imgWarningHeight;
    @Bind(R.id.rdMale)
    RadioButton rdMale;
    @Bind(R.id.rdFemale)
    RadioButton rdFemale;
    @Bind(R.id.imgPersonPhoto)
    ImageView imgPersonPhoto;
    @Bind(R.id.btnUpdatePerson)
    Button btnUpdatePerson;
    Person person = new Person();
    DbHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        person = getArguments().getParcelable(Constant.PERSON_PARCE_KEY);
        initView();
        return view;
    }

    private void initView() {
        etInputName.setText(person.getName());
        etInputAge.setText(person.getAge());
        etInputHeight.setText(person.getHeight());
        etInputHairColor.setText(person.getHairColour());
        etInputAdress.setText(person.getAddress());
        etInputAdditionalComment.setText(person.getAdditionalComment());
        ByteArrayInputStream input = new ByteArrayInputStream(person.getImage());
        if (input != null) {
            imgPersonPhoto.setImageBitmap(BitmapFactory.decodeStream(input));
        }
        if (rdMale.getText().toString().toLowerCase().equals(person.getGender().toLowerCase())) {
            rdMale.setChecked(true);
        } else {
            rdFemale.setChecked(true);
        }
        db = new DbHelper(getContext());

        //save update information
        btnUpdatePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person newPerson = new Person();
                newPerson.setName(etInputName.getText().toString());
                newPerson.setAge(etInputAge.getText().toString());
                newPerson.setHeight(etInputHeight.getText().toString());
                newPerson.setHairColour(etInputHairColor.getText().toString());
                newPerson.setAddress(etInputAdress.getText().toString());
                newPerson.setAdditionalComment(etInputAdditionalComment.getText().toString());
                newPerson.setMovementCount(person.getMovementCount());
                newPerson.setId(person.getId());
                if(rdMale.isChecked()){
                    newPerson.setGender(rdMale.getText().toString());
                } else {
                    newPerson.setGender(rdFemale.getText().toString());
                }
                newPerson.setImage(bitmapToByte(((BitmapDrawable)imgPersonPhoto.getDrawable()).getBitmap()));

                db.updatePerson(newPerson);
                Snackbar.make(view, "Updated", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    private byte[] bitmapToByte(Bitmap bmp){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

}
