package com.example.mybeatbox.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.mybeatbox.R;
import com.example.mybeatbox.controller.fragment.BeatBoxFragment;

public class BeatBoxActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}