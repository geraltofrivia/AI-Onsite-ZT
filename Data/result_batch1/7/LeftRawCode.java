// https://github.com/crossoverJie/JCSprout/tree/fc4c6e5f6d1772c2aec4c0f94cb3c8e7eb04018f/src/main/java/com/crossoverjie/actual/LRUAbstractMap.java#L153-L188
public class TempClass {
    @Override
    public Object get(Object key) {

        int hash = hash(key) ;
        int index = hash % arraySize ;
        Node currentNode = (Node) arrays[index] ;

        if (currentNode == null){
            return null ;
        }
        if (currentNode.next == null){

            //更新时间
            currentNode.setUpdateTime(System.currentTimeMillis());

            //没有冲突
            return currentNode ;

        }

        Node nNode = currentNode ;
        while (nNode.next != null){

            if (nNode.key == key){

                //更新时间
                currentNode.setUpdateTime(System.currentTimeMillis());

                return nNode ;
            }

            nNode = nNode.next ;
        }

        return super.get(key);
    }

}