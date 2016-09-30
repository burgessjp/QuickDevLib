package me.solidev.library.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author drakeet
 */
public abstract class OnRecyclerItemClickListener extends GestureDetector.SimpleOnGestureListener
    implements RecyclerView.OnItemTouchListener {

    private View childView;
    private RecyclerView recyclerView;
    private GestureDetector gestureDetector;


    abstract void onItemClick(View view, int position);


    void onItemLongClick(View view, int position) {

    }


    public OnRecyclerItemClickListener(Context context) {
        gestureDetector = new GestureDetector(context.getApplicationContext(), this);
    }


    @Override public boolean onSingleTapUp(MotionEvent ev) {
        if (childView != null) {
            onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
        }
        return true;
    }


    @Override public void onLongPress(MotionEvent ev) {
        if (childView != null) {
            onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
        }
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        this.recyclerView = recyclerView;
        return false;
    }


    @Override public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

    }


    @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
