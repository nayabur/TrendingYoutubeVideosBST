//https://stackoverflow.com/questions/19393202/how-can-i-add-a-space-in-between-two-outputs
//https://www.geeksforgeeks.org/java-system-nanotime-vs-system-currenttimemillis/

import com.sun.org.apache.xerces.internal.dom.PSVIDOMImplementationImpl;

import java.util.Scanner;

public class Main {

    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("\t\tWELCOME TO THE YOUTUBE VIRAL VIDEO MAKER");

        printCategories();
        System.out.print("Select a category number: ");
        int category = keyboard.nextInt();

        long nano_StartTime = 0;
        long nano_EndTime = 0;
        long nano_TotalTime = 0;//nano_createEndTime-nano_createStartTime;

        String csvFile = "USvideosClean.csv";
        //String csvFile = "/Users/naya/Documents/Sophomore Year/Spring/Data Structures/finalproject/USvideosClean.csv";
        CSVParser csv = new CSVParser();
        nano_StartTime = System.nanoTime();
        VideoBST pubDateBST = csv.parse(csvFile, category); // use test instead
        VideoNode toFind = new VideoNode(category);
        nano_EndTime = System.nanoTime();

        while (pubDateBST.getSize()==0){
            System.out.println("\t"+ "There are no videos in this category");
            System.out.print("Select a different category number: ");
            category = keyboard.nextInt();
            csv = new CSVParser();
            nano_StartTime = System.nanoTime();
            pubDateBST = csv.parse(csvFile, category); // use test instead
            toFind = new VideoNode(category);
            nano_EndTime = System.nanoTime();
        }
        nano_TotalTime = nano_EndTime-nano_StartTime;
        /*
        pubDateBST.print();
        System.out.println();
        System.out.print("leftmost: ");
        pubDateBST.getLeftmost(pubDateBST.root).videoContents.publishTime.print();
        System.out.println();
        System.out.print("rightmost: ");
        pubDateBST.getRightmost(pubDateBST.root).videoContents.publishTime.print();
        System.out.println();
         */

        nano_StartTime = System.nanoTime();
        System.out.print("Select a publication date between ");
        pubDateBST.getLeftmost(pubDateBST.root).videoContents.publishTime.print();
        System.out.print(" and ");
        pubDateBST.getRightmost(pubDateBST.root).videoContents.publishTime.print();
        System.out.println();
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        System.out.print("Enter date (mm/dd/yyyy): ");
        String date = keyboard.next();
        PublishDate selectedDate = new PublishDate();
        selectedDate.splitDate(date);
        toFind.videoContents.publishTime = selectedDate;
        nano_StartTime = System.nanoTime();
        VideoNode closest = pubDateBST.getClosest(toFind);
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        if (closest.videoContents.publishTime.compareTo(toFind.videoContents.publishTime) != 0) { //if not exact date match
            closest.print(nano_TotalTime);
            System.exit(0);
        }

        if (closest.left == null || (closest.left.videoContents.publishTime.compareTo(toFind.videoContents.publishTime) != 0)) { //if only one exact match
            closest.print(nano_TotalTime);
            System.exit(0);
        }

        nano_StartTime = System.nanoTime();
        VideoBST comCountBST = new VideoBST(9);

        /*
        System.out.println("CLOSEST: ");
        System.out.println("Title: "+ closest.videoContents.title);
        System.out.print("Date: ");
        closest.videoContents.publishTime.print();
        System.out.println();
        System.out.println("Comment Count: " + closest.videoContents.commentCount);
        System.out.println();

        System.out.println("LEFT");
        System.out.println("Title: "+ closest.left.videoContents.title);
        System.out.print("Date: ");
        closest.left.videoContents.publishTime.print();
        System.out.println();
        System.out.println("Comment Count: " + closest.left.videoContents.commentCount);
        System.out.println();
         */


        while (closest.videoContents.publishTime.compareTo(toFind.videoContents.publishTime) == 0) {
            if (closest.left == null) {
                closest.resetRelations();
                break;
            }
            VideoNode leftOfClosest = closest.left;
            closest.resetRelations();
            comCountBST.add(closest);
            closest = leftOfClosest;
        }


