package com.example.muhammad.amotul.digicore;

public class PerfectSquareCount {
    public static void main(String[] args){
        int [][] numbers = {{1,7,19,56, 89},{3,1,9,64, 16}, {7, 6, 9, 10, 12 }};

        System.out.println(calculate(numbers));
    }
    private static int calculate(int[][] numbers){
        int count = 0;
        for(int i = 0; i < numbers.length; i++){
            for(int j = 0; j < numbers.length; j++){
                if(checkIsPerfectSquare(numbers[i][j])){
                    count++;
                }
            }
        }
        return count;
    }
    private static boolean checkIsPerfectSquare(int n){
        int sqrRoot = (int) Math.sqrt(n);
        return sqrRoot * sqrRoot == n;
    }
}
