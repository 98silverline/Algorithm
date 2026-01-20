class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int n : nums) {
            hm.put(n, hm.getOrDefault(n, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if(entry.getValue() != 2) return entry.getKey();
        }
        return 0;
    }
}