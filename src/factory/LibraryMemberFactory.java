package factory;

public class LibraryMemberFactory {
    public static boolean isNumeric(String str) {
        if (str.isEmpty())
            return false;
        for (int i = 0; i < str.length(); i++) {

            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;

    }

    public static boolean isExactLength(String str, int len) {
        if (str.length() == len)
            return true;
        return false;
    }

    public static boolean isAllLetters(String str) {
        if (str.isEmpty())
            return false;
        for (int i = 0; i < str.length(); i++) {

            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;

    }

    //A-Z
    public static boolean isAllCapitals(String str) {
        if (str.isEmpty())
            return false;
        for (int i = 0; i < str.length(); i++) {

            if (!Character.isUpperCase(str.charAt(i))) {
                return false;
            }
        }
        return true;

    }

}
