package com.chengjian.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyLocalRecycleViewItemDecoration extends RecyclerView.ItemDecoration {


    private int mSpacing = 0;

    public MyLocalRecycleViewItemDecoration(int spacing) {
        this.mSpacing = spacing;
    }


    /**
     * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
     * the number of pixels that the item view should be inset by, similar to padding or margin.
     * The default implementation sets the bounds of outRect to 0 and returns.
     *
     * <p>
     * If this ItemDecoration does not affect the positioning of item views, it should set
     * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
     * before returning.
     *
     * <p>
     * If you need to access Adapter for additional data, you can call
     * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
     * View.
     *
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = mSpacing;
            outRect.right = mSpacing;
            outRect.bottom = mSpacing;
            outRect.top = mSpacing;
    }
}
