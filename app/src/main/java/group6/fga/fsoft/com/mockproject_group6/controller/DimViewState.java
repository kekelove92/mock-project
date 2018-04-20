package group6.fga.fsoft.com.mockproject_group6.controller;

import android.os.Message;
import android.util.Log;

public class DimViewState extends BaseState {
    public static final String TAG = DimViewState.class.getName();

    public DimViewState(Controller controller) {
        super(controller);
    }

    @Override
    public void handleMsg(Message msg) {
        Log.e("state",TAG);
        getModel().setEditingLesson(!getModel().isEditingLesson());
    }
}
