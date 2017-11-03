package com.upyun.upplayer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.upyun.upplayer.widget.UpVideoView;
import com.uzmap.pkg.uzcore.UZResourcesIDFinder;

import org.json.JSONException;
import org.json.JSONObject;

public class YouPlayerActivity extends Activity {

    //    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/upyunb";
//    String path = "rtmp://rtmptest.b0.upaiyun.com/live/default4demo33596ad21e01c659489973d38c4d2c56d9mic";
//    String path = "http://rtmptest.b0.upaiyun.com/live/default4demo33596ad21e01c659489973d38c4d2c56d9mic.m3u8";
    String path = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
//    String path = "rtmp://play.jiashizhan.com/live/streamkey=lili&roomid=23266772&dataid=3266&isApp=1";
//    String path = "/mnt/sdcard/storage/emulated/0/2651H.mp4";
//    String path = "/mnt/sdcard/videotest/2641H.mp4";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/myapp11";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/upyunab";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/myapp11";
//    String path = "rtmp://www.zhibo.58youxian.cn/uplive/test111";
//    String path = "rtmp://115.231.100.126/live/upyunab";
//    String path = "/mnt/sdcard/test.mp3";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/upyunaa";
//    String path = "rtmp://testlivesdk.b0.upaiyun.com/live/test131";

    //    String path_yin = "http://save.jiashizhan.com:8081/video/20171027/130023675_020714/3675_20171027020714231_av.mp4";
//    String path_yin = "http://save.jiashizhan.com:8081/video/20171027/130023675_020714/3160_20171027020714527_av.mp4";

    private static final String TAG = YouPlayerActivity.class.getSimpleName();
    RelativeLayout.LayoutParams mVideoParams;

    UpVideoView upVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        int layoutId = UZResourcesIDFinder.getResLayoutID("mo_youplayer_activity_main");
        if (layoutId > 0) {
            setContentView(layoutId);
        } else {
            Toast.makeText(this, "文件丢失!", Toast.LENGTH_LONG).show();
            return;
        }
        // --- 获取连接房间参数
        Intent intent = getIntent();
        path = intent.getStringExtra("appParam");
//        try {
//            JSONObject object = new JSONObject(msg);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        int uvv_vidoId = UZResourcesIDFinder.getResIdID("uvv_vido");
        upVideoView = (UpVideoView) findViewById(uvv_vidoId);
//        upVideoView.setHudView(mHudView);

        //设置背景图片
//        upVideoView.setImage(R.drawable.dog);

        //设置播放地址
        upVideoView.setVideoPath(path);
//        upVideoView.setMediaController(new MediaController(this, true));

        //开始播放
        upVideoView.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 重新开始播放器
        upVideoView.resume();
        upVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        upVideoView.pause();
    }

//    public void toggle(View view) {
//
//        if (upVideoView.isPlaying()) {
//            //暂停播放
//            upVideoView.pause();
//        } else {
//            //开始播放
//            upVideoView.start();
//        }
//    }

//    public void refresh(View view) {
//        upVideoView.setVideoPath(path);
//        upVideoView.start();
//    }

//    //全屏播放
//    public void fullScreen(View view) {
//        upVideoView.fullScreen(this);
//    }

//    private void fullScreen() {
//        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(metrics.widthPixels, metrics.heightPixels);
//        mVideoParams = (RelativeLayout.LayoutParams) upVideoView.getLayoutParams();
//        upVideoView.setLayoutParams(params);
//        upVideoView.getTrackInfo();
//    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {

//        if (upVideoView.isFullState()) {
//            //退出全屏
//            upVideoView.exitFullScreen(this);
//            return;
//        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        upVideoView.release(true);
    }
}
