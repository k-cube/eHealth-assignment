package com.example.keivannorouzi.ehealthtest;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.orm.SugarContext;

public class AppController extends Application {

    public static final String TAG = AppController.class
            .getSimpleName();


    private RequestQueue mRequestQueue;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        SugarContext.init(this);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
