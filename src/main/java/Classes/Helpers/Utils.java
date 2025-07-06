package Classes.Helpers;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<Object> objectListify(Object key) {
        ArrayList<Object> key_List = new ArrayList<>();
        key_List.add(key);
        return key_List;
    }

}
