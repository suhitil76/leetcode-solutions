import java.util.List;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int totalSum = 0;
        
        // Convert to primitive array to bypass List interface overhead
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums.get(i);
        }
        
        // Constraints state nums[i] values are between 1 and 100
        // We use a boolean array to track distinct values in O(1) time without allocations
        boolean[] seen = new boolean[101];
        
        for (int i = 0; i < n; i++) {
            int distinctCount = 0;
            
            for (int j = i; j < n; j++) {
                int val = arr[j];
                
                if (!seen[val]) {
                    seen[val] = true;
                    distinctCount++;
                }
                
                totalSum += distinctCount * distinctCount;
            }
            
            // Fast reset of the boolean array for the next starting point
            // This is much faster than recreating a new array or using a HashSet
            for (int j = i; j < n; j++) {
                seen[arr[j]] = false;
            }
        }
        
        return totalSum;
    }
}
