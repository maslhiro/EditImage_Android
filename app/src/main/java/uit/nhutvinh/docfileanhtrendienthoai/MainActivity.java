package uit.nhutvinh.docfileanhtrendienthoai;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {


    private static final int SELECT_PHOTO = 100;
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

    public void xuLyChonAnh(View view) {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                 if (resultCode == RESULT_OK  && null != imageReturnedIntent) {
                     decodeUri(imageReturnedIntent.getData());
                }
        }

    }
      private void addEvents() {
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonAnh(v);
            }
        });
    }

    public void decodeUri(Uri uri) {
        ParcelFileDescriptor parcelFD = null;
        try {
            // Get Bitmap
            parcelFD = getContentResolver().openFileDescriptor(uri, "r");
            assert parcelFD != null;
            FileDescriptor imageSource = parcelFD.getFileDescriptor();

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(imageSource, null, o);

            // the new size we want to scale to
            final int REQUIRED_SIZE = 2048;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(imageSource, null, o2);

            imgHinh.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
           Toast.makeText(MainActivity.this,"File ảnh ko khả dụng !, Vui lòng thủ lại",Toast.LENGTH_LONG).show();
        } finally {
            if (parcelFD != null)
                try {
                    parcelFD.close();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this,"File ảnh ko khả dụng !, Vui lòng thủ lại",Toast.LENGTH_LONG).show();
                    // ignored
                }
        }
    }



    private void addConTrols() {
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        btnChon = (Button) findViewById(R.id.btnChon);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions, @NonNull int[] grantResults) {
                if (requestCode == 1) {
                    if (grantResults.length == 1 &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "Permision Write File is Granted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Permision Write File is Denied", Toast.LENGTH_SHORT).show();

            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void initPermission() {
        // Kiểm tra xem thiết bị chạy trên android 6.0 trở lên hay ko
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                //Permisson don't granted
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(MainActivity.this, "Permission isn't granted ", Toast.LENGTH_SHORT).show();
                }
                // Permisson don't granted and dont show dialog again.
                else {
                    Toast.makeText(MainActivity.this, "Permisson don't granted and dont show dialog again ", Toast.LENGTH_SHORT).show();
                }
                //Register permission
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            }
        }
    }
}