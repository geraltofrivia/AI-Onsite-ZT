// https://github.com/crossoverJie/JCSprout/tree/fc4c6e5f6d1772c2aec4c0f94cb3c8e7eb04018f/src/main/java/com/crossoverjie/actual/LRUAbstractMap.java#L106-L150
public class TempClass {
    @Override
    public Object put(Object key, Object value) {
        int hash = hash(key);
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            arrays[index] = new Node(null,null, key, value);

            //写入队列
            QUEUE.offer((Node) arrays[index]) ;

            sizeUp();
        }else {
            Node cNode = currentNode ;
            Node nNode = cNode ;

            //存在就覆盖
            if (nNode.key == key){
                cNode.val = value ;
            }

            while (nNode.next != null){
                //key 存在 就覆盖 简单判断
                if (nNode.key == key){
                    nNode.val = value ;
                    break ;
                }else {
                    //不存在就新增链表
                    sizeUp();
                    Node node = new Node(nNode,null,key,value) ;

                    //写入队列
                    QUEUE.offer(currentNode) ;

                    cNode.next = node ;
                }

                nNode = nNode.next ;
            }

        }

        return null ;
    }

}