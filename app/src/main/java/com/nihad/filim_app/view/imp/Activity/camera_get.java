package com.nihad.filim_app.view.imp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nihad.filim_app.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class camera_get extends AppCompatActivity {

//    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
//    @BindView(R.id.btn_start_capture)
//    Button btnStartCapture;
//    Uri imageUri ;
//    String outputPath;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera_get);
//        ButterKnife.bind(this);
//
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        String formattedDate = df.format(c.getTime());
//        String PATH = "storage/emulated/0/AudioFile";
//        String fileName =  formattedDate + ".jpg";
//
//        File directory = new File(PATH);
//        if (! directory.exists()){
//            directory.mkdir();
//            // If you require it to make the entire directory path including parents,
//            // use directory.mkdirs(); here instead.
//        }
//
//        File file = new File(PATH + "/" + fileName);
//
//
//
//
//
////        Environment.getRootDirectory();
////        File sdcard = Environment.getRootDirectory();
////        File file = new File(sdcard, "file path");
////        outputPath = "storage/emulated/0/AudioFile" + formattedDate + ".3gp";
//        outputPath = file.getPath();
//        btnStartCapture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"fname_" +
//                        String.valueOf(System.currentTimeMillis()) + ".jpg"));
//                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//
//        if (resultCode == RESULT_OK) {
//            if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
//
//                //use imageUri here to access the image
//
//                Bundle extras = data.getExtras();
//
//                Log.e("URI", imageUri.toString());
//
//                Bitmap bmp = (Bitmap) extras.get("data");
//
//                // here you will get the image as bitmap
//
//
//            } else if (resultCode == RESULT_CANCELED) {
//                Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//    }


    private static final String EXTRA_FILENAME =
            "com.commonsware.android.camcon.EXTRA_FILENAME";
    private static final String FILENAME = "CameraContentDemo.jpeg";
    private static final int CONTENT_REQUEST = 1337;
    @BindView(R.id.btn_start_capture)
    Button btnStartCapture;
    private File output = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_get);
        ButterKnife.bind(this);

        btnStartCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (savedInstanceState == null) {
                    File dir = getExternalFilesDir(Environment.DIRECTORY_DCIM);

                    dir.mkdirs();
                    output = new File(dir, FILENAME);
                } else {
                    output = (File) savedInstanceState.getSerializable(EXTRA_FILENAME);
                }

                if (output.exists()) {
                    output.delete();
                }

                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                i.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));

                startActivityForResult(i, CONTENT_REQUEST);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(EXTRA_FILENAME, output);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Intent i = new Intent(Intent.ACTION_VIEW);

                i.setDataAndType(Uri.fromFile(output), "image/jpeg");
                startActivity(i);
                finish();
            }
        }
    }

}
