package com.dchip.android.facedetection.Manger;

import android.content.Context;

import org.bytedeco.javacpp.opencv_core;

/**
 * Created by llakcs on 2018/3/23.
 */

public interface OpencvManager {

    void init(final Context context);
    void CameraStarted(int width, int height);
    opencv_core.Mat CameraFrame(opencv_core.Mat rgbaMat);
}
