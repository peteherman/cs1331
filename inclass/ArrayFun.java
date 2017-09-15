public class ArrayFun {

    private static int[] a = {1, 2, 3};
    private static int[] a2 = {1, 3, 5};

    public static String arrayToString(int[] b) {
        String result = "[";
        for (int i = 0; i < b.length; i++) {
            result += b[i];
            if (i < (b.length - 1)) {
                result += ",";
            }
        }
        return (result + "]");
    }
    public static void main(String[] args) {
        System.out.println(arrayToString(a));
        System.out.println(arrayToString(a2));
    }
}
