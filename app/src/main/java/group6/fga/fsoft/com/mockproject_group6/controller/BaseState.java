package group6.fga.fsoft.com.mockproject_group6.controller;//package com.tunganh.finalmockproject.controller;
//

import android.os.Message;

import group6.fga.fsoft.com.mockproject_group6.model.Model;

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

    public Model getModel() {
        return mController.getMainActivity().getmModel();
    }

}
