package com.example.myapplication.animation;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAnimator extends DefaultItemAnimator {

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        dispatchAddFinished(holder); // this is what bypasses the animation
        return true;
    }

/* this is the default implementation of animateAdd() in DefaultItemAnimator
@Override
    public boolean animateAdd(final RecyclerView.ViewHolder holder) {
        resetAnimation(holder);
        holder.itemView.setAlpha(0); // this is what caused the flashing/blinking
        mPendingAdditions.add(holder);
        return true;
    }
*/
}
