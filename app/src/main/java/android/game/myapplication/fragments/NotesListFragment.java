package android.game.myapplication.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.game.myapplication.Note;
import android.game.myapplication.NotesAdapter;
import android.game.myapplication.NotesAdapterCallback;
import android.game.myapplication.NotesSpaceDecorator;
import android.game.myapplication.R;
import android.game.myapplication.activities.NoteActivity;
import android.game.myapplication.activities.NoteEditActivity;
import android.game.myapplication.observer.Observer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;


public class NotesListFragment extends Fragment implements NotesAdapterCallback, Observer {

    private List<Note> noteList = new ArrayList<>();
    private boolean isLandscapeOrientation;

    private boolean isEditFunctionTurned = false;

    private final NotesAdapter notesAdapter = new NotesAdapter(this);

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
        createNotes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notesAdapter.setItems(noteList);
    }


    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), RecyclerView.VERTICAL));
        recyclerView.addItemDecoration(new NotesSpaceDecorator(getResources().getDimensionPixelSize(R.dimen.default_margin)));
        recyclerView.setAdapter(notesAdapter);
    }

    @Override
    public void onOnItemClicked(int position) {
        if (isEditFunctionTurned) {
            startEditActivity(noteList.get(position));
        } else {
            checkOrientation(noteList.get(position));
        }
    }

    private void createNotes() {
        noteList.add(new Note("Name1", "Desc1", "15.02.2021"));
        noteList.add(new Note("Name2", "Desc2", "13.02.2021"));
        noteList.add(new Note("Name3", "Desc3", "11.02.2021"));
        noteList.add(new Note("Name4", "Desc4", "15.02.2021"));
        noteList.add(new Note("Name5", "Desc5", "17.02.2021"));
        noteList.add(new Note("Name6", "Desc6", "18.02.2021"));
    }


    private void startEditActivity(Note note) {
        startNoteEditActivity(note);
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

    private void startNoteEditActivity(Note note) {
        Intent intent = new Intent(getActivity(), NoteEditActivity.class);
        intent.putExtra(NoteFragment.ARG_INDEX, note);
        startActivity(intent);
    }

    private void startNoteActivity(Note note) {
        Intent intent = new Intent(getActivity(), NoteActivity.class);
        intent.putExtra(NoteFragment.ARG_INDEX, note);
        startActivity(intent);

    }


    @Override
    public void updateBoolean(Boolean isEditFunctionTurned) {
        this.isEditFunctionTurned = isEditFunctionTurned;
        if (this.isEditFunctionTurned) {
            System.out.println("True");
        }
    }
}