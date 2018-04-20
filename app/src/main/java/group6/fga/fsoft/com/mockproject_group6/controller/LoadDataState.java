package group6.fga.fsoft.com.mockproject_group6.controller;

import android.database.Cursor;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import group6.fga.fsoft.com.mockproject_group6.model.Contract;
import group6.fga.fsoft.com.mockproject_group6.model.entity.Lesson;

public class LoadDataState extends BaseState {
    public static final String TAG = LoadDataState.class.getName();

    public LoadDataState(Controller controller) {
        super(controller);
    }

    @Override
    public void handleMsg(Message msg) {
        Log.e("state", TAG);

        List<Object> mListLesson = new ArrayList<>();
        List<Object> mListTimetable = new ArrayList<>();

        loadListLesson(mListLesson);
        loadTimetable(mListTimetable);

        getModel().setLessonList(mListLesson);
        getModel().setTimetable(mListTimetable);

    }

    private void loadTimetable(List<Object> mListTimetable) {

    }

    private void loadListLesson(List<Object> mListLesson) {
        Cursor cursorLessons = mController.getmLessonDatabase().getAllLesson();

        if (cursorLessons.moveToFirst()) {
            do {
                int id = cursorLessons.getInt(cursorLessons.getColumnIndex(Contract.LESSON_ID));
                String name = cursorLessons.getString(cursorLessons.getColumnIndex(Contract.LESSON_NAME));
                Lesson lesson = new Lesson(id, name);
                mListLesson.add(lesson);
            } while (cursorLessons.moveToNext());
        }
        cursorLessons.close();
    }


}
