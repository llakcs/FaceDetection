package com.dchip.android.facedetection;

import android.app.Application;

import com.getkeepsafe.relinker.ReLinker;

/**
 * Created by llakcs on 2018/3/23.
 */

public class SdkInit {
    public static void init(Application app){
        ReLinker.recursively().loadLibrary(app,"opencv_core");
        ReLinker.recursively().loadLibrary(app,"jniopencv_core");
        ReLinker.recursively().loadLibrary(app,"opencv_imgproc");
    }
}