        System.out.print("Select a comment count between ");
        System.out.print(comCountBST.getLeftmost(comCountBST.root).videoContents.commentCount);
        System.out.print(" and ");
        System.out.print(comCountBST.getRightmost(comCountBST.root).videoContents.commentCount);
        System.out.println();
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        System.out.print("Enter amount: ");
        int comCount = keyboard.nextInt();
        toFind.videoContents.commentCount = comCount;
        nano_StartTime = System.nanoTime();
        VideoNode closestCom = comCountBST.getClosest(toFind);
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        if (closestCom.videoContents.compareTo(toFind.videoContents, 9) != 0) { //if not exact date match
            closestCom.print(nano_TotalTime);
            System.exit(0);
        }
        if (closestCom.left == null || (closestCom.left.videoContents.compareTo(toFind.videoContents, 9) != 0)) { //if only one exact match
            closestCom.print(nano_TotalTime);
            System.exit(0);
        }

        nano_StartTime = System.nanoTime();
        VideoBST viewsBST = new VideoBST(6);

        while (closestCom.videoContents.compareTo(toFind.videoContents, 9) == 0) {
            if (closestCom.left == null) {
                closestCom.resetRelations();
                viewsBST.add(closestCom);
                break;
            }
            VideoNode leftOfClosest = closestCom.left;
            closestCom.resetRelations();
            viewsBST.add(closestCom);
            closestCom = leftOfClosest;
        }

        System.out.print("Select a view count between ");
        System.out.print(viewsBST.getLeftmost(viewsBST.root).videoContents.views);
        System.out.print(" and ");
        System.out.print(viewsBST.getRightmost(viewsBST.root).videoContents.views);
        System.out.println();
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        System.out.print("Enter amount: ");
        int viewCount = keyboard.nextInt();
        toFind.videoContents.commentCount = comCount;
        nano_StartTime=System.nanoTime();
        VideoNode closestView = comCountBST.getClosest(toFind);
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        if (closestView.videoContents.compareTo(toFind.videoContents, 6) != 0) { //if not exact date match
            closestView.print(nano_TotalTime);
            System.exit(0);
        }
        if (closestView.left == null || (closestView.left.videoContents.compareTo(toFind.videoContents, 6) != 0)) { //if only one exact match
            closestView.print(nano_TotalTime);
            System.exit(0);
        }

        System.out.println("MAKE ANOTHER TREE");

    }

    public static void printCategories() {
        System.out.println("Video Categories: ");
        System.out.printf("\t%-30s %s\n", "1. Film & Animation", "2. Autos & Vehicles");
        System.out.printf("\t%-30s %s\n", "10. Music", "15. Pets & Animals");
        System.out.printf("\t%-30s %s\n",  "17. Sports", "18. Short Movies");
        System.out.printf("\t%-30s %s\n", "19. Travel & Events", "20. Gaming");
        System.out.printf("\t%-30s %s\n","21. Videoblogging","22. People & Blogs");
        System.out.printf("\t%-30s %s\n","23. Comedy","24. Entertainment");
        System.out.printf("\t%-30s %s\n", "25. News & Politics","26. Howto & Style");
        System.out.printf("\t%-30s %s\n", "27. Education","28. Science & Technology");
        System.out.printf("\t%-30s %s\n", "29. Nonprofits & Activism", "30. Movies");
        System.out.printf("\t%-30s %s\n","31. Anime/Animation","32. Action/Adventure");
        System.out.printf("\t%-30s %s\n","33. Classics","34. Comedy");
        System.out.printf("\t%-30s %s\n","35. Documentary","36. Drama");
        System.out.printf("\t%-30s %s\n","37. Family","38. Foreign");
        System.out.printf("\t%-30s %s\n","39. Horror","40. Sci-Fi/Fantasy");
        System.out.printf("\t%-30s %s\n","41. Thriller","42. Shorts");
        System.out.printf("\t%-30s %s\n","43. Shows","44. Trailers");
        System.out.println();
    }

}



