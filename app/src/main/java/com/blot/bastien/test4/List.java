package com.blot.bastien.test4;

public class List {

    protected int mSmiley;
    protected int mMoodColor;
    protected String mMoodText;



    public List (int mSmiley, int mMoodColor, String mMoodText) {
        this.mSmiley = mSmiley;
        this.mMoodColor = mMoodColor;
        this.mMoodText = mMoodText;

    }

    public int getSmiley() {
        return mSmiley;
    }

    public void setSmiley(int smiley) {
        mSmiley = smiley;
    }

    public int getMoodColor() {
        return mMoodColor;
    }

    public void setMoodColor(int moodColor) {
        mMoodColor = moodColor;
    }

    public String getMoodText() {
        return mMoodText;
    }

    public void setMoodText(String moodText) {
        mMoodText = moodText;
    }

    @Override
    public String toString() {
        return "List{" +
                "mSmiley='" + mSmiley + '\'' +
                ", mMoodColor=" + mMoodColor +
                ", mMoodText='" + mMoodText + '\'' +
                '}';
    }
}
