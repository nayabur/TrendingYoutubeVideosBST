import java.util.List;

public class VideoNode {

    Contents videoContents;
    public VideoNode left;
    public VideoNode right;
    public VideoNode parent;

    public VideoNode(String[] line) {
        left = null;
        right = null;
        parent = null;
        videoContents = new Contents(line);
    }

    public VideoNode(int cat) {
        videoContents = new Contents(cat);
    }

    public void addLeft (VideoNode child) {
        left = child;
        child.parent = this;
    }

    public void addRight (VideoNode child) {
        right = child;
        child.parent = this;
    }
    public void resetRelations() {
        left = null;
        right = null;
        parent = null;
    }
    public void print(long time){
        System.out.println();
        System.out.println("YOUR VIDEO DETAILS: ");
        System.out.println("\t" + '"' + this.videoContents.title + '"' + " by " + this.videoContents.channelTitle);
        System.out.print("\tPublished on: ");
        this.videoContents.print(5);
        System.out.print("\tTrended on: ");
        this.videoContents.print(1);
        System.out.println();
        System.out.print("\tOn ");
        this.videoContents.print(1);
        System.out.println(" your video had...");
        System.out.printf("\t\t%-20s %s\n",  "Views: " + this.videoContents.views, "Likes: " + this.videoContents.likes);
        System.out.printf("\t\t%-20s %s\n",  "Comments: " + this.videoContents.commentCount, "Dislikes: " + this.videoContents.dislikes);
        System.out.println("\t" + "Link: https://www.youtube.com/watch?v=" + this.videoContents.videoID);
        System.out.println("Time spent creating and parsing data structures in nanoseconds: " + time);
        System.out.println();
    }
}

