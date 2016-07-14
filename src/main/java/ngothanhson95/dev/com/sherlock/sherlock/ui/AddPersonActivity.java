package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;


public class AddPersonActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.btnSavePerson)
    Button btnsavePerson;
    @Bind(R.id.btnReturnPerson)
    Button btnReturnPerson;
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
    @Bind(R.id.rdgGender)
    RadioGroup rdgGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        btnsavePerson.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(checkRequiredText()==true){
            AlertDialog.Builder confirmAlert = new AlertDialog.Builder(AddPersonActivity.this);


            int selectedId = rdgGender.getCheckedRadioButtonId();
            RadioButton rd = (RadioButton) findViewById(selectedId);

            confirmAlert.setTitle("Confirm PersonInfomation");
            confirmAlert.setMessage(
                    "Person Name: " + etInputName.getText()
                            + "\nPerson Age: " + etInputAge.getText()
                            + "\nPerson Height: " + etInputHeight.getText()
                            + "\nPerson Gender: " + rd.getText());

            confirmAlert.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            confirmAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            confirmAlert.create().show();
        }
    }

    private boolean checkRequiredText() {
        if(etInputAge.length()==0||etInputName.length()==0||etInputHeight.length()==0){
            if (etInputName.length() == 0) {
                imgWarningName.setVisibility(View.VISIBLE);
            } else {
                imgWarningName.setVisibility(View.INVISIBLE);
            }
            if (etInputAge.length() == 0) {
                imgWarningAge.setVisibility(View.VISIBLE);
            } else {
                imgWarningAge.setVisibility(View.INVISIBLE);
            }

            if (etInputHeight.length() == 0) {
                imgWarningHeight.setVisibility(View.VISIBLE);
            } else {
                imgWarningHeight.setVisibility(View.VISIBLE);
            }
            return false;
        } else {
            imgWarningHeight.setVisibility(View.VISIBLE);
            imgWarningAge.setVisibility(View.INVISIBLE);
            imgWarningName.setVisibility(View.INVISIBLE);
            return true;
        }
    }
}
