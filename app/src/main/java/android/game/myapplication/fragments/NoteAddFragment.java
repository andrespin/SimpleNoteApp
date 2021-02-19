package android.game.myapplication.fragments;

import android.game.myapplication.CutomFrameLayout;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.game.myapplication.R;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import java.io.Serializable;


public class NoteAddFragment extends Fragment {

    public static final String ARG_FRAMELAYOUT = "arg_frameLayout";


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
        EditText editTextDescription = view.findViewById(R.id.editTextDescription);
        EditText editTextDate = view.findViewById(R.id.editTextDate);
        Button buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesListFragment notesListFragment = NotesListFragment.newInstance();
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, notesListFragment)
                        .commit();
            }
        });
    }

}