package com.nihad.filim_app.view.imp.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.nihad.filim_app.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordPage extends AppCompatActivity {


    public static final String TAG = "MainActivity";

    Button btnStartRecording;

    Button btnStopRecording;

    private MediaRecorder mr;
    private String outputPath;

    public static final int REQUEST_CODE_PERMISSION = 0;
    private String[] permision = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_page);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());


        btnStartRecording=findViewById(R.id.btnStartRecording);
        btnStopRecording=findViewById(R.id.btnStopRecording);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time
        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();


        String PATH = "storage/emulated/0/AudioFile";
         String fileName =  formattedDate + ".3gp";

        File directory = new File(PATH);
        if (! directory.exists()){
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        File file = new File(PATH + "/" + fileName);





//        Environment.getRootDirectory();
//        File sdcard = Environment.getRootDirectory();
//        File file = new File(sdcard, "file path");
//        outputPath = "storage/emulated/0/AudioFile" + formattedDate + ".3gp";
        outputPath = file.getPath();

        ActivityCompat.requestPermissions(this, permision, REQUEST_CODE_PERMISSION);
        btnStartRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allAcceptedPermission(permision)) {

                    startRecording();
                }
                else
                {
                    Toast.makeText(RecordPage.this, "You need All permision", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(RecordPage.this, permision, REQUEST_CODE_PERMISSION);
                };
            }
        });

        btnStopRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });


    }


    private void startRecording() {
        mr = new MediaRecorder();
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mr.setOutputFile(outputPath);
        try {
            mr.prepare();
        } catch (Exception e) {
            Log.e(TAG, "StartRecornung error" + "  " + e.toString());

        }
        mr.start();
    }


    private void stopRecording() {
        if (mr != null) {
            mr.stop();
            mr.release();
            mr = null;

        }
    }

    private boolean allAcceptedPermission(String[] permisions) {
        for (String permission : permisions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;

            }

        }
        return true;
    }
}
