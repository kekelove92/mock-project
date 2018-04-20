package group6.fga.fsoft.com.mockproject_group6.view;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import group6.fga.fsoft.com.mockproject_group6.GridViewAdapter;
import group6.fga.fsoft.com.mockproject_group6.R;
import group6.fga.fsoft.com.mockproject_group6.controller.Controller;
import group6.fga.fsoft.com.mockproject_group6.controller.DropState;
import group6.fga.fsoft.com.mockproject_group6.model.Model;
import group6.fga.fsoft.com.mockproject_group6.model.entity.Lesson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mLinearLayoutMain;
    private ImageButton mButtonPrevious;
    private ImageButton mButtonNext;
    private Button mButtonEditLessonName;
    private Button mButtonAddLessonName;
    private Button mButtonOk;
    private Button mButtonCancel;
    private ImageView mRecycleBin;
    private GridView mGridViewTimetable;
    private GridView mGridViewLessons;
    private GridViewAdapter mTimetableAdapter;
    private GridViewAdapter mLessonAdapter;
    private List<Object> mListTimetable;
    private List<Object> mListLesson;

    private Model mModel;
    private Controller mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        registerViewListener();
        initModel();
        initController();

        requestLoadData();
    }

    private void registerViewListener() {
        mButtonOk.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);
        mButtonAddLessonName.setOnClickListener(this);
        mButtonEditLessonName.setOnClickListener(this);
        mButtonPrevious.setOnClickListener(this);
        mButtonNext.setOnClickListener(this);

        mRecycleBin.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.animate().scaleX(1.5f).scaleY(1.5f).start();

                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.animate().scaleX(1).scaleY(1).start();
                        break;

                    case DragEvent.ACTION_DROP:
                        v.animate().scaleX(1).scaleY(1).start();

                        ClipData clipData = event.getClipData();
                        String text = clipData.getItemAt(0).getText().toString();
                        String[] arrayText = text.split(" ");
                        int startPosition = Integer.parseInt(arrayText[0]);
                        int fromTable = Integer.parseInt(arrayText[1]);

                        Log.e("startPosition", String.valueOf(startPosition));
                        Log.e("fromTable", fromTable == 2 ? "GRID_LESSON" : "GRID_TIMETABLE");

                        Message msg = new Message();
                        msg.what = Controller.DROP_STATE;
                        msg.arg1 = DropState.DELETE_LESSON;

                        mController.sendMessage(msg);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:

                        break;
                }
                return true;
            }
        });

        mTimetableAdapter.setOnCellDroppedListener(new GridViewAdapter.OnCellDroppedListener() {
            @Override
            public void onCellDrop(int startPosition, int fromTable, int dropPosition, int toTable) {

                Lesson dropLesson = (Lesson) mListTimetable.get(dropPosition);

                if (fromTable == GridViewAdapter.GRID_LESSON && toTable == GridViewAdapter.GRID_TIMETABLE) {

                    if (!dropLesson.getmName().equals("")) {
                        Message msg = new Message();
                        msg.what = Controller.DROP_STATE;
                        msg.arg1 = DropState.REPLACE_LESSON;
                        msg.arg2 = DropState.FROM_LESSONS_TO_TIMETABLE;
                        mController.sendMessage(msg);

                    } else if (dropLesson.getmName().equals("")) {
                        Message msg = new Message();
                        msg.what = Controller.DROP_STATE;
                        msg.arg1 = DropState.ADD_LESSON_TO_TIMETABLE;
                        mController.sendMessage(msg);
                    }
                } else if (fromTable == GridViewAdapter.GRID_TIMETABLE && toTable == GridViewAdapter.GRID_TIMETABLE) {
                    if (!dropLesson.getmName().equals("")) {
                        Message msg = new Message();
                        msg.what = Controller.DROP_STATE;
                        msg.arg1 = DropState.REPLACE_LESSON;
                        msg.arg2 = DropState.FROM_TIMETABLE_TO_TIMETABLE;
                        mController.sendMessage(msg);
                    }
                }
            }
        });
    }

    private void initView() {
        mLinearLayoutMain = findViewById(R.id.linear_layout_main);
        mButtonPrevious = findViewById(R.id.button_previous);
        mButtonNext = findViewById(R.id.button_next);
        mButtonEditLessonName = findViewById(R.id.button_edit);
        mButtonAddLessonName = findViewById(R.id.button_add);
        mButtonOk = findViewById(R.id.button_ok);
        mButtonCancel = findViewById(R.id.button_cancel);
        mRecycleBin = findViewById(R.id.r_bin);
        mGridViewTimetable = findViewById(R.id.grid_view_timetable);
        mGridViewLessons = findViewById(R.id.grid_view_lessons);

        //******************Fake Data

        mListLesson = new ArrayList<>();
        mListTimetable = new ArrayList<>();

        mListTimetable.add("");
        mListTimetable.add("Monday");
        mListTimetable.add("Tuesday");
        mListTimetable.add("Wednesday");
        mListTimetable.add("Thursday");
        mListTimetable.add("Friday");
        mListTimetable.add("Saturday");

        for (int i = 1; i <= 6; i++) {
            mListTimetable.add("Lesson " + i);
            for (int j = 1; j <= 6; j++) {
                mListTimetable.add(new Lesson("Literature"));
            }
        }
        mListTimetable.set(15, new Lesson(""));
        mListTimetable.set(22, new Lesson(""));

        mTimetableAdapter = new GridViewAdapter(this, mListTimetable, GridViewAdapter.GRID_TIMETABLE);
        mLessonAdapter = new GridViewAdapter(this, mListLesson, GridViewAdapter.GRID_LESSON);

        mGridViewTimetable.setAdapter(mTimetableAdapter);
        mGridViewLessons.setAdapter(mLessonAdapter);
    }

    public void requestLoadData() {
        Message msg = new Message();
        msg.what = Controller.LOAD_DATA_STATE;
        mController.sendMessage(msg);
    }

    private void initModel() {
        mModel = new Model();
        mModel.setPropertyChangeSupportListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent event) {
                onUpdateModel(event);
            }
        });
    }

    private void onUpdateModel(PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case Model.UPDATE_TIMETABLE:
                mListTimetable.clear();
                mListTimetable.addAll((Collection<?>) event.getNewValue());
                mTimetableAdapter.notifyDataSetChanged();
                break;

            case Model.UPDATE_LESSON_LIST:
                mListLesson.clear();
                mListLesson.addAll((Collection<?>) event.getNewValue());
                mLessonAdapter.notifyDataSetChanged();
                break;

            case Model.UPDATE_DIM_VIEW:  boolean isEditing = (boolean) event.getNewValue();
                if (isEditing) {

                    mRecycleBin.setEnabled(false);
                    mRecycleBin.setAlpha(0.4f);
                    mGridViewTimetable.setAlpha(0.8f);
                    mButtonOk.setEnabled(false);
                    mButtonCancel.setEnabled(false);
                    mButtonNext.setEnabled(false);
                    mButtonNext.setAlpha(0.4f);
                    mButtonPrevious.setEnabled(false);
                    mButtonPrevious.setAlpha(0.4f);
                    mButtonAddLessonName.setEnabled(false);
                    mButtonEditLessonName.setText(getResources().getString(R.string.cancel_editing));
                    mLessonAdapter.setDraggable(false);
                    mTimetableAdapter.setDraggable(false);
                } else {
                    mRecycleBin.setEnabled(true);
                    mRecycleBin.setAlpha(1f);
                    mGridViewTimetable.setAlpha(1f);
                    mButtonOk.setEnabled(true);
                    mButtonCancel.setEnabled(true);
                    mButtonNext.setEnabled(true);
                    mButtonNext.setAlpha(1f);
                    mButtonPrevious.setEnabled(true);
                    mButtonPrevious.setAlpha(1f);
                    mButtonAddLessonName.setEnabled(true);
                    mButtonEditLessonName.setText(getResources().getString(R.string.edit_lesson_name));
                    mLessonAdapter.setDraggable(true);
                    mTimetableAdapter.setDraggable(true);
                }
                break;
            default:
                break;
        }
    }


    private void initController() {
        mController = new Controller(this);
    }

    @Override
    public void onClick(View view) {
        Message msg;
        switch (view.getId()) {

            case R.id.button_add:
                msg = new Message();
                msg.what = Controller.ADD_LESSON_NAME_TO_LIST_STATE;
                mController.sendMessage(msg);

                break;
            case R.id.button_edit:
                msg = new Message();
                msg.what = Controller.DIM_VIEW_STATE;
                mController.sendMessage(msg);

                break;
            case R.id.button_next:
                msg = new Message();
                msg.what = Controller.LOAD_DATA_STATE;
                // set msg.arg1 : nextWeek
                mController.sendMessage(msg);

                break;

            case R.id.button_previous:
                msg = new Message();
                msg.what = Controller.LOAD_DATA_STATE;
                // set msg.arg1 : previousWeek
                mController.sendMessage(msg);

                break;

            case R.id.button_ok:
                msg = new Message();
                msg.what = Controller.SAVE_DATA_STATE;
                mController.sendMessage(msg);

                break;
            case R.id.button_cancel:
                msg = new Message();
                msg.what = Controller.LOAD_DATA_STATE;
                // set msg.arg1 : currentWorkingWeek
                mController.sendMessage(msg);

                break;

        }
    }

    public Model getmModel() {
        return mModel;
    }
}
