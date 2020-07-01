package com.example.mochon;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemViewHolder> implements ItemMoveCallback.ItemTouchHelperAdapter{
    ArrayList<MainItem> list;

    public MainAdapter(ArrayList<MainItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        final MainItem mainItem = list.get(position);
        final int pos = position;

        holder.item_title.setText(list.get(position).getTitle());
        holder.item_des.setText(list.get(position).getDes());

        holder.item_check.setOnCheckedChangeListener(null);
        holder.item_check.setChecked(mainItem.isSelected());
        holder.item_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mainItem.setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView item_title, item_des;
        public CheckBox item_check;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            item_title = itemView.findViewById(R.id.item_title);
            item_des = itemView.findViewById(R.id.item_des);
            item_check = itemView.findViewById(R.id.item_check);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(mListener != null){
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onItemMove(int fromPos, int targetPos) {
        if(fromPos < targetPos){
            for(int i = fromPos; i < targetPos; i++){
                Collections.swap(list, i, i+1);
            }
        }else{
            for(int i = fromPos; i > targetPos; i--){
                Collections.swap(list, i, i-1);
            }
        }
        notifyItemMoved(fromPos, targetPos);
        Log.d("ADAPTER_PRINT", list.get(0).getTitle());
    }

    @Override
    public void onItemDismiss(int pos) {
        list.remove(pos);
        notifyItemRemoved(pos);
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }


}
