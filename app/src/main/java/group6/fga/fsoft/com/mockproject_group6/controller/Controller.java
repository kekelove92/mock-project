package group6.fga.fsoft.com.mockproject_group6.controller;

import android.os.Handler;
import android.os.Message;


import group6.fga.fsoft.com.mockproject_group6.DBManager;
import group6.fga.fsoft.com.mockproject_group6.MainActivity;

public class Controller {
    public static final String TAG = Controller.class.getName();
    private DBManager dbManager;
    private MainActivity mMainActivity;

    DBManager getDBManager() {
        return dbManager;
    }

    public MainActivity getMainActivity() {
        return mMainActivity;
    }

    private static class MsgHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
