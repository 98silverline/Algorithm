import java.util.*;
import java.io.*;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        boolean flag = false;
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < nums.length; j++) {
                if(i == j) continue;
                if(nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    flag = true;
                    break;
                }
                if(flag) break;
            }
        }
        return result;
    }
}