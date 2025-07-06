package Classes.Person;

import Classes.DBInterface.*;
import Classes.accounts.*;
import Classes.Helpers.*;

public class Person {
    String name;
    String family_Name;

    public Person(String name_Param) {

        String[] parts = name_Param.split(" ", 2);
        this.name = parts[0];
        if (parts.length > 1) {
            this.family_Name = parts[1];
        } else {
            this.family_Name = "";
        }
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return family_Name;
    }
}
