package com.virtualpairprogrammers.isbntools;

import java.util.HashSet;

public class Solution {
    //Put all int into a set, convert set to an array of n length
 public int[] gardenNoAdj(int n, int[][] paths){
     HashSet<Integer> plot = new HashSet<Integer>();

     for(int i =0; i < paths.length; i++){
         for(int j =0; j< paths.length; j++){
             plot.add(paths[i][j]);
         }
     }

     int num = plot.size();
     int arr[] = new int[num];

     int i = 0;

     for(int x : plot){
         arr[i++] = x;
     }

     return arr;

 }

}
