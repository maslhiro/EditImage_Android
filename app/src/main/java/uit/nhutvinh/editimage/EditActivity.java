package uit.nhutvinh.editimage;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ImageButton;

/**
 * Created by Vin Vin on 28/10/2017.
 */

public class EditActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ImageButton btnBack = (ImageButton) findViewById(R.id.btnSave);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
