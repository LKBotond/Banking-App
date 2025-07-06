package Classes.card;

public class Card {

    // encrypted values
    private int cvv_Hash;
    private int pin_Hash;

    // card specific
    private int[] card_Number;
    private int expiry_Month;
    private int expiary_Year;
    private String name;
    private String family_Name;

    private int[] reverseArray(int[] array) {
        int zero_Offset = 1;
        int size = array.length;
        for (int i = 0; i < size / 2; i++) {
            int temp = array[i];
            array[i] = array[size - zero_Offset - i];
            array[size - zero_Offset - i] = temp;
        }
        return array;
    }

    private int calculateChecksum(int[] card_Number) {
        int[] reversed = reverseArray(card_Number);
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

    private boolean luhnValidate(int[] card_Number) {
        int checksum = calculateChecksum(card_Number);
        if (checksum % 10 == 0) {
            return true;
        }
        return false;
    }

}