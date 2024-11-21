import java.util.TreeSet;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int k=0;
for(int i=0;i<nums.length;i++){

    if(nums[i]!=nums[i+1]){
        k++;
    }
}
return k;
    }
}
