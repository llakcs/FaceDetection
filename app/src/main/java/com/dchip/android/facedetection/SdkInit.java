package com.dchip.android.facedetection;

import android.app.Application;

import com.getkeepsafe.relinker.ReLinker;

/**
 * Created by llakcs on 2018/3/23.
 */

public class SdkInit {
    public static void init(Application app){
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"avutil");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"jniswscale");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"jniavcodec");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"jniavdevice");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"jniswresample");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"jniavformat");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"jnipostproc");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"jniavfilter");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"avcodec");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"avdevice");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"avfilter");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"avformat");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"jniavutil");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"jnipostproc");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"swresample");
        ReLinker.recursively().loadLibrary(app.getApplicationContext(),"swscale");
    }
}
