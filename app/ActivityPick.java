package com.lottery.app.appUseCases.pick.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lottery.app.R;
import com.lottery.app.appUseCases.pick.fragments.FragmentPick;

public class ActivityPick extends AppCompatActivity implements FragmentPick.OnFragmentInteractionListener {

    private static final String FRAGMENT_PICK = "FragmentPick";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        initializeFragmentPick();
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void initializeFragmentPick() {
        FragmentPick fragmentPick = new FragmentPick();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_activity_pick, fragmentPick, FRAGMENT_PICK).addToBackStack(FRAGMENT_PICK).commit();
    }
}
