package com.example.mochon;


import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class ItemMoveCallback extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapter adapter;

    public ItemMoveCallback(ItemTouchHelperAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int flagDrag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int flagSwipe = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(flagDrag, flagSwipe);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder fromHolder, @NonNull RecyclerView.ViewHolder targetHolder) {
        adapter.onItemMove(fromHolder.getAdapterPosition(), targetHolder.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    public interface ItemTouchHelperAdapter{
        void onItemMove(int fromPos, int targetPos);
        void onItemDismiss(int pos);
    }
}
