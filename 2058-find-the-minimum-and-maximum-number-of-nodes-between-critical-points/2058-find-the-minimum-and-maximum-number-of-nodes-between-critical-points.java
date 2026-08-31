/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode prev = head; 
        ListNode curr = head.next;
        int i = 1;
        int PrevCriticalPos = -1;
        int FirstCriticalPos = -1;
        int min = Integer.MAX_VALUE;

        // int[] ans = new int[2];
        // if(head.size() <= 2 ){
        //     Arrays.fill(ans , -1);
        //     return ans;
        // }

        while(curr.next != null){
            // local minima & local maxima
            if(curr.val < prev.val && curr.val < curr.next.val || curr.val > prev.val && curr.val > curr.next.val){
                if(PrevCriticalPos == -1){
                    PrevCriticalPos = i;
                    FirstCriticalPos = i;
                }else{
                    min = Math.min(min , Math.abs(i - PrevCriticalPos));
                    PrevCriticalPos = i;
                }
            }
            i++;
            prev = curr;
            curr = curr.next;
        }

        if(FirstCriticalPos == PrevCriticalPos){
            return new int[]{-1,-1};
        }

        int max = PrevCriticalPos - FirstCriticalPos;
       return new int[]{min,max};
    }
}