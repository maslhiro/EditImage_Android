package uit.nhutvinh.editimage;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import uit.nhutvinh.permisson.AbsRuntimePermission;

public class MainActivity extends AbsRuntimePermission {


    private ImageButton btnEdit,btnCamera,btnInfo,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addConTrols();
        addEvents();

    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        Toast.makeText(MainActivity.this,"Cám ơn đã cấp quyền cho ứng dụng ^^",Toast.LENGTH_LONG).show();
    }


    private void addEvents() {
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOpenEditActivity();
            }
        });
    }

    // Khai bao su kien
    private void addConTrols() {
        btnEdit= (ImageButton) findViewById(R.id.btnEdit);
        btnExit= (ImageButton) findViewById(R.id.btnExit);
        btnInfo= (ImageButton) findViewById(R.id.btnInfo);
        btnCamera= (ImageButton) findViewById(R.id.btnCamera);
    }


    private void doOpenEditActivity() {
        Intent myIntent=new Intent(MainActivity.this, EditActivity.class);
        startActivity(myIntent);
    }


}
