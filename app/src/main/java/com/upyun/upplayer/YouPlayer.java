package com.upyun.upplayer;

import android.content.Intent;
import android.content.Loader;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.upyun.upplayer.widget.UpVideoView;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

public class YouPlayer extends UZModule {


    private UpVideoView upVideoView;

    public YouPlayer(UZWebView webView) {
        super(webView);
    }

    //    public void jsmethod_startYouPlayer(UZModuleContext moduleContext) {
////        Intent intent = new Intent(getContext(), MeetingActivity.class);
//        Intent intent = new Intent(getContext(), YouPlayerActivity.class);
//        intent.putExtra("appParam", moduleContext.optString("appParam"));
//        startActivity(intent);
//    }
    public void jsmethod_startYouPlayer(UZModuleContext moduleContext) {
        int x = moduleContext.optInt("x");
        int y = moduleContext.optInt("y");
        int w = moduleContext.optInt("w");
        int h = moduleContext.optInt("h");
        if (w == 0) {
            w = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        if (h == 0) {
            h = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        upVideoView = new UpVideoView(mContext);
        String path = moduleContext.optString("appParam");
        String fixedOn = moduleContext.optString("fixedOn");
        boolean fixed = moduleContext.optBoolean("fixed", true);

        //设置播放地址
        upVideoView.setVideoPath(path);
        //开始播放
        upVideoView.start();

        int FROM_TYPE = Animation.RELATIVE_TO_PARENT;
        Animation anim = new TranslateAnimation(FROM_TYPE, 1.0f, FROM_TYPE, 0.0f, FROM_TYPE, 0.0f, FROM_TYPE, 0.0f);
        anim.setDuration(500);
        upVideoView.setAnimation(anim);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(w, h);
        rlp.leftMargin = x;
        rlp.topMargin = y;
//        insertViewToCurWindow(upVideoView, rlp, fixedOn, fixed);//线上
        insertViewToCurWindow(upVideoView, rlp);//线下
        Log.e("ggg", path);
    }

    protected void onClean() {
        this.upVideoView.pause();
        this.upVideoView.release(true);
    }
}
