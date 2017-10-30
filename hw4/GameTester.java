import java.util.Arrays;
public class GameTester {

    public static void main(String[] args) {
        try {
            Square s = new Square('e', '3');
            Square s2 = new Square('a', '2');
            Square s3 = new Square("a2");
            SquareSet ss = new SquareSet();
            ss.add(s);
            ss.add(s2);
            ss.add(s3);
            System.out.println("Set 1: " + ss);
            SquareSet ss2 = new SquareSet();
            ss2.add(s);
            ss2.add(s2);
            ss2.add(new Square("a5"));
            System.out.println("Set 2: " + ss2);
            System.out.println("Set 1 == Set 2: " + ss2.equals(ss));
            System.out.println("Set 2 containsAll Set 1: " + ss2.containsAll(ss));
            System.out.println("Set 2 rm e3: " + ss2.remove(new Square("e3")));
            System.out.println("Set 2: " + ss2);
            System.out.println(Arrays.toString(ss2.toArray()));
        } catch(InvalidSquareException e) {
            System.out.println("InvalidSquareException for valid square: "
                               + e.getMessage());
        }

    }
}
