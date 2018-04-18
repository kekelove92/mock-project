package group6.fga.fsoft.com.mockproject_group6.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TungAnh on 4/16/18.
 */
public class Lesson implements Parcelable {
    private int mId;
    private String mName;

    public Lesson(String mName) {
        this.mName = mName;
    }

    public Lesson(int id, String name) {
        mId = id;
        mName = name;
    }

    public static Creator<Lesson> getCREATOR() {
        return CREATOR;
    }

    protected Lesson(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
    }

    @Override
    public String toString() {
        return "Lesson{" +
            "mId=" + mId +
            ", mName='" + mName + '\'' +
            '}';
    }

    public static final Creator<Lesson> CREATOR = new Creator<Lesson>() {
        @Override
        public Lesson createFromParcel(Parcel in) {
            return new Lesson(in);
        }

        @Override
        public Lesson[] newArray(int size) {
            return new Lesson[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
    }
}
