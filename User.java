package personal.rohit.costsplitter;

import java.util.ArrayList;

/**
 * Created by rohit on 8/9/15.
 */

public class User implements Comparable{

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

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object another) {
        return name.compareTo(((User)another).name);
    }
}
