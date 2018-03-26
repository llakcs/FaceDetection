package com.dchip.android.facedetection;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.dchip.android.facedetection.Manger.OpencvManager;
import com.dchip.android.facedetection.utils.StorageHelper;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_objdetect;

import java.lang.ref.WeakReference;

import static org.bytedeco.javacpp.opencv_core.LINE_8;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2RGBA;
import static org.bytedeco.javacpp.opencv_imgproc.cvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.rectangle;

/**
 * Created by llakcs on 2018/3/23.
 */

public class OpencvImpl implements OpencvManager {
    private WeakReference<Context> wr  = null;
    private opencv_objdetect.CascadeClassifier faceDetector;
    private int absoluteFaceSize = 0;
    private boolean ism = false;
    private static final Object lock = new Object();
    private static volatile OpencvImpl instance;
    public static void registerInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new OpencvImpl();
                }
            }
        }
        FaceDetection.Ext.setOpencvManager(instance);
    }

    @Override
    public void init(Context context) {
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.M){
            ism = true;
        }
        wr = new WeakReference<Context>(context);
        if(wr.get() == null){
            Log.e("opencvimp","###wr.get() == null");
        }
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                faceDetector = StorageHelper.loadClassifierCascade(wr.get(), R.raw.frontalface);
                return null;
            }
        }.execute();
    }

    @Override
    public void CameraStarted(int width, int height) {
        absoluteFaceSize = (int) (width * 0.32f);
    }

    @Override
    public opencv_core.Mat CameraFrame(opencv_core.Mat rgbaMat) {
        if(ism) {
            cvtColor(rgbaMat, rgbaMat, CV_BGR2RGBA);
        }
        if (faceDetector != null) {
            opencv_core.Mat grayMat = new opencv_core.Mat(rgbaMat.rows(), rgbaMat.cols());
            cvtColor(rgbaMat, grayMat, CV_BGR2GRAY);
            opencv_core.RectVector faces = new opencv_core.RectVector();
            faceDetector.detectMultiScale(grayMat, faces, 1.25f, 3, 1,
                    new opencv_core.Size(absoluteFaceSize, absoluteFaceSize),
                    new opencv_core.Size(4 * absoluteFaceSize, 4 * absoluteFaceSize));
            if (faces.size() == 1) {
                int x = faces.get(0).x();
                int y = faces.get(0).y();
                int w = faces.get(0).width();
                int h = faces.get(0).height();
                rectangle(rgbaMat, new opencv_core.Point(x, y), new opencv_core.Point(x + w, y + h), opencv_core.Scalar.GREEN, 2, LINE_8, 0);
            }

            grayMat.release();

        }

        return rgbaMat;
    }
}
