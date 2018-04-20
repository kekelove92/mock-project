package group6.fga.fsoft.com.mockproject_group6;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import group6.fga.fsoft.com.mockproject_group6.model.entity.Lesson;
import group6.fga.fsoft.com.mockproject_group6.view.MainActivity;

/**
 * Created by Tu on 18-Apr-18.
 */

public class GridViewAdapter extends BaseAdapter {

    public static final int LESSON_ITEM = 0;
    public static final int TITLE_ITEM = 1;
    public static final int GRID_LESSON = 2;
    public static final int GRID_TIMETABLE = 3;
    private boolean mDraggable = true;

    private List<Object> mList;
    private LayoutInflater mInflater;
    private int mGridType;
    private MainActivity mMainActivity;

    private OnCellDroppedListener mListener;

    public interface OnCellDroppedListener {
        void onCellDrop(int startPosition, int fromTable, int dropPosition, int toTable);
    }

    public void setDraggable(boolean isDraggable) {
        mDraggable = isDraggable;
    }

    public void setOnCellDroppedListener(OnCellDroppedListener listener) {
        mListener = listener;
    }

    public GridViewAdapter(Context context, List<Object> mList, int mGridType) {
        this.mList = mList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mGridType = mGridType;
        mMainActivity = (MainActivity) context;
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof Lesson) {
            return LESSON_ITEM;
        } else return TITLE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.grid_view_item, null);
            holder = new ViewHolder();
            holder.textView = view.findViewById(R.id.text_view_cell);
            holder.linearLayout = view.findViewById(R.id.linear_layout_cell);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        switch (getItemViewType(i)) {
            case LESSON_ITEM:

                Lesson lesson = (Lesson) mList.get(i);
                holder.textView.setText(lesson.getmName());
                if (!lesson.getmName().equals("")) {


                    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (mMainActivity.getmModel().ismEditingLesson()) {
                                // open activity edit lesson name
                                Log.e("edit lesson name", String.valueOf(i));
                            }
                        }
                    });


                    holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {

                        @Override
                        public boolean onLongClick(View view) {

                            if (!mDraggable) {
                                return false;
                            } else {

//                        Log.e("startPosition", String.valueOf(i));
//                        Log.e("fromTable", mGridType == 2 ? "GRID_LESSON" : "GRID_TIMETABLE");

                                String text = i + " " + mGridType;
                                ClipData data = ClipData.newPlainText("text", text);

                                // Construct draggable shadow for view
                                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                                // Start the drag of the shadow
                                view.startDrag(data, shadowBuilder, view, 0);
                                // Hide the actual view as shadow is being dragged
                                ((LinearLayout) view).getChildAt(0).setVisibility(View.INVISIBLE);

                                return true;
                            }
                        }
                    });
                }

                holder.linearLayout.setOnDragListener(new View.OnDragListener() {
//                    // Drawable for when the draggable enters the drop target
//                    Drawable enteredZoneBackground = getResources().getDrawable(R.drawable.shape_border_green);
//                    // Drawable for the default background of the drop target
//                    Drawable defaultBackground = getResources().getDrawable(R.drawable.shape_border_red);

                    @Override
                    public boolean onDrag(View v, DragEvent event) {
                        // Get the dragged view being dropped over a target view
                        final View draggedView = (View) event.getLocalState();

                        switch (event.getAction()) {
                            case DragEvent.ACTION_DRAG_STARTED:
                                // Signals the start of a drag and drop operation.
                                // Code for that event here
                                break;
                            case DragEvent.ACTION_DRAG_ENTERED:
//                                // Signals to a View that the drag point has
//                                // entered the bounding box of the View.
//                                v.setBackground(enteredZoneBackground);
                                break;
                            case DragEvent.ACTION_DRAG_EXITED:
//                                // Signals that the user has moved the drag shadow
//                                // outside the bounding box of the View.
//                                v.setBackground(defaultBackground);
                                break;
                            case DragEvent.ACTION_DROP:

                                if (mGridType == GRID_LESSON) {
                                    Log.e("dropPosition", String.valueOf(i));
                                    Log.e("toTable", "GRID_LESSON");
                                } else if (mGridType == GRID_TIMETABLE) {
                                    Log.e("dropPosition", String.valueOf(i));
                                    Log.e("toTable", "GRID_TIMETABLE");
                                }

                                ClipData clipData = event.getClipData();
                                String text = clipData.getItemAt(0).getText().toString();
                                String[] arrayText = text.split(" ");
                                int startPosition = Integer.parseInt(arrayText[0]);
                                int fromTable = Integer.parseInt(arrayText[1]);

                                Log.e("startPosition", String.valueOf(startPosition));
                                Log.e("fromTable", fromTable == 2 ? "GRID_LESSON" : "GRID_TIMETABLE");

                                if (mListener != null) {
                                    mListener.onCellDrop(startPosition, fromTable, i, mGridType);
                                }

                                break;
                            case DragEvent.ACTION_DRAG_ENDED:
                                // Signals to a View that the drag and drop operation has concluded.
                                // If event result is set, this means the dragged view was dropped in target
                                if (event.getResult()) { // drop succeeded
//                                    v.setBackground(enteredZoneBackground);

                                } else { // drop did not occur
                                    // restore the view as visible

                                    // restore drop zone default background
//                                    v.setBackground(defaultBackground);
                                }

                                // Tam thoi hien lai view drag
                                ((LinearLayout) draggedView).getChildAt(0).
                                        setVisibility(View.VISIBLE);
                            default:
                                break;
                        }
                        return true;
                    }
                });

                break;
            case TITLE_ITEM:
                holder.textView.setText((String) mList.get(i));
                break;
        }

        return view;
    }

    static class ViewHolder {
        TextView textView;
        LinearLayout linearLayout;
    }
}
