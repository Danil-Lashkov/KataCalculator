import java.util.TreeMap;

class Converter {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();


    public Converter(){
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);

        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");

    }

    public boolean isRoman(String input){
        return romanKeyMap.containsKey(input.charAt(0));
    }

    public int romanToInt(String part){

        int result = 0;
        int prevValue = 0;

        for (int i = part.length() - 1; i >= 0; i--) {
            int value = romanKeyMap.get(part.charAt(i));
            if (value < prevValue) {
                result = result - value;
            } else {
                result = result + value;
            }
            prevValue = value;
        }
        return result;
    }

    public String intToRoman(int value){
        String romanValue = "";
        int arabianValue;
        while(value != 0){
            arabianValue = arabianKeyMap.floorKey(value);
            romanValue = romanValue + arabianKeyMap.get(arabianValue);
            value = value - arabianValue;
        }
        return romanValue;
    }
}
