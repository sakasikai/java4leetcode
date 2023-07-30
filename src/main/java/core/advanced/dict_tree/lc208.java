package core.advanced.dict_tree;

class Trie {
    // 也叫前缀树
    // root_null => 26*node_first_character ...
    private class Node{
        boolean end; // 边路径代表str，end=true代表 存储了一个str，而不管其前缀
        Node[] edgeTo; // 26

        public Node(){
            edgeTo = new Node[26]; // 26 null}
        }
    }
    private Node start;

    public Trie() {
        start = new Node();
    }

    public void insert(String wd) {
        Node cur = start;
        for(char x: wd.toCharArray()){
            int i = x - 'a';
            if(cur.edgeTo[i] == null) // 没有节点，就创建
                cur.edgeTo[i] = new Node();
            // 树中转移节点
            cur = cur.edgeTo[i];
        }
        cur.end = true;
    }

    /**
     * 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
     * @param wd
     * @return
     */
    public boolean search(String wd) {
        Node cur = start;
        for(char x: wd.toCharArray()){
            int i = x - 'a';
            if(cur.edgeTo[i] == null) return false; // 没有节点可走，说明没插入过
            else cur = cur.edgeTo[i];
        }
        return cur.end;
    }

    /**
     * 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false
     * @param wd
     * @return
     */
    public boolean startsWith(String wd) {
        Node cur = start;
        for(char x: wd.toCharArray()){
            int i = x - 'a';
            if(cur.edgeTo[i] == null) return false; // 没有节点可走，说明没插入过
            else cur = cur.edgeTo[i];
        }
        return true;
    }
}
