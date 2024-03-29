package com.nucleus.ist_final_projects;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class Show_camera extends SurfaceView implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceHolder holder;
    public Show_camera(Context context, Camera camera) {
        super(context);
        this.camera=camera;
        holder=getHolder();
        holder.addCallback(this);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Camera.Parameters parameters=camera.getParameters();
        if (this.getResources().getConfiguration().orientation!= Configuration.ORIENTATION_LANDSCAPE){
            parameters.set("orientation","protrait");
            camera.setDisplayOrientation(90);
            parameters.setRotation(90);
        }
        else {
            parameters.set("orientation","landscape");
            camera.setDisplayOrientation(0);
            parameters.setRotation(0);
        }

        camera.setParameters(parameters);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }catch (IOException e )
        {
            e.printStackTrace();
        }

    }
}
