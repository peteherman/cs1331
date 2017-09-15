public class Loopy {

    public static void main(String[] args) {
        String aretha = "RESPECT";
        char[] respect = new char[7];
        for (int i = 0; i < aretha.length(); i++) {
            respect[i] = aretha.charAt(i);
        }
        for (int i = 0; i < respect.length; i++) {
            System.out.println(respect[i]);
        }
    }
}
