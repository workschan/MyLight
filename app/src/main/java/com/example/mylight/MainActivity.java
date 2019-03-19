package com.example.mylight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isopen = false; //记录手电筒状态
    private Camera camera;
    private Button btn_flash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_flash = findViewById(R.id.btn_flash);
        btn_flash.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isopen) {
                    camera = Camera.open();
                    Parameters params = camera.getParameters();
                    params.setFlashMode(Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(params);
                    camera.startPreview(); // 开始亮灯
                    isopen = true;
                    btn_flash.setText(R.string.close);
                } else {
                    Parameters params = camera.getParameters();
                    params.setFlashMode(Parameters.FLASH_MODE_OFF);
                    camera.setParameters(params);
                    camera.stopPreview(); // 关掉亮灯
                    camera.release(); // 关掉照相机
                    isopen = false;
                    btn_flash.setText(R.string.open);
                }
            }
        });
    }

}