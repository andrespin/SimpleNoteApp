package android.game.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class NoteFragment extends Fragment {

    public static final String ARG_INDEX = "arg_index";


    public NoteFragment() {

    }

    public static NoteFragment newInstance(Note note) {
        NoteFragment fragment = new NoteFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int padding = getResources().getDimensionPixelSize(R.dimen.default_margin);
        if (getArguments() != null) {
            FrameLayout frameLayout = (FrameLayout) view;
            Note note = (Note) getArguments().getSerializable(ARG_INDEX);
            String str = String.format(Locale.getDefault(), "Fragment with one note here\n%s\n%s\n%s",
                    note.getName(), note.getDescription(), note.getDate());
            TextView textView = new TextView(frameLayout.getContext());
            textView.setText(str);
            textView.setPadding(padding, 0, padding, 0);
            textView.setTextSize(30f);
            frameLayout.addView(textView);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}