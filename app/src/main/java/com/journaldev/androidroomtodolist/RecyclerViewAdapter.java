package com.journaldev.androidroomtodolist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Todo> todoList;
    private RecyclerViewAdapter.ClickListener clickListener;

    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        todoList = new ArrayList<>();
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.txtName.setText(todo.name);
        //holder.txtDesc.setText("Note: ");
        holder.txtCategory.setText(todo.category);

        switch (todo.category){
            case "Facebook":
                holder.accountLogo.setImageResource(R.drawable.fb);
                break;
            case "Google":
                holder.accountLogo.setImageResource(R.drawable.google);
                break;
            case "Yahoo":
                holder.accountLogo.setImageResource(R.drawable.yahoo);
                break;
            case "STI ELMS":
                holder.accountLogo.setImageResource(R.drawable.sti);
                break;
            default:
                holder.accountLogo.setImageResource(R.drawable.error);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }


    public void updateTodoList(List<Todo> data) {
        todoList.clear();
        todoList.addAll(data);
        notifyDataSetChanged();
    }

    public void addRow(Todo data) {
        todoList.add(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;
        public ImageView accountLogo;
        public TextView txtDesc;
        public TextView txtCategory;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);

            accountLogo = view.findViewById(R.id.accountLogo);
            txtName = view.findViewById(R.id.txtName);
            //txtDesc = view.findViewById(R.id.txtDesc);
            txtCategory = view.findViewById(R.id.txtCategory);
            cardView = view.findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(todoList.get(getAdapterPosition()).todo_id);
                }
            });
        }
    }

    public interface ClickListener {
        void launchIntent(int id);
    }
}
