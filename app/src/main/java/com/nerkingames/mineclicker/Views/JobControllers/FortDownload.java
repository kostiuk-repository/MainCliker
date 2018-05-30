package com.nerkingames.mineclicker.Views.JobControllers;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

import com.nerkingames.mineclicker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladyslav on 22.03.18.
 */

public class FortDownload extends BaseJobController implements View.OnTouchListener{


    public static final String ID = "FortDownload";
    private int onMode = R.mipmap.fort_download;
    private int offMode = R.mipmap.fort_download;
    private int help = R.mipmap.fort_download;
    private BaseJobController baseJobController;
    private static FortDownload instance;
    private Context context;

    public BaseJobController getIT() {
        return baseJobController;
    }




    public static synchronized FortDownload getInstance(Context context) {
        if (instance == null) {
            instance = new FortDownload(context);
        }
        return instance;
    }


    private FortDownload(Context context) {
        super(context);
        this.context = context;
        baseJobController = new BaseJobController.Builder(context)
                .setId(ID)
                .setOn(onMode)
                .setOff(offMode)
                .setHelp(help)
                .setCompareList(1)
                .setStatuses(STATUS.NOTHING)
                .setMinusValueList(0)
                .setOnTouchListener(this)
                .setClickable(true)
                .setBaseJobControllerListSubscriptions()
                .setBaseJobControllerListSubscribers()
                .build();
        baseJobController.getIT().getViewContainer().removeAllViews();
    }


    public View getViewScene(int subscribersCounter,int subscriptionsCounter,BaseJobController ... baseJobControllers) {
        List<BaseJobController> localList = new ArrayList<>();
        for (BaseJobController b :baseJobControllers) {
            localList.add(b);
        }
        for (int i = 0; i < subscribersCounter; i++) {
            baseJobController.setBaseJobControllerListSubscribers(localList.get(i));
        }
        for (int i = 0; i < subscriptionsCounter; i++) {
            baseJobController.setBaseJobControllerListSubscriptions(localList.get(subscribersCounter + i));
        }

        return baseJobController.getViewContainer();
    }

    public View getViewScene(){
        return baseJobController.getViewContainer();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                if(Fort.getInstance(context).getIT().getViewCounter()>=1) {
                    Uri uri = Uri.parse("https://goo.gl/RgPAJ1");
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    likeIng.setPackage("com.google.android.apps.docs");

                    try {
                        context.startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://goo.gl/RgPAJ1")));
                    }

                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return false;
    }


}
