package com.example.adilbekramazanov.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.RealmBasedRecyclerViewAdapter;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> notes = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> notes, Context mContext) {
        this.notes = notes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listiem, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.note.setText(notes.get(i));

        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( TAG, "onClick: clicked on: " + notes.get(i));

                Toast.makeText(mContext, notes.get(i),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, GalleryActivity.class);
                intent.putExtra("note", notes.get(i));
                mContext.startActivity(intent);

            }
        });

    }
    public void deleteItem(int position) {

        String mRecentlyDeletedItem = notes.get(position);
        int mRecentlyDeletedItemPosition = position;
        notes.remove(position);
        notifyItemRemoved(position);

    }


    @Override
    public int getItemCount() {

        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView note;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note = itemView.findViewById(R.id.todo_text_view);
            relativeLayout = itemView.findViewById(R.id.itemLayout);
        }
    }

}
