package com.example.app2.SampleProject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app2.R;

public class PermissionActivity extends AppCompatActivity {

    Button btnChoose;
    ImageView img;
    private static final int REQUEST_CODE = 1001;
    private static final int REQUEST_PERMISSION_CODE = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        btnChoose = (Button) findViewById(R.id.btn_permission_choosePic);
        img = (ImageView) findViewById(R.id.img_permission_image);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {

                    // PERMISSION_GRANTED : yani ghBLn permission dadeh shodeh ast,
                    // PERMISSION_DENY:yani ghBLn permission dadeh nashodeh.
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                    } else {
                        choosePicture();
                    }

                } else {
                    choosePicture();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            String[] info = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(uri, info, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(info[0]);
            String filePath = cursor.getString(columnIndex);
            img.setImageBitmap(BitmapFactory.decodeFile(filePath));
        }
    }

    private void choosePicture() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "you accepted permission", Toast.LENGTH_SHORT).show();
            choosePicture();
        } else {
            Toast.makeText(this, "you denied permission", Toast.LENGTH_SHORT).show();
        }
    }
}
