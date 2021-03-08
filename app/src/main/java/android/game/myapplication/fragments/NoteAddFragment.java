package android.game.myapplication.fragments;

import android.game.myapplication.Note;
import android.game.myapplication.firebase.note.NoteFirestoreCallbacks;
import android.game.myapplication.firebase.note.NoteRepository;
import android.game.myapplication.firebase.note.NoteRepositoryImpl;
import android.game.myapplication.firebase.notes.NotesFirestoreCallbacks;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.game.myapplication.R;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;


public class NoteAddFragment extends Fragment implements NoteFirestoreCallbacks {

    public static final String ARG_FRAMELAYOUT = "arg_frameLayout";
    private final NoteRepository repository = new NoteRepositoryImpl(this);


    private EditText editTextDescription;
    private EditText editTextName;
    private EditText editTextDate;
    private Button buttonAdd;
    private String title;
    private String description;


    public static NoteAddFragment newInstance() {
        NoteAddFragment fragment = new NoteAddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note_add, container, false);
        //      initEditTextsAndButton(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout fragmentContainer = (FrameLayout) getArguments().getSerializable(ARG_FRAMELAYOUT);
        initEditTextsAndButton(view, fragmentContainer);
    }

    private void initEditTextsAndButton(View view, FrameLayout fragmentContainer) {
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextDate = view.findViewById(R.id.editTextDate);
        editTextName = view.findViewById(R.id.editTextName);
        buttonAdd = view.findViewById(R.id.BtnAdd);
        setAddingButtonOnClick();

    }

    private void setAddingButtonOnClick() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = editTextName.getText().toString();
                description = editTextDescription.getText().toString();
                addNoteToRepository(title, description);
                openNotesListFragment();
            }
        });
    }

    private void addNoteToRepository(
            @Nullable String title,
            @Nullable String description) {
        String id = UUID.randomUUID().toString();
        repository.setNote(id, title, description);
    }


    private void openNotesListFragment() {
        NotesListFragment notesListFragment = NotesListFragment.newInstance();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, notesListFragment)
                .commit();
    }


    @Override
    public void onSuccess(@Nullable String message) {

    }

    @Override
    public void onError(@Nullable String message) {

    }
}