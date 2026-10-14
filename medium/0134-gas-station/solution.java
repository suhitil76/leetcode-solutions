class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int currentTank = 0;
        int startStation = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            currentTank += gas[i] - cost[i];

            // If we run out of gas, the current startStation is invalid
            if (currentTank < 0) {
                // Try starting from the next station
                startStation = i + 1;
                // Reset the tank for the new starting point
                currentTank = 0;
            }
        }

        // If total gas is less than total cost, it's impossible to complete the circuit
        return totalGas >= totalCost ? startStation : -1;
    }
}
