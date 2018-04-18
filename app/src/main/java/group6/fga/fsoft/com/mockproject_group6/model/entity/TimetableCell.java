package group6.fga.fsoft.com.mockproject_group6.model.entity;

/**
 * Created by TungAnh on 4/16/18.
 */
public class TimetableCell {
    private int mLessonID;
    private String mLessonName;
    private int mCol;
    private int mRow;

    public TimetableCell(int lessonID, String lessonName, int col, int row) {
        mLessonID = lessonID;
        mLessonName = lessonName;
        mCol = col;
        mRow = row;
    }

    public int getLessonID() {
        return mLessonID;
    }

    public void setLessonID(int lessonID) {
        mLessonID = lessonID;
    }

    public String getLessonName() {
        return mLessonName;
    }

    public void setLessonName(String lessonName) {
        mLessonName = lessonName;
    }

    public int getCol() {
        return mCol;
    }

    public void setCol(int col) {
        mCol = col;
    }

    public int getRow() {
        return mRow;
    }

    public void setRow(int row) {
        mRow = row;
    }

    @Override
    public String toString() {
        return "TimetableCell{" +
            "mLessonID=" + mLessonID +
            ", mLessonName='" + mLessonName + '\'' +
            ", mCol=" + mCol +
            ", mRow=" + mRow +
            '}';
    }
}
