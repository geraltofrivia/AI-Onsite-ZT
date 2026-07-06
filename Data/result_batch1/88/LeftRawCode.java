// https://github.com/crossoverJie/JCSprout/tree/fc4c6e5f6d1772c2aec4c0f94cb3c8e7eb04018f/src/main/java/com/crossoverjie/algorithm/ReverseNode.java#L46-L76
public class TempClass {
    public  void reverseNode(Node head) {
        if (head == null) {
            return ;
        }

        //最终翻转之后的 Node
        Node node ;

        Node pre = head;
        Node cur = head.next;
        Node next ;
        while(cur != null){
            next = cur.next;

            //链表的头插法
            cur.next = pre;
            pre = cur;

            cur = next;
        }
        head.next = null;
        node = pre;


        //遍历新链表
        while (node != null){
            System.out.println(node.value);
            node = node.next ;
        }

    }

}