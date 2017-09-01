public class CourseAverage {

  public static void main(String args[]){
    double hwAvg = 80 ;
    double examAvg = 85;
    double targetAvg = 80;
    double gradeNeeded = (targetAvg
                          - (.2 * hwAvg)
                          - (.6 * examAvg))
                          / .2;
    System.out.println("Given a hwAvg of " +
      hwAvg + " and an Exam Average of " +
      examAvg + " you need a " + gradeNeeded +
      " to get a B");
  }
}
