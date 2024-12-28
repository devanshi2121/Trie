public class Implementation2 {
    static class Node{
        int cntEndWith=0;
        int cntPrefix=0;
        Node[] links=new Node[26]; 
        boolean flag=false;
        boolean containsKey(char ch){
            return links[ch-'a']!=null;
        }
        void put(char ch,Node node){
            links[ch-'a']=node;
        }
        Node get(char ch){
            return links[ch-'a'];
        }
        void increaseEnd() {
            cntEndWith++;
        }
        void increasePrefix() {
            cntPrefix++;
        }
        void deleteEnd() {
            cntEndWith--;
        }
        void reducePrefix() {
            cntPrefix--;
        }
    }

    private Node root;
    Implementation2(){
        root=new Node();
    }

    void insert(String word){
        Node node=root;
        for(int i=0;i<word.length();i++){
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i),new Node());
            }
            node=node.get(word.charAt(i));
            node.increasePrefix();
        }
        node.increaseEnd();
    }
    int countWords(String word){
        Node node=root;
        for(int i=0;i<word.length();i++){
            if(node.containsKey(word.charAt(i))){
                node=node.get(word.charAt(i));
            }else{
                return 0;
            }
        }
        return node.cntEndWith;
    }
    int countWordsStartsWith(String word){
        Node node=root;
        for(int i=0;i<word.length();i++){
            if(node.containsKey(word.charAt(i))){
                node=node.get(word.charAt(i));
            }else{
                return 0;
            }
        }
        return node.cntPrefix;
    }
    void erase(String word){
        Node node=root;
        for(int i=0;i<word.length();i++){
            if(node.containsKey(word.charAt(i))){
                node=node.get(word.charAt(i));
                node.reducePrefix();
            }else{
                return;
            }
        }
        node.deleteEnd();
    }
    public static void main(String[] args) {
        Implementation2 trie = new Implementation2();
        trie.insert("apple");
        trie.insert("app");
        System.out.println("Inserting strings 'apple', 'app' into Trie");
        System.out.print("Count Words Equal to 'apple': ");
        System.out.println(trie.countWords("apple"));
        System.out.print("Count Words Starting With 'app': ");
        System.out.println(trie.countWordsStartsWith("app"));
        System.out.println("Erasing word 'app' from trie");
        trie.erase("app");
        System.out.print("Count Words Equal to 'apple': ");
        System.out.println(trie.countWords("apple"));
        System.out.print("Count Words Starting With 'apple': ");
        System.out.println(trie.countWordsStartsWith("app"));
    }
}
