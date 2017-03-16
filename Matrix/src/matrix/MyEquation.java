/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Scanner;

/**
 *
 * @author Salma
 */
public class MyEquation {
    
    int n;
    Matrix LHS;
    float RHS[];
    float detX[];
    
    Scanner input = new Scanner (System.in);
     
    MyEquation(){
        System.out.print("Enter number of variables in the equation: ");
        n = input.nextInt();        //askes the user for the number of variables in the equation
        LHS = new Matrix(n,n);      //inisialze the left hand side with a matrix
        RHS = new float[n];         //inisialze the right hand side with an array
        detX = new float[n];        //for holding the values of the variables
    }
    
    void insertNewEquation (){
        
        for (int row = 0 ; row < n ; row++){
            
            System.out.println("Equation "+(row+1)+": ");   
           
            for(int column = 0 ; column < n ; column++){
                System.out.print("X"+(column+1)+" = ");
                LHS.setData(row, column, input.nextFloat());    //insert new equation to the matrix
            }
            
            System.out.println("Enter absolute value for Equation "+(row+1)+" = ");
            RHS[row] = input.nextFloat();                       // insert RHS
        }
        
    }
        void swapEquations (Matrix LHS, int i, int j){  //normal swapping in array
        
            float[] temp=new float[n];

            for (int l = 0; l < n; l++) {
                temp[l]= LHS.getData(i, l);             //insert elements from i index of LHS in temp array
            }
            for (int k = 0;k < n; k++) {
                LHS.setData(i, k, LHS.getData(j, k));   //insert elements from k index of LHS in i index of LHS
            }
            for (int m = 0; m < n; m++) {
               LHS.setData(j, m, temp[m]);              //returns the elements from the temp array to i index of LHS
            }

    }
    
    float [] solveSystem () throws NotASquareException {
        float det = Matrix.determinant(LHS);            //get the seterminant of the original equation
        Matrix temp = new Matrix(n,n);
        
        for(int eq = 0 ; eq < n ; eq++){
            temp.setValues(LHS);                        //returns the matrix back to its original elements after each column is changed by the RHS
            for(int r = 0 ; r < n ; r++){
                for(int c = 0 ; c < n ; c++){
                    if(c == eq)
                        temp.setData(r, c, RHS[r]);     //set each column to the RHS one at a time
                }
            }
            detX[eq] = Matrix.determinant(temp)/det;    // gets the determinant of the matrix each time a column is changed bythe RHS
        }
        return detX;
    }
    
    
    public void display(float[] detX){
        System.out.println();
        for(int eq=0;eq<n;eq++){
            System.out.println("X"+(eq+1)+" = "+ detX[eq]); //display the values of the variables
        }
    }
}

       
