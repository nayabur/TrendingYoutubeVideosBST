//https://www.programcreek.com/2014/05/leetcode-closest-binary-search-tree-value-java/

public class VideoBST {

    public VideoNode root;
    private VideoNode deep;
    private int contentIndex;
    private int size;

    private long min = Long.MAX_VALUE;
    private VideoNode closest=null;

    public VideoBST(int index) {
        root = null;
        size = 0;
        contentIndex = index;
    }

    public void add(VideoNode toAdd) {
        root = addRootless(root, toAdd);
        size=size+1;
    }

    private VideoNode addRootless(VideoNode current, VideoNode toAdd) {

        if (current == null) {
            current = toAdd;
            return current;
        } else if (toAdd.videoContents.compareTo(current.videoContents, contentIndex) > 0) {

            /*
            System.out.print("result: ");
            System.out.println(toAdd.videoContents.compareTo(current.videoContents, contentIndex));
            System.out.println("one > two, RIGHT");
             */

            addRootless(current.right, toAdd);
            if (current.right == null) {
                VideoNode nodeUpdate = toAdd;
                current.addRight(nodeUpdate);
            }
        } else if (toAdd.videoContents.compareTo(current.videoContents, contentIndex) <= 0) {
            /*
            System.out.print("result: ");
            System.out.println(toAdd.videoContents.compareTo(current.videoContents, contentIndex));
            System.out.println("one <= two, LEFT");
             */

            addRootless(current.left, toAdd);
            if (current.left == null) {
                VideoNode nodeUpdate = toAdd;
                current.addLeft(nodeUpdate);
            }
        }
        return current;
    }

    public void print(){
        traversePrint(root);
    }

    private void traversePrint(VideoNode n) {
        if (n == null) return;
        else n.videoContents.print(contentIndex);
        System.out.print(", ");
        traversePrint(n.left);
        traversePrint(n.right);
    }

    public int getSize(){
        return size;
    }

    public VideoNode getLeftmost (VideoNode node) {
        if (node.left==null) {
            return node;
        }
        VideoNode leftmost = getLeftmost(node.left);
        return leftmost;
    }

    public VideoNode getRightmost (VideoNode node) {
        if (node.right==null) {
            return node;
        }
        VideoNode rightmost = getRightmost(node.right);
        return rightmost;
    }

    public boolean find(VideoNode toFind) {
        if (root==null) {
            return false;
        }
        else {
            return findRootless(root, toFind);
        }
    }

    private boolean findRootless(VideoNode current, VideoNode toFind){
        if (current==null) {
            return false;
        }
        else if (toFind.videoContents.compareTo(current.videoContents, contentIndex)==0) {
            return true;
        }
        else if (toFind.videoContents.compareTo(current.videoContents, contentIndex)>0) {
            return findRootless(current.right, toFind);
        }
        else if (toFind.videoContents.compareTo(current.videoContents, contentIndex)<0) {
            return findRootless(current.left, toFind);
        }
        return false;
    }

    public VideoNode getClosest(VideoNode find) {
        findClosest(find);
        return closest;
    }

    public void findClosest(VideoNode toFind) {
        if (root==null) {
            return;
        }
        else {
            findClosestRootless(root, toFind);
        }
    }

    private void findClosestRootless(VideoNode current, VideoNode toFind){

        if (current==null) {
            return;
        }
        if (toFind.videoContents.subtract(current.videoContents, contentIndex) < min) {
            min = toFind.videoContents.subtract(current.videoContents, contentIndex);
            closest = current;
            if ((toFind.videoContents.subtract(current.videoContents,contentIndex))==0){
                return;
            }
        }
        if (toFind.videoContents.compareTo(current.videoContents, contentIndex)>0) {
            findClosestRootless(current.right, toFind);
        }
        else if (toFind.videoContents.compareTo(current.videoContents, contentIndex)<0) {
            findClosestRootless(current.left, toFind);
        }
    }


        /*

        @Override
        public boolean add(int Video) {
            root = addRootless(root, toAdd);
            return true;
        }

        private Node addRootless (Node node, int toAdd) {
            if (node == null) {
                node = new Node(toAdd);
                return node;
            } else if (toAdd > node.contents) {
                addRootless(node.right, toAdd);
                if (node.right == null) {
                    Node nodeUpdate = new Node(toAdd);
                    node.addRight(nodeUpdate);
                }
            } else if (toAdd < node.contents) {
                addRootless(node.left, toAdd);
                if (node.left == null) {
                    Node nodeUpdate = new Node(toAdd);
                    node.addLeft(nodeUpdate);
                }
            }
            return node;
        }
   */
}


