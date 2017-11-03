package com.upyun.upplayer;

import android.content.Intent;

import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;

public class YouPlayer extends UZModule {


    public YouPlayer(UZWebView webView) {
        super(webView);
    }

    public void jsmethod_startYouPlayer(UZModuleContext moduleContext) {
//        Intent intent = new Intent(getContext(), MeetingActivity.class);
        Intent intent = new Intent(getContext(), YouPlayerActivity.class);
        intent.putExtra("appParam", moduleContext.optString("appParam"));
        startActivity(intent);
    }
}
