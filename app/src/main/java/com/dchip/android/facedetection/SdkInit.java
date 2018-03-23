package com.dchip.android.facedetection;

import android.app.Application;

import com.getkeepsafe.relinker.ReLinker;

/**
 * Created by llakcs on 2018/3/23.
 */

public class SdkInit {
    public static void init(Application app){
        ReLinker.recursively().loadLibrary(app,"avutil");
        ReLinker.recursively().loadLibrary(app,"jniswscale");
        ReLinker.recursively().loadLibrary(app,"jniavcodec");
        ReLinker.recursively().loadLibrary(app,"jniavdevice");
        ReLinker.recursively().loadLibrary(app,"jniswresample");
        ReLinker.recursively().loadLibrary(app,"jniavformat");
        ReLinker.recursively().loadLibrary(app,"jnipostproc");
        ReLinker.recursively().loadLibrary(app,"jniavfilter");
        ReLinker.recursively().loadLibrary(app,"avcodec");
        ReLinker.recursively().loadLibrary(app,"avdevice");
        ReLinker.recursively().loadLibrary(app,"avfilter");
        ReLinker.recursively().loadLibrary(app,"avformat");
        ReLinker.recursively().loadLibrary(app,"jniavutil");
        ReLinker.recursively().loadLibrary(app,"jnipostproc");
        ReLinker.recursively().loadLibrary(app,"swresample");
        ReLinker.recursively().loadLibrary(app,"swscale");
    }
}
