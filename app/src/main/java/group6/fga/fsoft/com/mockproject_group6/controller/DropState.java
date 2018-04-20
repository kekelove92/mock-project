package group6.fga.fsoft.com.mockproject_group6.controller;

import android.os.Message;
import android.util.Log;

public class DropState extends BaseState {

    public static final int FROM_TIMETABLE_TO_TIMETABLE = 0;
    public static final int FROM_LESSONS_TO_TIMETABLE = 4;
    public static final int DELETE_LESSON = 1;
    public static final int REPLACE_LESSON = 2;
    public static final int ADD_LESSON_TO_TIMETABLE = 3;

    public static final String TAG = DropState.class.getName();


    public DropState(Controller controller) {
        super(controller);
    }

    @Override
    public void handleMsg(Message msg) {
        Log.e("state",TAG);

        if(msg.arg1==REPLACE_LESSON){
            Log.e("action","REPLACE_LESSON");
            if(msg.arg2 == FROM_TIMETABLE_TO_TIMETABLE){

            }else if(msg.arg2 == FROM_LESSONS_TO_TIMETABLE){
                // hien thi dialog dang ky chu ky
            }

        }else if(msg.arg1 == DELETE_LESSON){
            Log.e("action","DELETE_LESSON");

        }else if(msg.arg1 == ADD_LESSON_TO_TIMETABLE){
            Log.e("action","ADD_LESSON_TO_TIMETABLE");
            // hien thi dialog dang ky chu ky

        }

    }
}
