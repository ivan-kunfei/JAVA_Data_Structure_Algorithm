package leetcodePractice;

public class ReverseLinkedList {
    // 反转链表的非递归写法
    public ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;  // 要注意 head要有next 否则空指针异常
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverse_recursion(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode sub_node = reverse_recursion(head.next);
        head.next.next = head;
        head.next = null;
        return sub_node;
    }

    public static void main (String[] args){
        int [] nums = {1,2,3,4,5,6};
        ListNode head = new ListNode(nums);

        ListNode reverse_node = new ReverseLinkedList().reverse(head);
        System.out.println(reverse_node);

        head = new ListNode(nums);
        reverse_node = new ReverseLinkedList().reverse_recursion(head);
        System.out.println(reverse_node);
    }


}
