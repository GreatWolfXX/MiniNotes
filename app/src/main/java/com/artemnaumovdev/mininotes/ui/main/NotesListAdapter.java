package com.artemnaumovdev.mininotes.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.artemnaumovdev.mininotes.R;
import com.artemnaumovdev.mininotes.data.dto.NoteDto;

import java.util.ArrayList;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesListViewHolder> {

    private final ArrayList<NoteDto> list;
    private final OnClickListeners onClickListeners;

    public NotesListAdapter(ArrayList<NoteDto> list, OnClickListeners onClickListeners) {
        this.list = list;
        this.onClickListeners = onClickListeners;
    }

    @NonNull
    @Override
    public NotesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.v_notes_card, parent, false);
        return new NotesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesListViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.description.setText(list.get(position).getDescription());
        holder.deleteBtn.setOnClickListener(listener -> {
            onClickListeners.onDeleteClicked(list.get(position));
        });
        holder.containerNote.setOnClickListener(listener -> {
            onClickListeners.onEditClicked(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    public static class NotesListViewHolder extends RecyclerView.ViewHolder {
        public NotesListViewHolder(@NonNull View view) {
            super(view);
        }

        CardView containerNote = itemView.findViewById(R.id.container_note);
        TextView title = itemView.findViewById(R.id.title_note);
        TextView description = itemView.findViewById(R.id.description_note);
        ImageView deleteBtn = itemView.findViewById(R.id.btn_delete);
    }

    void setItems(ArrayList<NoteDto> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public interface OnClickListeners {
        void onDeleteClicked(NoteDto noteDto);
        void onEditClicked(NoteDto noteDto);
    }
}
