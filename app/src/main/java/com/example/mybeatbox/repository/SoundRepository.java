package com.example.mybeatbox.repository;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.mybeatbox.model.Sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoundRepository {

    public static final String TAG = "beatBox";
    private String ASSET_FOLDER="sound";
    private List<Sound> mSounds;
    private Context mContext;
    private static SoundRepository sInstance;

    public static SoundRepository getsInstance(Context context) {
        if (sInstance==null)
            sInstance=new SoundRepository(context);
        return sInstance;
    }

    private SoundRepository(Context context) {
        mContext=context.getApplicationContext();
    }

    public List<Sound> getSounds(){
        List<Sound> sounds=new ArrayList<>();
        AssetManager assetManager=mContext.getAssets();
        try {
            String[] fileNames=assetManager.list(ASSET_FOLDER);
            for (String fileName:fileNames) {
                Log.i(TAG, fileName);

                String assetPath=ASSET_FOLDER+ "/" +fileName;
                Sound sound=new Sound(assetPath);
                sounds.add(sound);

            }
        } catch (IOException e) {
            Log.e(TAG,e.getMessage(),e);
        }
        return sounds;
    }
}
