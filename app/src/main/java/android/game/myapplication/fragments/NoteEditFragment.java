package android.game.myapplication.fragments;

import android.game.myapplication.Note;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.game.myapplication.R;
import android.widget.EditText;

import java.util.Locale;

public class NoteEditFragment extends Fragment {


    public static final String ARG_INDEX = "arg_index";


    public NoteEditFragment() {

    }

    public static NoteEditFragment newInstance(Note note) {
        NoteEditFragment fragment = new NoteEditFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_INDEX, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            Note note = (Note) getArguments().getSerializable(ARG_INDEX);
            EditText editTextName = view.findViewById(R.id.editName);
            EditText editTextDesc = view.findViewById(R.id.editDesc);
            EditText editTextDate = view.findViewById(R.id.editDate);
            editTextName.setText(note.getName());
            editTextDesc.setText(note.getDescription());
            editTextDate.setText(note.getDate());
        }
    }

    private void initButtonApply(View view) {
        EditText editTextName = view.findViewById(R.id.editName);
        EditText editTextDesc = view.findViewById(R.id.editDesc);
        EditText editTextDate = view.findViewById(R.id.editDate);
        view.findViewById(R.id.buttonApply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName = editTextName.getText().toString();
                String strDesc = editTextDesc.getText().toString();
                String strDate = editTextDesc.getText().toString();
            }
        });
    }

}