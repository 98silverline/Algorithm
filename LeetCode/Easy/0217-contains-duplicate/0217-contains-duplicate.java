class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for(int n : nums) {
            if(hs.contains(n)) return true;
            hs.add(n);
        }
        return false;
    }
}