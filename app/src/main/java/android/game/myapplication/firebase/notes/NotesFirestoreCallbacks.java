package android.game.myapplication.firebase.notes;

import android.game.myapplication.Note;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public interface NotesFirestoreCallbacks {

    void onSuccessNotes(@NonNull List<Note> items);
    void onErrorNotes(@Nullable String message);
}
