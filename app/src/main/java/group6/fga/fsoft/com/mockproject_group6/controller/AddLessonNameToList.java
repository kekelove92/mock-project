package group6.fga.fsoft.com.mockproject_group6.controller;

import android.os.Message;
import android.util.Log;

/**
 * Created by Tu on 19-Apr-18.
 */

class AddLessonNameToList extends BaseState {
    public static final String TAG = AddLessonNameToList.class.getName();

    public AddLessonNameToList(Controller controller) {
        super(controller);
    }

    @Override
    public void handleMsg(Message msg) {
        Log.e("state",TAG);
    }
}
