package personal.rohit.costsplitter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by rohit on 8/9/15.
 */

public class User implements Comparable, Parcelable{

    private String name;
    private ArrayList<User> friends;

    public User() {
        name = "NULL";
        friends = new ArrayList<>();
    }

    public User(User us) {
        name = us.name;
        friends = (ArrayList)us.friends.clone();
    }

    public User(String name) {
        this.name = name;
        friends = new ArrayList<>();
    }

    protected User(Parcel in) {
        name = in.readString();
        friends = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public boolean addFriend(User friend) {
        boolean ret = false;
        try {
            ret = friends.add(friend);
        } catch (Exception e) {
            ret = false;
        }
        return ret;
    }

    //for testing purposes only
    public ArrayList<User> getFriends() {
        return friends;
    }

//    public String getName() {
//        return name;
//    }

    @Override
    public int compareTo(Object another) {
        return name.compareTo(((User)another).name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(friends);
    }
}
