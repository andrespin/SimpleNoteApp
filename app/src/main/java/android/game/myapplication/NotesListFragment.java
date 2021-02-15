package android.game.myapplication;

import android.content.res.Configuration;
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


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public NotesListFragment() {
        // Required empty public constructor
    }


    public static NotesListFragment newInstance(String param1, String param2) {
        NotesListFragment fragment = new NotesListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
//            String str = String.format(Locale.getDefault(), "%d , %d, %d",
//                    note.getName(), note.getDescription(), note.getDate());
            String str = note.getName();
            TextView textView = new TextView(linearLayout.getContext());
            textView.setText(str);
            textView.setPadding(padding, 0, padding, 0);
            textView.setTextSize(30f);
            linearLayout.addView(textView);

        }
    }

    private void createNotes() {
        noteList.add(new Note("Name1", "Desc1", "15.02.2021"));
        noteList.add(new Note("Name2", "Desc2", "13.02.2021"));
        noteList.add(new Note("Name3", "Desc3", "11.02.2021"));
    }

    private void checkOrientation(int index) {
        if (isLandscapeOrientation) {
            openEmblemFragment(index);
        } else {
            startEmblemActivity(index);
        }
    }

    private void openEmblemFragment(int index) {

    }

    private void startEmblemActivity(int index) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }
}