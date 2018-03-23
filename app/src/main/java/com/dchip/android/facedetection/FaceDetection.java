package com.dchip.android.facedetection;

import android.app.Application;
import android.content.Context;

import com.dchip.android.facedetection.Manger.OpencvManager;

import java.lang.reflect.Method;

/**
 * Created by llakcs on 2018/3/23.
 */

public class FaceDetection {
    public FaceDetection() {
    }

    public static Application app(){

        if (FaceDetection.Ext.app == null) {
            try {
                // 在IDE进行布局预览时使用
                Class<?> renderActionClass = Class.forName("com.android.layoutlib.bridge.impl.RenderAction");
                Method method = renderActionClass.getDeclaredMethod("getCurrentContext");
                Context context = (Context) method.invoke(null);
                FaceDetection.Ext.app = new FaceDetection.MockApplication(context);
            } catch (Throwable ignored) {
                throw new RuntimeException("please invoke x.Ext.init(app) on Application#onCreate()"
                        + " and register your Application in manifest.");
            }
        }
        return FaceDetection.Ext.app;
    }
    private static class MockApplication extends Application {
        public MockApplication(Context baseContext) {
            this.attachBaseContext(baseContext);
        }
    }

    /**
     * 返回 人面识别管理器
     *
     * @return the opencv manager
     */
    public static OpencvManager opencv(){
        if(Ext.opencvManager == null){
            OpencvImpl.registerInstance();
        }
        return Ext.opencvManager;
    }

    public static class Ext{
        public Ext() {
        }
        private static Application app;
        private static OpencvManager opencvManager;
//        public static void init(Application app){
//            if (Ext.app == null) {
//                Ext.app = app;
//            }
////            SdkInit.init(Ext.app);
//        }

        /**
         * 设置人面识别管理器
         *
         * @param opencvManager the opencv manager
         */
        public static void setOpencvManager(OpencvManager opencvManager){
            Ext.opencvManager = opencvManager;
        }

    }
}
