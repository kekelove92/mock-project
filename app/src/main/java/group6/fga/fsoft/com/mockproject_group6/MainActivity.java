package group6.fga.fsoft.com.mockproject_group6;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import group6.fga.fsoft.com.mockproject_group6.controller.Controller;
import group6.fga.fsoft.com.mockproject_group6.controller.DropState;
import group6.fga.fsoft.com.mockproject_group6.model.Model;
import group6.fga.fsoft.com.mockproject_group6.model.entity.Lesson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton mButtonPrevious;
    private ImageButton mButtonNext;
    private Button mButtonEditLessonName;
    private Button mButtonAddLessonName;
    private Button mButtonOk;
    private Button mButtonCancel;
    private ImageView mRecycleBin;
    private GridView mGridViewTimetable;
    private GridView mGridViewLessons;
    private GridViewAdapter mGridViewAdapter;
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

                        break;
                    case DragEvent.ACTION_DRAG_EXITED:

                        break;
                    case DragEvent.ACTION_DRAG_ENDED:

                        break;
                    case DragEvent.ACTION_DROP:

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
                }
                return true;
            }
        });

        mGridViewAdapter.setOnCellDroppedListener(new GridViewAdapter.OnCellDroppedListener() {
            @Override
            public void onCellDrop(int startPosition, int fromTable, int dropPosition, int toTable) {

                Lesson dropLesson = (Lesson) mListTimetable.get(dropPosition);

                if (fromTable == GridViewAdapter.GRID_LESSON && toTable == GridViewAdapter.GRID_TIMETABLE) {

                    if (!dropLesson.getName().equals("")) {
                        Message msg = new Message();
                        msg.what = Controller.DROP_STATE;
                        msg.arg1 = DropState.REPLACE_LESSON;
                        mController.sendMessage(msg);

                    } else if (dropLesson.getName().equals("")) {
                        Message msg = new Message();
                        msg.what = Controller.DROP_STATE;
                        msg.arg1 = DropState.ADD_LESSON_TO_TIMETABLE;
                        mController.sendMessage(msg);
                    }
                } else if (fromTable == GridViewAdapter.GRID_TIMETABLE && toTable == GridViewAdapter.GRID_TIMETABLE) {
                    if (!dropLesson.getName().equals("")) {
                        Message msg = new Message();
                        msg.what = Controller.DROP_STATE;
                        msg.arg1 = DropState.REPLACE_LESSON;
                        mController.sendMessage(msg);
                    }
                }
            }
        });

    }

    private void initView() {
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

        for (int i = 0; i < 15; i++) {
            mListLesson.add(new Lesson("English"));
        }
        mListLesson.set(3, new Lesson(""));

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

        mGridViewAdapter = new GridViewAdapter(this, mListTimetable, GridViewAdapter.GRID_TIMETABLE);
        mLessonAdapter = new GridViewAdapter(this, mListLesson, GridViewAdapter.GRID_LESSON);

        mGridViewTimetable.setAdapter(mGridViewAdapter);
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

                break;
            case Model.UPDATE_LESSON_LIST:

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
                msg.what = Controller.EDIT_LESSON_NAME_STATE;
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
}
