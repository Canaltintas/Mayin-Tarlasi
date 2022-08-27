import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a,b;
        Scanner input = new Scanner(System.in);
        System.out.print("Oyun alanı için satır değeri giriniz : ");
        a=input.nextInt();
        System.out.print("Oyun alanı için sütun değeri giriniz : ");
        b=input.nextInt();
        MineSweeper game=new MineSweeper(a,b);
        game.run();


    }
}
