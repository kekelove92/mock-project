package group6.fga.fsoft.com.mockproject_group6.model;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import group6.fga.fsoft.com.mockproject_group6.model.entity.Lesson;
import group6.fga.fsoft.com.mockproject_group6.model.entity.TimetableCell;

/**
 * Created by TungAnh on 4/16/18.
 */
public class Model {
    public static final String UPDATE_TIMETABLE = "UPDATE_TIMETABLE";
    public static final String UPDATE_LESSON_LIST = "UPDATE_LESSON_LIST";

    private ArrayList<Lesson> mLessonsList;
    private ArrayList<TimetableCell> mTimetableCellsList;

    private boolean mEditingLesson;
    private PropertyChangeSupport mPropertyChangeSupport;

    public Model() {
        mPropertyChangeSupport = new PropertyChangeSupport(this);
    }

    public ArrayList<Lesson> getLessonsList() {
        return mLessonsList;
    }

    public void setLessonsList(
        ArrayList<Lesson> lessonsList) {
        mLessonsList = lessonsList;
    }

    public void setPropertyChangeSupportListener(PropertyChangeListener listener) {
        mPropertyChangeSupport.addPropertyChangeListener(listener);
    }

    public ArrayList<TimetableCell> getTimetableCellsList() {
        return mTimetableCellsList;
    }

    public void setTimetableCellsList(
        ArrayList<TimetableCell> timetableCellsList) {
        mTimetableCellsList = timetableCellsList;
    }

    public boolean isEdttingLesson() {
        return mEditingLesson;
    }

    public void setEditingLesson(boolean editingLesson) {
        mEditingLesson = editingLesson;
    }
}
