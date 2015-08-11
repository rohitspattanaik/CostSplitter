package personal.rohit.costsplitter;

import java.util.ArrayList;

/**
 * Created by rohit on 8/9/15.
 */

public class User implements Comparable{

    String name;
    ArrayList<User> friends;

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

    @Override
    public int compareTo(Object another) {
        return name.compareTo(((User)another).name);
    }
}
