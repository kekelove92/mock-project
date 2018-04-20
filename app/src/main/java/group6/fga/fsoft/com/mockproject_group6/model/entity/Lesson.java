package group6.fga.fsoft.com.mockproject_group6.model.entity;

/**
 * Created by TungAnh on 4/16/18.
 */
public class Lesson {
    private int mId;
    private String mName;

    public Lesson(String mName) {
        this.mName = mName;
    }

    public Lesson(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
