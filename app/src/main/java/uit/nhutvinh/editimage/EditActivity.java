package uit.nhutvinh.editimage;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;


import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import uit.nhutvinh.model.SavePicture;
import uit.nhutvinh.model.TakePicture;


/**
 * Created by Vin Vin on 28/10/2017.
 */

public class EditActivity extends AppCompatActivity {


    private static final int SELECT_PHOTO = 100;

    private ImageButton btnTakePicture,btnSavePicture;
    private SavePicture savePicture;
    private TakePicture takePicture;
    private ImageView imgPicture;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        addConTrols();
        addEvents();


    }

    private void addEvents() {

        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(v);
            }
        });

    }


    private void addConTrols() {
        btnTakePicture = (ImageButton) findViewById(R.id.btnInsertPicture);
        btnSavePicture =  (ImageButton) findViewById(R.id.btnSave);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);

        takePicture= new TakePicture(imgPicture);
        savePicture = new SavePicture(imgPicture);
    }

    public void takePicture(View view) {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK && null != imageReturnedIntent) {
                    //decodeUri(imageReturnedIntent.getData());
                    takePicture.decodeUri(this,imageReturnedIntent.getData());
                }
        }

    }


}
