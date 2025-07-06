package Classes.Provider;

public class Provider {

    private int[] identifier;
    private int[] digit_Count;

    public Provider(int[] identifier_Param, int[] digit_Count_Param) {
        this.identifier = identifier_Param;
        this.digit_Count = digit_Count_Param;
    }

    public int[] getIdentifier() {
        return identifier;
    }

    public int[] getDigitCount() {
        return digit_Count;
    }
}
