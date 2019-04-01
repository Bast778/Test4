package com.blot.bastien.test4;

public class List {

    protected String mDayName;
    protected int mMoodColor;
    protected String mMoodText;



    public List (String mDayName, int mMoodColor, String mMoodText) {
        this.mDayName = mDayName;
        this.mMoodColor = mMoodColor;
        this.mMoodText = mMoodText;



    }

    public String getDayName() {
        return mDayName;
    }

    public void setDayName(String dayName) {
        mDayName = dayName;
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
                "mDayName='" + mDayName + '\'' +
                ", mMoodColor=" + mMoodColor +
                ", mMoodText='" + mMoodText + '\'' +
                '}';
    }
}
