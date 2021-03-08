package android.game.myapplication.firebase.note;

import androidx.annotation.NonNull;

public interface NoteRepository {

    void setNote(
            @NonNull String id,
            @NonNull String title,
            @NonNull String description
    );

    void onDeleteClicked(@NonNull String id);
}
