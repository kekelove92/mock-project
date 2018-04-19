package group6.fga.fsoft.com.mockproject_group6.controller;

import android.os.Message;
import android.util.Log;

public class EditLessonNameState extends BaseState {
    public static final String TAG = EditLessonNameState.class.getName();

    public EditLessonNameState(Controller controller) {
        super(controller);
    }

    @Override
    public void handleMsg(Message msg) {
        Log.e("state",TAG);
    }
}
