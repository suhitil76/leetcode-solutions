class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Checking if a valid number is mathematically possible
        if (sum < 1 || sum > 9 * num) {
            return "";
        }
        
        int numNines = sum / 9;
        int remainder = sum % 9;
        int numZeros = num - numNines - (remainder > 0 ? 1 : 0);
        
        StringBuilder sb = new StringBuilder();
        
        // Append all 9s first to maximize the value
        for (int i = 0; i < numNines; i++) {
            sb.append('9');
        }
        
        // Append the remainder digit if it exists
        if (remainder > 0) {
            sb.append(remainder);
        }
        
        // Append all trailing 0s
        for (int i = 0; i < numZeros; i++) {
            sb.append('0');
        }
        
        return sb.toString();
    }
}
