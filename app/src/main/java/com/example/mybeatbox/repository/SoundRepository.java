package com.example.mybeatbox.repository;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.example.mybeatbox.model.Sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoundRepository {

    public static final String TAG = "beatBox";
    public static final int MAX_STREAMS = 5;
    private String ASSET_FOLDER="sound";
    private List<Sound> mSounds=new ArrayList<>();
    private Context mContext;
    private SoundPool mSoundPool;
    private static SoundRepository sInstance;

    public static SoundRepository getsInstance(Context context) {
        if (sInstance==null)
            sInstance=new SoundRepository(context);
        return sInstance;
    }

    private SoundRepository(Context context) {
        mContext=context.getApplicationContext();
        mSoundPool=new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC,0);

        loadSounds();
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void play(Sound sound){

        if (sound==null || sound.getSoundId()==null)
            return;

        int playStat=mSoundPool.play(
                sound.getSoundId(),
                1.0f,
                1.0f,
                0,
                0,
                1.0f);

        if (playStat==0){
            Log.e(TAG, "this sound has not been played"+ sound.getName() );
        }

    }

    public void loadSounds(){

        AssetManager assetManager=mContext.getAssets();
        try {
            String[] fileNames=assetManager.list(ASSET_FOLDER);
            for (String fileName:fileNames) {
                Log.i(TAG, fileName);
                String assetPath=ASSET_FOLDER+ "/" +fileName;
                Sound sound=new Sound(assetPath);

                AssetFileDescriptor afd=assetManager.openFd(sound.getAssetPath());
                int soundId=mSoundPool.load(afd,1);
                sound.setSoundId(soundId);

                mSounds.add(sound);

            }
        } catch (IOException e) {
            Log.e(TAG,e.getMessage(),e);
        }

    }
}
