package android.game.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private static final String TAG = "NoteAdapter";
    private final List<Note> notes = new ArrayList<>();
    private final Fragment fragment;

    private final NotesAdapterCallback callback;

    public NotesAdapter(NotesAdapterCallback callback, Fragment fragment) {
        this.callback = callback;
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.onBind(notes.get(position), position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setItems(List<Note> items) {
        notes.clear();
        notes.addAll(items);
        notifyDataSetChanged();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private final MaterialTextView textName;
        private final MaterialTextView textDescription;
        private final MaterialTextView textDate;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.item_name);
            textDescription = itemView.findViewById(R.id.item_description);
            textDate = itemView.findViewById(R.id.item_date);
            registerContextMenu(itemView);
        }

        private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null){
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        itemView.showContextMenu(10, 10);
                        return false;
                    }
                });
                fragment.registerForContextMenu(itemView);
            }
        }

        public void onBind(Note model, int position) {
            Log.d(TAG, String.valueOf(position));
            textName.setText(model.getName());
            textDescription.setText(model.getDescription());
            textDate.setText(model.getDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        callback.onOnItemClicked(getAdapterPosition());
                    }
                }
            });
        }
    }
}
