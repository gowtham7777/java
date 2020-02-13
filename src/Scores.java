public class Scores {

    static void findTheBest(Integer[] h,Integer[] p,int time){
   // h=[1,4,1,2] p=[2,3,2,1] time=4
    int maxPoints=0,maxHours=0,finalPts=0;
            for(int i=0;i<h.length;i++){
                maxPoints= p[i];
                maxHours=h[i];
            for(int j=i+1;j<h.length;j++){
                if(maxHours + h[j] <=time ){
                    maxHours = maxHours + h[j];
                    maxPoints = maxPoints + p[j];
                    System.out.println("hours " + maxHours + "points" + maxPoints);
            }
        }
        if(maxPoints > finalPts)
            finalPts = maxPoints;
    }
        System.out.println(finalPts);
    }

    public static void main(String[] args) {

      //  Integer[] hours = new Integer[]{4, 4, 78, 89, 98,3,9,44,67,12};
      //  Integer[] points = new Integer[]{4, 4, 78, 89, 98,3,9,44,67,12};
        Integer[] hours =new Integer[]{1,4,2};
        Integer[] points =new Integer[]{2,3,2};
        findTheBest(hours, points,4);

       // System.out.println(sortedList);

    }
}
