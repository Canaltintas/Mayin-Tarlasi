import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int column;
    boolean isWin;
    Scanner input=new Scanner(System.in);
    Random random=new Random();

    MineSweeper(int row,int column){
        this.column=column;
        this.row=row;
        this.isWin=false;
    }
    void run(){
        String[][] board=new String[this.row][this.column];
        String[][] map=new String[this.row][this.column];
        makeMap(board,map);
        rules();
        System.out.println("-------------");
        printMap(board);
        user(board,map);


    }
    void rules(){
        System.out.print("--- Mayın Tarlasına Hoşgeldiniz ---\n"+
                "Oyun Satırlar ve Sutunlar seçerek oynanır.\n"+"Seçilen  bölgede mayın varsa oyun biter \n"+
                "Seçilen bölgedeki rakamlar kutunun sağı,solu ve çaprazındaki bölgelerde ki mayın adedini gösterir. \n"+
                "Mayın Tarlasında başarılar dileriz. :) \n");

    }
    void user(String[][] board,String[][] map){
        int row;
        int column;
        while (!isWin){
            System.out.print("Satır Giriniz : ");
            row=input.nextInt()-1;
            System.out.println();
            System.out.print("Sütun Giriniz : ");
            column=input.nextInt()-1;
            if ((row < board.length) && (column < board[row].length)){
                if (map[row][column].equals("*")){
                    System.out.println("GameOver");
                    isWin=true;
                    System.out.println("Mayınlar :( ");
                    printMap(map);
                    break;
                }else {
                    mineCheck(map,board,row,column);
                }
                printMap(board);
            }else{
                System.out.println("Hatalı bir seçim yaptınız! Lütfen geçerli bir değer giriniz.");
            }
            endTheGame(board);
        }
    }
    void mineCheck(String[][] array,String[][] board,int i,int j){
        int tempRow=i;
        int tempColumn=j;
        int count=0;

        count +=isCheckMine(array,++i,j);
        i=tempRow;
        count +=isCheckMine(array,--i,j);
        i=tempRow;
        count +=isCheckMine(array,i,--j);
        j=tempColumn;
        count +=isCheckMine(array,i,++j);
        j=tempColumn;
        count +=isCheckMine(array,--i,++j);
        i=tempRow;
        j=tempColumn;
        count +=isCheckMine(array,++i,--j);
        i=tempRow;
        j=tempColumn;
        count +=isCheckMine(array,--i,--j);
        i=tempRow;
        j=tempColumn;
        count +=isCheckMine(array,++i,++j);
        i=tempRow;
        j=tempColumn;
        board[i][j]=Integer.toString(count);
    }
    int isCheckMine(String[][] array,int i,int j){
        if ((i >= 0) && (i<array.length) && (j >= 0) && (j < array[i].length)){
            if (array[i][j].equals("*")){
                return 1;
            }
        }
        return 0;
    }


    void makeMap(String[][] board, String[][] map){
        int itemNumber= (this.row * this.column)/4;
        boolean isAddField=false;

        for(int i=0;i<board.length;i++){
            for (int j=0; j<board[i].length;j++){
                if (random.nextInt(100)>50){
                    isAddField=true;
                }
                board[i][j]="-";
                if (isAddField && itemNumber >0){
                    map[i][j]="*";
                    isAddField=false;
                    itemNumber--;
                }else {
                    map[i][j]="-";
                }
            }
        }

    }
    void printMap(String[][] array){
        for (String[] i:array){
            for (String j:i){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
    void endTheGame(String[][] array){
        int itemNumber=(this.row * this.column)/4;
        int count=0;
        for (String[] i:array){
            for (String j:i){
                if (j.equals("-")){
                    count++;
                }
            }
        }
        if (count <=itemNumber){
            isWin=true;
            System.out.println("Tebrikler ! Kazandınız.");
        }
    }


}
