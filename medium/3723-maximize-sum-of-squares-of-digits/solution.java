import java.util.Arrays;

class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Handle impossible configurations instantly
        if (sum < 1 || sum > 9 * num) {
            return "";
        }
        
        char[] result = new char[num];
        int numNines = sum / 9;
        int remainder = sum % 9;
        
        // 1. Fill all 9s at the beginning
        if (numNines > 0) {
            Arrays.fill(result, 0, numNines, '9');
        }
        
        // 2. Place the remainder digit right after the 9s (if it exists)
        if (remainder > 0) {
            result[numNines] = (char) ('0' + remainder);
            numNines++; // Advance pointer past the remainder
        }
        
        // 3. Fill the rest of the array with '0's instantly
        if (numNines < num) {
            Arrays.fill(result, numNines, num, '0');
        }
        
        return new String(result);
    }
}
