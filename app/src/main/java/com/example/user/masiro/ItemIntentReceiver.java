package com.example.user.masiro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by User on 2017-05-28.
 */

public class ItemIntentReceiver extends BroadcastReceiver {

    private String mExpectedAction;
    private Intent mLastReceivedIntent;

    public ItemIntentReceiver(String expectedAction){
        mExpectedAction = expectedAction;
        mLastReceivedIntent = null;
    }

    public IntentFilter getFilter(){
        IntentFilter filter = new IntentFilter(mExpectedAction);
        return filter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null){

            mLastReceivedIntent = intent;

            int id = intent.getIntExtra("id",0);

            double latitude = intent.getDoubleExtra("latitude",0.0D);
            double longitude = intent.getDoubleExtra("longitude",0.0D);
        }
    }

    public Intent getLastReceivedIntent(){
        return mLastReceivedIntent;
    }

    public void clearReceivedIntents(){
        mLastReceivedIntent = null;
    }
}
