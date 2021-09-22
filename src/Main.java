import java.util.Scanner;

public class Main {
    public static void main (String[] args)
    {
        int size = -1, first = -1;
        Scanner in = new Scanner(System.in);
        while (size < 1) {
            System.out.print("Введите количество городов: ");
            size = in.nextInt();
        }
        while (first < 0 || first > size-1) {
            System.out.print("Введите первый город: ");
            first = in.nextInt();
        }
        MyMatrix myMatrix = new MyMatrix(first,size,1);
        myMatrix.printmatrix();
        for (int i = 0; i<size-1; i++)
        {
            System.out.println("Шаг "+(i+1));
            myMatrix.printsolution();
            myMatrix.minforevery();
            myMatrix.searchmin();
        }
        myMatrix.printend();
    }
}
