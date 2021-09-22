import java.util.HashMap;
import java.util.Random;

public class MyMatrix {
    private HashMap<Integer,Integer> candidates = new HashMap<>();
    private HashMap<Integer,Integer> solution = new HashMap<>();
    private HashMap<Integer,Integer> mincandidates = new HashMap<>();
    private int first = 0, size = 0, type = 0, maxlentgh = 10;
    private int sum = 0;
    private int[][] matrix;
    //if type = 0 - matrix [i][j] != matrix[i][j], another - matrix[i][j] ==matrix[i][j]
    MyMatrix (int first, int size,int type)
    {
        this.size = size;
        this.first = first;
        this.type = type;
        this.matrix = new int[size][size];
        Random rnd = new Random();
        if (this.type == 0) {
            for (int i = 0; i < this.size; i++)
                for (int j = 0; j<this.size; j++)
                    matrix[i][j] = Math.abs(rnd.nextInt()) % maxlentgh + 1;
        }
        else {
            for (int i = 0; i < this.size; i++)
                for (int j = i; j < this.size; j++) {
                    matrix[i][j] = Math.abs(rnd.nextInt()) % maxlentgh + 1;
                    matrix[j][i] = matrix[i][j];
                }
        }
        for (int i = 0; i<this.size; i++)
        {
            matrix[i][i] = 0;
            candidates.put(i,i);
        }
        candidates.remove(first);
        solution.put(0,first);
    }
    public void printsolution()
    {
        if (solution.size()<this.size) System.out.println("Текущий обход: ");
        for (int i = 0; i < solution.size();i++) {
            if (i != solution.size() - 1)
                System.out.print(solution.get(i) + "---");
            else System.out.print(solution.get(i));
        }
        System.out.println();

    }
    public void printend()
    {
        System.out.println("Итоговый цикл:");
        printsolution();
        System.out.println("Итоговая длина цикла: " + sum);
    }
    public void printmatrix()
    {
        for (int i = 0; i<this.size; i++) {
            for (int j = 0; j < this.size; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }

    public HashMap<Integer, Integer> getCandidates() {
        return candidates;
    }

    public void minforevery()
    {
        mincandidates.clear();
        int min = this.maxlentgh*2;
        int minindex = 0;
        for (int i = 0; i< solution.size(); i++) {
            min = this.maxlentgh*2;
            minindex = 0;
            for (int j = 0; j < this.size; j++) {
                if (candidates.get(j) != null) {
                    if (matrix[solution.get(i)][j] < min
                            && j != solution.get(i)) {
                        min = matrix[solution.get(i)][j];
                        minindex = j;
                    }
                }
            }
            mincandidates.put(solution.get(i),minindex);
        }
        for (int i = 0; i < solution.size(); i++)
        {
            System.out.println(solution.get(i) + " ----- "
                    + mincandidates.get(solution.get(i)) + " ----- "
            + matrix[mincandidates.get(solution.get(i))][solution.get(i)]);
        }

    }
    public void searchmin()
    {
        int min = this.maxlentgh*2;
        int minindex = 0;
        int mindistance = this.maxlentgh*2;
        for (int i = 0; i<solution.size(); i++)
        {
           if (matrix[mincandidates.get(solution.get(i))][solution.get(i)]<min
           || (matrix[mincandidates.get(solution.get(i))][solution.get(i)]== min
           && i<(solution.size()-1) && matrix[mincandidates.get(solution.get(i))][solution.get(i+1)]<mindistance))
                {
                    min = matrix[mincandidates.get(solution.get(i))][solution.get(i)];
                    if (solution.get(i+1)!=null)
                        mindistance = matrix[mincandidates.get(solution.get(i))][solution.get(i+1)];
                    minindex = i;
                }
        }
        solution.put(solution.size(),solution.get(solution.size()-1));
        for (int i = solution.size()-1; i>minindex + 1; i--)
        {
            solution.remove(i);
        solution.put(i,solution.get(i-1));
        }
        solution.remove(minindex+1);
        solution.put(minindex+1,mincandidates.get(solution.get(minindex)));
        candidates.remove(mincandidates.get(solution.get(minindex)));
        this.sum = 0;
        for (int i = 0; i<solution.size()-1; i++)
        {
            this.sum+=matrix[solution.get(i)][solution.get(i+1)];
        }
        System.out.println("Выбранный город - " + mincandidates.get(solution.get(minindex)));
        System.out.println("Расстояние от предыдущего до выбранного - "
                + matrix[solution.get(minindex)][mincandidates.get(solution.get(minindex))]);
        System.out.println("Полученная длина текущего обхода - " + this.sum);
    }
    public void setCandidates(HashMap<Integer, Integer> candidates) {
        this.candidates = candidates;
    }

    public HashMap<Integer, Integer> getSolution() {
        return solution;
    }

    public void setSolution(HashMap<Integer, Integer> solution) {
        this.solution = solution;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

}
