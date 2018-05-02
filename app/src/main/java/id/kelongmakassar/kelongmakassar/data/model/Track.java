package id.kelongmakassar.kelongmakassar.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ahmadnaufal on 03/03/18.
 */

public class Track implements Parcelable {
    private String lyricsPath;
    private String meaningPath;
    private String title;
    private int resId;

    public Track(int resId, String title, String lyricsPath, String meaningPath) {
        this.resId = resId;
        this.title = title;
        this.lyricsPath = lyricsPath;
        this.meaningPath = meaningPath;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyricsPath() {
        return lyricsPath;
    }

    public void setLyricsPath(String lyricsPath) {
        this.lyricsPath = lyricsPath;
    }

    public String getMeaningPath() {
        return meaningPath;
    }

    public void setMeaningPath(String meaningPath) {
        this.meaningPath = meaningPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lyricsPath);
        parcel.writeString(meaningPath);
        parcel.writeString(title);
        parcel.writeInt(resId);
    }

    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel parcel) {
            return new Track(parcel);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    private Track(Parcel in) {
        lyricsPath = in.readString();
        meaningPath = in.readString();
        title = in.readString();
        resId = in.readInt();
    }
}
