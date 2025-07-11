package Classes.Helpers;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<Object> objectListify(Object key) {
        ArrayList<Object> key_List = new ArrayList<>();
        key_List.add(key);
        return key_List;
    }

    public static void addToExistingObjectList(ArrayList<Object> list, Object key) {
        list.add(key);
    }

    public static int stringToInt(String input) {
        Integer num;
        try {
            num = Integer.parseInt(input);
            return num;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number: " + input);
            return 0;
        }
    }

}
