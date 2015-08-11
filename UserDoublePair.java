package personal.rohit.costsplitter;

import android.util.Pair;

/**
 * Created by rohit on 8/10/15.
 */
public class UserDoublePair implements Comparable {

    Pair<User, Double> pair;

    UserDoublePair() {
        pair = new Pair<>(null, 0.0);
    }

    UserDoublePair(User u, Double d) {
        pair = new Pair<>(u, d);
    }

    User getUser() {
        return pair.first;
    }

    Double getDouble() {
        return pair.second;
    }

    @Override
    public int compareTo(Object another) {
        if(pair.first.equals(((UserDoublePair)another).getUser())) {
            return pair.second.compareTo(((UserDoublePair)another).getDouble()));
        }
        else {
            return pair.first.compareTo(((UserDoublePair)another).getUser()));
        }
    }
}
