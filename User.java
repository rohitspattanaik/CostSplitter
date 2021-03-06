package personal.rohit.costsplitter;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

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

//    public JSONObject toJSON() {
//        JSONObject jsonObject = new JSONObject();
//
//        try {
//            jsonObject.put("name", name);
//            jsonObject.put("friendsList", friends);
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//        return jsonObject;
//    }

    public String getSaveString() { //emulates xml with beginning and closing tags
        String ret = "User\n" + name;

        ret += "\nfriends";
        if(friends == null || friends.size() == 0) {
            ret += "\nnull";
        }
        else {
            //nothing here. not doing friends for now
        }

        ret += "\nend friends";
        ret += "\nend User";
        return ret;
    }

    public User getUserFromSaveString(String save) {
        String[] stuff = save.split("\n");
        if(!stuff[0].equals("User")) {
            return null;
        }

        //not doing anything with friends
        return new User(stuff[1]);
    }

    @Override
    public int compareTo(Object another) {
        return name.compareTo(((User)another).name);
    }

    @Override
    public boolean equals(Object another) {
        return ((User)another).name.equals(name);
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
