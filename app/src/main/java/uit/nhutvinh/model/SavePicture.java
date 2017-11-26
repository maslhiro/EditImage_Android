package uit.nhutvinh.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by Vin Vin on 25/10/2017.
 */

public class SavePicture {

    private ImageView imgHinh;

    public SavePicture(ImageView imgHinh) {
        this.imgHinh = imgHinh;
    }

    public SavePicture() {
    }

    public ImageView getImgHinh() {
        return imgHinh;
    }

    public void setImgHinh(ImageView imgHinh) {
        this.imgHinh = imgHinh;
    }

    public void savePicture(Context context) {
        Bitmap bitmap = ((BitmapDrawable)imgHinh.getDrawable()).getBitmap();
        File sdCardDirectory = Environment.getExternalStorageDirectory().getAbsoluteFile();
        File image = new File(sdCardDirectory, "test.png");

        boolean success = false;

        // Encode the file as a PNG image.
        FileOutputStream outStream;
        try {

            outStream = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        /* 100 to keep full quality of the image */

            outStream.flush();
            outStream.close();
            success = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (success) {
            Toast.makeText(context.getApplicationContext(), sdCardDirectory.toString(),
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context.getApplicationContext(),
                    "Error during image saving", Toast.LENGTH_LONG).show();
        }

    }

}
