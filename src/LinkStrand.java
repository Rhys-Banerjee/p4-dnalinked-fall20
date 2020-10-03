public class LinkStrand implements IDnaStrand {

    private class Node{
        String info;
        Node next;
        public Node(String s){
            info = s;
            next = null;
        }
    }
    private Node myFirst,myLast;
    private long mySize;
    private int myAppends;
    private int count;
    private int dex;
    private Node myList;

    public LinkStrand() {
        this("");
    }

    public LinkStrand(String a){
        initialize(a);
    }

    @Override
    public long size() {
        return mySize;
    }

    @Override
    public void initialize(String source) {
        myFirst = new Node(source);
        myLast = myFirst;
        mySize = myFirst.info.length();
        myAppends = 0;
        count = 0;
        dex = 0;
        myList = myFirst;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        myLast.next = new Node(dna);
        myLast = myLast.next;
        mySize += myLast.info.length();
        myAppends ++;
        return this;
    }


    private void appendToFront(String dna){
        Node n = new Node(dna);
        this.mySize += n.info.length();
        n.next = myFirst;
        myFirst = n;
    }

    @Override
    public IDnaStrand reverse() {
        Node s = myFirst;
        LinkStrand s1 = new LinkStrand();
        while(s != null){
            StringBuilder s2 = new StringBuilder(s.info);
            String reverseRaw = s2.reverse().toString();
            s1.appendToFront(reverseRaw);
            s = s.next;
        }
        return s1;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        if(0 > index || this.size() <= index){
            throw new IndexOutOfBoundsException();
        }
        if(count >= index){
            dex = 0;
            myList = myFirst;
            count = 0;
        }
        while(index != count){
            count += 1;
            dex += 1;
            if(myList.next != null && myList.info.length()<=dex){
                myList = myList.next;
                dex = 0;
            }
        }
        return myList.info.charAt(dex);
    }

    @Override
    public String toString() {
        Node list = myFirst;
        StringBuilder str = new StringBuilder();
        while(list != null){
            str.append(list.info);
            list = list.next;
        }
        return str.toString();
    }
}
