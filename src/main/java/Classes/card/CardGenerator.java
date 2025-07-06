package Classes.card;

import Classes.Helpers.TimeH;
import Classes.Person.Person;
import Classes.Provider.Provider;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class CardGenerator {
    public ArrayList<Integer> card_Number;
    String name;
    String family_Name;
    int expiry_Month;
    int expiary_Year;

    public CardGenerator(Person Owner, Provider Processor) {
        int cvv_Hash;
        int pin_Hash;

        this.card_Number = generateCardNumber(Processor.getDigitCount()[0], Processor.getIdentifier()[0]);
        this.expiry_Month = TimeH.getTimeFromNow(10).getMonthValue();
        this.expiary_Year = TimeH.getTimeFromNow(10).getYear();
        this.name = Owner.getName();
        this.family_Name = Owner.getFamilyName();

    }

    private static int[] reverseArray(int[] array) {
        int zero_Offset = 1;
        int size = array.length;
        for (int i = 0; i < size / 2; i++) {
            int temp = array[i];
            array[i] = array[size - zero_Offset - i];
            array[size - zero_Offset - i] = temp;
        }
        return array;
    }

    private static int calculateChecksum(ArrayList<Integer> card_Number) {
        ArrayList<Integer> reversed = new ArrayList<>(card_Number);
        Collections.reverse(reversed);
        boolean second = false;
        int checksum = 0;
        for (int num : reversed) {
            if (second) {
                num *= 2;
            }
            if (num > 9) {
                num -= 9;
            }
            checksum += num;
            second = !second;
        }
        return checksum;
    }

    static boolean luhnValidate(ArrayList<Integer> card_Number) {
        int checksum = calculateChecksum(card_Number);
        if (checksum % 10 == 0) {
            return true;
        }
        return false;
    }

    private static int[] rng(int size) {
        SecureRandom generator = new SecureRandom();
        int[] card_Number = new int[size];
        for (int i = 0; i < size; i++) {
            card_Number[i] = generator.nextInt(10); // 0–9
        }
        return card_Number;
    }

    private static int createLastDigit(int checksum) {
        return (10 - (checksum % 10)) % 10;
    }

    public ArrayList<Integer> generateCardNumber(int digit_Count, int identifier) {

        ArrayList<Integer> card_Number = new ArrayList<>();

        int identifier_Length = 0;
        while (identifier > 0) {
            card_Number.add(identifier % 10);
            identifier /= 10;
            identifier_Length++;
        }

        int last_Digit_Offset = 1;
        int[] random = rng(digit_Count - identifier_Length - last_Digit_Offset);

        for (int digit : random) {
            card_Number.add(digit);
        }
        int checksum = calculateChecksum(card_Number);
        card_Number.add(createLastDigit(checksum));
        return card_Number;
    }
}
