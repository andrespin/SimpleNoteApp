package android.game.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.game.myapplication.Note;
import android.game.myapplication.fragments.NoteFragment;
import android.game.myapplication.R;
import android.os.Bundle;

public class NoteActivity extends AppCompatActivity {

    private boolean isLandScapeOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initProcess(savedInstanceState);

    }

    private void initProcess(Bundle savedInstanceState) {
        isLandScapeOrientation = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandScapeOrientation) {
            finish();
        } else if (savedInstanceState == null) {
            Intent intent = getIntent();
            Note note = (Note) intent.getSerializableExtra(NoteFragment.ARG_INDEX);
            NoteFragment fragment = NoteFragment.newInstance(note);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_container1, fragment);
            fragmentTransaction.commit();
        }
    }
}