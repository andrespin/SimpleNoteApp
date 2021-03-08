package android.game.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.game.myapplication.Note;
import android.game.myapplication.R;
import android.game.myapplication.fragments.NoteEditFragment;
import android.os.Bundle;

public class NoteEditActivity extends AppCompatActivity {

    private boolean isLandScapeOrientation;
    public static final String ARG_INDEX = "arg_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        initProcess(savedInstanceState);
    }

    private void initProcess(Bundle savedInstanceState) {
        isLandScapeOrientation = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandScapeOrientation) {
            finish();
        } else if (savedInstanceState == null) {
            Intent intent = getIntent();
            Note note = (Note) intent.getSerializableExtra(ARG_INDEX);
            NoteEditFragment fragment = NoteEditFragment.newInstance(note);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_container2, fragment);
            fragmentTransaction.commit();
        }
    }
}