package com.hadyandev.charalist;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Character implements Parcelable {
    private String name, anime, description, quote, photo;

    protected Character(Parcel in) {
        name = in.readString();
        anime = in.readString();
        description = in.readString();
        quote = in.readString();
        photo = in.readString();
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.anime);
        dest.writeString(this.photo);
        dest.writeString(this.quote);
        dest.writeString(this.quote);
    }

    public Character() {
    }
}
