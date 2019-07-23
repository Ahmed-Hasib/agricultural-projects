package com.nucleus.opencv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener {

    JavaCameraView jvcamera;
    Mat mat1,mat2;
    Scalar scalarlow,scalarhight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (!OpenCVLoader.initDebug()){
            Toast.makeText(getApplicationContext(),"Not loaded",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"loaded",Toast.LENGTH_LONG).show();
        }

        jvcamera=(JavaCameraView)findViewById(R.id.jvcamera);
        jvcamera.setCameraIndex(0);

        scalarlow=new Scalar(45,20,10);
        scalarhight=new Scalar(75,255,255);

        jvcamera.setCvCameraViewListener(this);
        jvcamera.enableView();

    }

    @Override
    protected void onPause() {
        super.onPause();
        jvcamera.disableView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        jvcamera.enableView();
    }

    @Override
    protected void onDestroy() {
        jvcamera.disableView();
        super.onDestroy();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
     mat1=new Mat(width,height, CvType.CV_16UC4);
     mat2=new Mat(width,height,CvType.CV_16UC4);
    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(Mat inputFrame) {
        Imgproc.cvtColor(inputFrame,mat1,Imgproc.COLOR_BGR2HSV);
        Core.inRange(mat1,scalarlow,scalarhight,mat2);

        return mat2;
    }
}
