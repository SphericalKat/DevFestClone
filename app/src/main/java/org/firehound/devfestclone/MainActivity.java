package org.firehound.devfestclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import me.jfenn.attribouter.Attribouter;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomappbar.BottomAppBar;

import org.firehound.devfestclone.fragments.AgendaFragment;
import org.firehound.devfestclone.fragments.InfoFragment;
import org.firehound.devfestclone.fragments.NavigationBottomSheetFragment;
import org.firehound.devfestclone.fragments.QuestionFragment;
import org.firehound.devfestclone.fragments.QuizFragment;
import org.firehound.devfestclone.fragments.ScratchFragment;
import org.firehound.devfestclone.fragments.SponsorFragment;

public class MainActivity extends AppCompatActivity implements NavigationBottomSheetFragment.NavClickListener {
    private NavigationBottomSheetFragment navigationBottomSheetFragment = new NavigationBottomSheetFragment();
    public static int selectedFragment = 1;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomAppBar bottomAppBar = findViewById(R.id.bottom_app_bar);
        bottomAppBar.setNavigationOnClickListener(l -> {
            navigationBottomSheetFragment.show(getSupportFragmentManager(), null);
        });
        updateFragment(0);
    }

    @Override
    public void onNavItemClicked(int index) {
        try {
            navigationBottomSheetFragment.dismiss();
            selectedFragment = index;
            updateFragment(index);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateFragment(int index) {
        Fragment selFrag = null;
        switch (index){

            case 1:
                selFrag = new AgendaFragment();
                Log.d(TAG, String.valueOf(index));
                break;
            case 2:
                selFrag = new ScratchFragment();
                Log.d(TAG, String.valueOf(index));
                break;
            case 3:
                selFrag = new QuizFragment();
                Log.d(TAG, String.valueOf(index));
                break;
            case 4:
                selFrag = new SponsorFragment();
                Log.d(TAG, String.valueOf(index));
                break;
            case 5:
                selFrag = new QuestionFragment();
                Log.d(TAG, String.valueOf(index));
                break;
            case 6:
                selFrag = Attribouter.from(this).toFragment();
                Log.d(TAG, String.valueOf(index));
                break;
            default:
                selFrag = new AgendaFragment();



        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selFrag).commit();
    }
}
