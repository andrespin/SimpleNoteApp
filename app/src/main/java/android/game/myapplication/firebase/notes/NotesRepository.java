package android.game.myapplication.firebase.notes;

import androidx.annotation.NonNull;

public interface NotesRepository {

    void requestNotes();

    void onDeleteClicked(@NonNull String id);
}
