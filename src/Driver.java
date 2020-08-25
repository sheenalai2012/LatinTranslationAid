import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        InputDivider sent = new InputDivider(in);

        System.out.println(sent.toString());
    }
}
