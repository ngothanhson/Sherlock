package ngothanhson95.dev.com.sherlock.sherlock.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import ngothanhson95.dev.com.sherlock.R;
import ngothanhson95.dev.com.sherlock.sherlock.constant.Constant;
import ngothanhson95.dev.com.sherlock.sherlock.database.DbHelper;
import ngothanhson95.dev.com.sherlock.sherlock.model.Person;


public class AddPersonActivity extends AppCompatActivity {
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
    @Bind(R.id.imgPersonPhoto)
    ImageView imgPersonPhoto;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public final String APP_TAG = "Sherlock";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    private static final int PICK_IMAGE_REQUEST = 1035;
    private Person person = new Person();
    private DbHelper db;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_sherlock);
        getSupportActionBar().setTitle("Add person");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.arrow_left));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPersonActivity.this.finish();
            }
        });
    }


    private void initView() {
        btnsavePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (checkRequiredText() == true) {
                    AlertDialog.Builder confirmAlert = new AlertDialog.Builder(AddPersonActivity.this);
                    int selectedId = rdgGender.getCheckedRadioButtonId();
                    RadioButton rd = (RadioButton) findViewById(selectedId);
                    person.setGender(rd.getText().toString());
                    person.setImage(bitmapToByte(((BitmapDrawable)imgPersonPhoto.getDrawable()).getBitmap()));
                    addPerson();
                    confirmAlert.setTitle("Confirm PersonInfomation");
                    confirmAlert.setMessage(
                            "Person Name: " + person.getName()
                                    + "\nPerson Age: " + person.getAge()
                                    + "\nPerson Height: " + person.getHeight()
                                    + "\nPerson Gender: " + person.getGender());

                    confirmAlert.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            db = new DbHelper(getApplicationContext());
                            db.insertPerson(person);
                            Snackbar.make(view, "Successful", Snackbar.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(Constant.PERSON_PARCE_KEY, person);
                            Intent in = new Intent(AddPersonActivity.this, MainActivity.class);
                            in.putExtra(Constant.BUNDLE_KEY , bundle);
                            AddPersonActivity.this.setResult(Constant.ADD_PERSON_RESULT_CODE, in );
                            AddPersonActivity.this.finish();
//                          Toast.makeText(AddPersonActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(AddPersonActivity.this, MainActivity.class);
//                            startActivity(intent);
                        }
                    });

                    confirmAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                    confirmAlert.create().show();
                }
            }
        });

        imgPersonPhoto.setOnClickListener(new View.OnClickListener() {
            final CharSequence[] items = {"Take Photo", "Choose from Library"};
            @Override
            public void onClick(View view) {
                AlertDialog.Builder selectPhoto = new AlertDialog.Builder(AddPersonActivity.this);
                selectPhoto.setTitle("Add photo");
                selectPhoto.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                onLaunchCamera();
                                break;
                            case 1:
                                onPickGallery();
                                break;
                        }
                    }
                });
                selectPhoto.create().show();
            }
        });


        btnReturnPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(AddPersonActivity.this, MainActivity.class);
//                startActivity(intent);
                AddPersonActivity.this.finish();
            }
        });
    }

    private void onPickGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private boolean checkRequiredText() {
        if (etInputAge.length() == 0 || etInputName.length() == 0 || etInputHeight.length() == 0) {
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
            imgWarningHeight.setVisibility(View.INVISIBLE);
            imgWarningAge.setVisibility(View.INVISIBLE);
            imgWarningName.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    public void onLaunchCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
                imgPersonPhoto.setImageBitmap(imageBitmap);
            } else {
                Toast.makeText(this, "Picture wasn' taken", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imgPersonPhoto.setImageBitmap(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private byte[] bitmapToByte(Bitmap bmp){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    private void addPerson(){
        person.setAddress(etInputAdress.getText().toString());
        person.setAdditionalComment(etInputAdditionalComment.getText().toString());
        person.setName(etInputName.getText().toString());
        person.setHeight(etInputHeight.getText().toString());
        person.setHairColour(etInputHairColor.getText().toString());
        person.setAge(etInputAge.getText().toString());
    }

}
