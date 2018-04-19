package group6.fga.fsoft.com.mockproject_group6.controller;//package com.tunganh.finalmockproject.controller;
//

import android.os.Message;

/**
 * Created by public on 3/13/2018.
 */
public class BaseState {

    protected Controller mController;

    public BaseState(Controller controller) {
        mController = controller;
    }

    public void handleMsg(Message msg){
        //......
    }

}
