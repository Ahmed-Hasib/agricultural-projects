package com.nucleus.ist_final_projects;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Show_camera show_camera;
    Camera camera;
    FrameLayout frameLayout;
    Button btn_capture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout=(FrameLayout)findViewById(R.id.framelayout);
//        btn_capture=(Button)findViewById(R.id.btn_capture);
//        btn_capture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(getApplicationContext(),"Captured",Toast.LENGTH_SHORT).show();
//            }
//        });

        //open the camera

        camera=Camera.open();
        show_camera=new Show_camera(this,camera);

        Camera.Parameters params = camera.getParameters();
        if (params.getSupportedFocusModes().contains(
                Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
        }
        camera.setParameters(params);




        frameLayout.addView(show_camera);
    }
}
