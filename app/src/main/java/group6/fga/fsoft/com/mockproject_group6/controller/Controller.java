package group6.fga.fsoft.com.mockproject_group6.controller;

import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;

import group6.fga.fsoft.com.mockproject_group6.database.DBManager;
import group6.fga.fsoft.com.mockproject_group6.database.LessonDatabase;
import group6.fga.fsoft.com.mockproject_group6.view.MainActivity;
import group6.fga.fsoft.com.mockproject_group6.database.TimeTableDatabase;

public class Controller {
    public static final String TAG = Controller.class.getName();
    public static final int DROP_STATE = 1;
    public static final int LOAD_DATA_STATE = 2;
    public static final int SAVE_DATA_STATE = 3;
    public static final int UPDATE_LESSON_STATE = 4;
    public static final int EDIT_LESSON_NAME_STATE = 5;
    public static final int ADD_LESSON_NAME_TO_LIST_STATE = 6;
    public static final int DIM_VIEW_STATE = 7;

    private MsgHandler mMsgHandler;
    private MainActivity mMainActivity;
    private DBManager mDbManager;
    private TimeTableDatabase mTimeTableDatabase;
    private LessonDatabase mLessonDatabase;

    private SparseArray<BaseState> mStates;
    private BaseState currentState;

    public Controller(MainActivity mMainActivity) {
        this.mMainActivity = mMainActivity;
        mMsgHandler = new MsgHandler(this);
        mDbManager = new DBManager(mMainActivity);
        mStates = initState();
        mLessonDatabase = new LessonDatabase(mMainActivity, mDbManager);
        mTimeTableDatabase = new TimeTableDatabase(mMainActivity, mDbManager);
    }

    public void sendMessage(Message msg) {

        mMsgHandler.sendMessage(msg);
    }

    public TimeTableDatabase getmTimeTableDatabase() {
        return mTimeTableDatabase;
    }

    public LessonDatabase getmLessonDatabase() {
        return mLessonDatabase;
    }

    private void handleMsg(Message msg) {

        switch (msg.what) {
            case DROP_STATE:
                currentState = mStates.get(DROP_STATE);

                break;
            case LOAD_DATA_STATE:
                currentState = mStates.get(LOAD_DATA_STATE);

                break;
            case SAVE_DATA_STATE:
                currentState = mStates.get(SAVE_DATA_STATE);

                break;
            case UPDATE_LESSON_STATE:
                currentState = mStates.get(UPDATE_LESSON_STATE);

                break;
            case EDIT_LESSON_NAME_STATE:
                currentState = mStates.get(EDIT_LESSON_NAME_STATE);

                break;
            case ADD_LESSON_NAME_TO_LIST_STATE:
                currentState = mStates.get(ADD_LESSON_NAME_TO_LIST_STATE);

                break;
            case DIM_VIEW_STATE:
                currentState = mStates.get(DIM_VIEW_STATE);

                break;
        }

        currentState.handleMsg(msg);

    }

    private SparseArray<BaseState> initState() {
        SparseArray<BaseState> states = new SparseArray<>();
        states.put(DROP_STATE, new DropState(this));
        states.put(LOAD_DATA_STATE, new LoadDataState(this));
        states.put(SAVE_DATA_STATE, new SaveDataState(this));
        states.put(UPDATE_LESSON_STATE, new UpdateLessonState(this));
        states.put(EDIT_LESSON_NAME_STATE, new EditLessonNameState(this));
        states.put(ADD_LESSON_NAME_TO_LIST_STATE, new AddLessonNameToListState(this));
        states.put(DIM_VIEW_STATE, new DimViewState(this));

        return states;
    }

    public MainActivity getMainActivity() {
        return mMainActivity;
    }

    private static class MsgHandler extends Handler {

        private Controller mController;

        public MsgHandler(Controller mController) {
            super();
            this.mController = mController;
        }

        @Override
        public void handleMessage(Message msg) {
            mController.handleMsg(msg);
        }
    }


}
