package android.game.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.game.myapplication.fragments.NoteAddFragment;
import android.game.myapplication.fragments.NotesListFragment;
import android.game.myapplication.R;
import android.game.myapplication.observer.Publisher;
import android.game.myapplication.observer.PublisherGetter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements PublisherGetter {


    Publisher publisher = new Publisher();
    private static boolean isEditFunctionTurned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initNotesListFragment(savedInstanceState);
    }

    private void initNotesListFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            NotesListFragment fragment = NotesListFragment.newInstance();
            publisher.subscribe(fragment);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }

    private void addNoteAddFragment() {
        NoteAddFragment fragment = NoteAddFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }


    private void initView() {
        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
    }


    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (navigateFragment(id)) {
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (navigateFragment(id)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Boolean switchFunction() {
        if (isEditFunctionTurned) {
            isEditFunctionTurned = false;
        } else {
            isEditFunctionTurned = true;
        }
        return isEditFunctionTurned;
    }


    private boolean navigateFragment(int id) {
        switch (id) {
            case R.id.action_add:
                addNoteAddFragment();
                return true;
            case R.id.action_edit:
                Toast.makeText(this, "action_edit", Toast.LENGTH_SHORT).show();
                isEditFunctionTurned = switchFunction();
                publisher.notify(isEditFunctionTurned);
                return true;
            case R.id.action_search:
                Toast.makeText(this, "action_search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_delete:
                Toast.makeText(this, "action_delete", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "action_settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_aboutapp:
                Toast.makeText(this, "action_aboutapp", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
//        CheckBox checkBox = (CheckBox) menu.findItem(R.id.action_edit).getActionView();
//        checkBox.setChecked(true);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchText = (SearchView) search.getActionView();
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
    }


    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }
}