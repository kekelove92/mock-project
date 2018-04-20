package group6.fga.fsoft.com.mockproject_group6.model;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import group6.fga.fsoft.com.mockproject_group6.model.entity.Lesson;
import group6.fga.fsoft.com.mockproject_group6.model.entity.Timetable;

/**
 * Created by TungAnh on 4/16/18.
 */
public class Model {
    public static final String UPDATE_TIMETABLE = "UPDATE_TIMETABLE";
    public static final String UPDATE_LESSON_LIST = "UPDATE_LESSON_LIST";
    public static final String UPDATE_DIM_VIEW = "UPDATE_DIM_VIEW ";

    private List<Object> mLessonList;
    private List<Object> mTimetableList;

    private boolean mEditingLesson;
    private PropertyChangeSupport mPropertyChangeSupport;

    public Model() {
        mPropertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void setPropertyChangeSupportListener(PropertyChangeListener listener) {
        mPropertyChangeSupport.addPropertyChangeListener(listener);
    }


    public List<Object> getLessonList() {
        return mLessonList;
    }

    public void setLessonList(List<Object> newLessonList) {
        mLessonList = newLessonList;
        mPropertyChangeSupport.firePropertyChange(UPDATE_LESSON_LIST, null, newLessonList);
    }

    public List<Object> getTimetableCellList() {
        return mTimetableList;
    }

    public void setTimetableCellList(List<Object> newTimetableList) {
        mTimetableList = newTimetableList;
        mPropertyChangeSupport.firePropertyChange(UPDATE_TIMETABLE, null, newTimetableList);
    }

    public boolean isEditingLesson() {
        return mEditingLesson;
    }

    public void setEditingLesson(boolean isEditing) {
        boolean oldMode = mEditingLesson;
        mEditingLesson = isEditing;
        mPropertyChangeSupport.firePropertyChange(UPDATE_DIM_VIEW, oldMode, mEditingLesson);
    }
}
