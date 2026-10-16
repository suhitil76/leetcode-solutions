# Subarrays Distinct Element Sum of Squares II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a **0-indexed **integer array `nums`.

The **distinct count** of a subarray of `nums` is defined as:

- Let nums[i..j] be a subarray of nums consisting of all the indices from i to j such that 0 <= i <= j < nums.length. Then the number of distinct values in nums[i..j] is called the distinct count of nums[i..j].

Return *the sum of the **squares** of **distinct counts** of all subarrays of *`nums`.

Since the answer may be very large, return it **modulo** `109 + 7`.

A subarray is a contiguous **non-empty** sequence of elements within an array.

 

**Example 1:**

```
Input: nums = [1,2,1]
Output: 15
Explanation: Six possible subarrays are:
[1]: 1 distinct value
[2]: 1 distinct value
[1]: 1 distinct value
[1,2]: 2 distinct values
[2,1]: 2 distinct values
[1,2,1]: 2 distinct values
The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 + 22 + 22 + 22 = 15.

```

**Example 2:**

```
Input: nums = [2,2]
Output: 3
Explanation: Three possible subarrays are:
[2]: 1 distinct value
[2]: 1 distinct value
[2,2]: 1 distinct value
The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 = 3.
```

 

**Constraints:**

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 105

## Solution

**Language:** Java  
**Runtime:** 189 ms (beats 60.71%)  
**Memory:** 125.6 MB (beats 39.29%)  
**Submitted:** 2026-10-16T09:19:07.205Z  

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    // Segment Tree Nodes
    private long[] sumSq;
    private long[] sumVal;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumSq = new long[4 * n];
        sumVal = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int prevIdx = lastSeen.getOrDefault(nums[j], -1);
            
            // Increment the distinct count for all subarrays starting from (prevIdx + 1) to j
            update(1, 0, n - 1, prevIdx + 1, j, 1);
            
            // Add the sum of squares of all subarrays ending at j to the total sum
            totalSum = (totalSum + sumSq[1]) % MOD;
            
            lastSeen.put(nums[j], j);
        }

        return (int) totalSum;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] == 0) return;

        long val = lazy[node];
        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        // Apply lazy value to left child
        long leftLen = mid - start + 1;
        sumSq[leftChild] = (sumSq[leftChild] + 2 * val * sumVal[leftChild] % MOD + val * val % MOD * leftLen % MOD) % MOD;
        sumVal[leftChild] = (sumVal[leftChild] + val * leftLen) % MOD;
        lazy[leftChild] = (lazy[leftChild] + val) % MOD;

        // Apply lazy value to right child
        long rightLen = end - mid;
        sumSq[rightChild] = (sumSq[rightChild] + 2 * val * sumVal[rightChild] % MOD + val * val % MOD * rightLen % MOD) % MOD;
        sumVal[rightChild] = (sumVal[rightChild] + val * rightLen) % MOD;
        lazy[rightChild] = (lazy[rightChild] + val) % MOD;

        lazy[node] = 0;
    }

    private void update(int node, int start, int end, int l, int r, long val) {
        if (l <= start && end <= r) {
            long len = end - start + 1;
            sumSq[node] = (sumSq[node] + 2 * val * sumVal[node] % MOD + val * val % MOD * len % MOD) % MOD;
            sumVal[node] = (sumVal[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
            return;
        }

        pushDown(node, start, end);
        int mid = start + (end - start) / 2;

        if (l <= mid) {
            update(2 * node, start, mid, l, r, val);
        }
        if (r > mid) {
            update(2 * node + 1, mid + 1, end, l, r, val);
        }

        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sumVal[node] = (sumVal[2 * node] + sumVal[2 * node + 1]) % MOD;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/)