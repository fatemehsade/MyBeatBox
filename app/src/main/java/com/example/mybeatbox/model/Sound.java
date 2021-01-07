package com.example.mybeatbox.model;

public class Sound {
    private String mName;
    private String mAssetPath;
    private Integer mSoundId;


    public Sound() {
    }

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] sections=assetPath.split("/");
        String fileNameWithExtension=sections[sections.length-1];
        int lastIndex=fileNameWithExtension.lastIndexOf(".");
        mName=fileNameWithExtension.substring(0,lastIndex);
    }


    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public void setAssetPath(String assetPath) {
        mAssetPath = assetPath;
    }
}
