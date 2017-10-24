package uit.nhutvinh.docfileanhtrendienthoai;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

import uit.nhutvinh.model.TakePicture;
import uit.nhutvinh.permission.AbsRuntimePermission;


public class MainActivity extends AbsRuntimePermission {

    private static final int REQUEST_PERMISSION = 10;
    private static final int SELECT_PHOTO = 100;
    TakePicture takePicture;
    ImageView imgHinh;
    Button btnChon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPermission();

        addConTrols();
        addEvents();
    }
    private void addEvents() {
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(v);
            }
        });
    }

    private void addConTrols() {

        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        btnChon = (Button) findViewById(R.id.btnChon);
        takePicture= new TakePicture(imgHinh);
    }

    public void takePicture(View view) {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK && null != imageReturnedIntent) {
                    //decodeUri(imageReturnedIntent.getData());
                    takePicture.decodeUri(MainActivity.this,imageReturnedIntent.getData());
                }
        }

    }

    private void initPermission() {
        requestAppPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},
                R.string.smg,REQUEST_PERMISSION);

    }

}