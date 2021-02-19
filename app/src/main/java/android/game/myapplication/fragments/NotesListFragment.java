package android.game.myapplication.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.game.myapplication.Note;
import android.game.myapplication.R;
import android.game.myapplication.activities.NoteActivity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class NotesListFragment extends Fragment {

    private List<Note> noteList = new ArrayList<>();
    private boolean isLandscapeOrientation;


    public NotesListFragment() {

    }

    public static NotesListFragment newInstance() {
        NotesListFragment fragment = new NotesListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLandscapeOrientation =
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createNotes();
        initView(view);
    }


    private void initView(View view) {
        int padding = getResources().getDimensionPixelSize(R.dimen.default_margin);
        LinearLayout linearLayout = (LinearLayout) view;
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            String str = String.format(Locale.getDefault(), "%s\n%s\n%s",
                    note.getName(), note.getDescription(), note.getDate());
            TextView textView = new TextView(linearLayout.getContext());
            textView.setText(str);
            textView.setPadding(padding, 0, padding, 0);
            textView.setTextSize(30f);
            int index = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //       checkOrientation(index);
                    checkOrientation(note);
                }
            });
            linearLayout.addView(textView);
        }
    }

    private void createNotes() {
        noteList.add(new Note("Name1", "Desc1", "15.02.2021"));
        noteList.add(new Note("Name2", "Desc2", "13.02.2021"));
        noteList.add(new Note("Name3", "Desc3", "11.02.2021"));
    }

    private void checkOrientation(Note note) {
        if (isLandscapeOrientation) {
            openNoteFragment(note);
        } else {
            startNoteActivity(note);
        }
    }

    private void openNoteFragment(Note note) {
        NoteFragment noteFragment = NoteFragment.newInstance(note);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container, noteFragment)
                .commit();
    }

    private void startNoteActivity(Note note) {
        Intent intent = new Intent(getActivity(), NoteActivity.class);
        intent.putExtra(NoteFragment.ARG_INDEX, note);
        startActivity(intent);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }
}