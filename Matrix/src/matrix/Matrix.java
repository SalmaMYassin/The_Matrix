
package matrix;

import java.util.Scanner;

/**
 *
 * @author Salma
 */

public class Matrix {

    private int row, column;
    private float[][] data;
    
    
    /* Overloaded Constructors */
    
    public Matrix(Matrix m) {               //Intialize matrix equals to a 2D array
        this.data = m.data;
    }
    
    public Matrix(float[][] matrix) {       //Intialize matrix equals to a 2D array
        this.data = matrix;

    }
    
    public Matrix(int row, int col) {       //Intialize matrix with #rows and #columns
        this.row = row;
        this.column = col;
        data = new float[row][col];
    }
    
    /* Matrix getters */
    
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public float getData(int row,int column) {
        return this.data[row][column];
    }
    
    
    /*   Setting the Matrix's values    */
    
    public void setValues(){                            //Set Values for a given matrix
        Scanner input = new Scanner (System.in);
        for(int i = 0;i < this.row;i++){            
            for (int j = 0;j < this.column;j++){        //looping through the Matrix's rows and columns 
                this.data[i][j] = input.nextFloat();    //Entering data
            }
            System.out.println();
        }
    }
    
    /* Matrix setters */
    
    public void setData(int row,int column, float data) {
        this.data[row][column] = data;
    }
    

   
    public void setValues(float matrix [][]){     //Set Values for a given matrix from a 2D array
        this.data = matrix;
    }
    
    public void setValues(Matrix matrix){         //Set Values for a given matrix from a 2D array
        for(int i = 0;i<matrix.row;i++){
            System.arraycopy(matrix.data[i], 0, this.data[i], 0, matrix.column);
        }
    }
    
    /*          Display Matrix          */
    
    public void display (){    
        for(int i = 0; i < this.row ;i++){
            for (int j = 0; j < this.column ;j++){
                System.out.print(this.data[i][j]);   //looping through the Matrix's rows and columns displaying elements
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    
    /* Overloaded Display Function to display a specific Matrix */
 
    public static void display (Matrix matrix){
        for(int i = 0; i < matrix.row ;i++){
            for (int j = 0; j < matrix.column ;j++){
                System.out.print(matrix.data[i][j]);    //looping through the Matrix's rows and columns displaying elements
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    
    /* Multipling a Matrix with a scalar */
    
    public Matrix ScalarMultiply(float scalar){
        
        for(int i = 0;i < this.row;i++){
            for (int j = 0;j < this.column;j++){    //Looping through the matrix
                this.data[i][j] *= scalar;          //Multiplying each matrix's value with the scalar
            }
        }
        
        return this;
    }

    /* Checking if you can multiply the two Matrices */ 
    
    public boolean validForMultiplication(Matrix matrix){

        return this.column == matrix.row;                                   //returns true or false if the first matrix's columns = the second matrix's rows

    }
    
    /*    Sum the two Matrices   */  
    public Matrix Sum (Matrix matrix){
       
        if(!(this.row == matrix.row) || !(this.column == matrix.column))    // if the two matrix are not equal in size then they're not valid for summaition so the function will return a zero matrix
            return zeroMatrix(this.row,this.column);
        
        Matrix newMatrix = new Matrix(this.row, this.column);               //creates new Matrix
       
       for(int i = 0;i < newMatrix.row;i++){
            for (int j = 0;j < newMatrix.column;j++){
                newMatrix.data[i][j] = this.data[i][j] + matrix.data[i][j]; //loops through the matrix summing every element with its corresponding element in the other matrix
            }
        }  
       return newMatrix;                                                    //returns the produced matrix
    }
    
    /*      checks if the two Matrices are equal     */    
    public boolean equals(Matrix matrix){
        
        boolean equal = (this.row == matrix.row) && (this.column == matrix.column); //checks if both matrices have same size
        
        if(equal){
            for(int i = 0;i < this.row;i++){
                for (int j = 0;j < this.column;j++){
                    if( this.data[i][j] != matrix.data[i][j] ){       //if the loop finds an element in the matrix is not equal to the corrosponding matrix it returns false
                        return false;
                    }
                }
            }
            return true;                                                            // if the loop processes correctly it returns true
        }
        return false;                                                               // if the 2 matrices are not equal in size it returns false
    }

    /*      checks if the matrix is symmetric     */    
    
    public boolean isSymmetric(){
        Matrix temp = new Matrix(this.row, this.column);    //creates a new matrix
        temp.setValues(this);                               //copies the values of the first matrix to the second matrix
        temp = transpose();                                 //transpose the second matrix
        return temp.equals(this);                           //checks the transposed matrix = the original matrix
        
//        for(int i = 0;i < this.row;i++){
//            for (int j = 0;j < this.column;j++){
//                if( this.data[i][j] != this.data[j][i] ){
//                    return false;
//                }
//            }
//        }
//        return true;       
                
        
    }
    
    /*     Generates new Zero Matrix    */
    public static Matrix zeroMatrix(int row,int column){
        Matrix newMatrix = new Matrix(row, column);     //creates a new matrix
        for(int i = 0 ; i < row ;i++){
            for(int j = 0 ; j < column ;j++){
                newMatrix.data[i][j] = 0;               //loops around the matrix setting all the elements to zero  
            }
        }
        return newMatrix;                               // returns the zero matrix
    }
    
    /*      Generates an identity matrix     */
    public static Matrix identityMatrix (int x){
        Matrix newMatrix = new Matrix(x, x);            //creates a new matrix
        for(int row = 0 ; row < x ; row++){
            for(int column = 0 ; column < x ;column++){
                if(row == column)
                    newMatrix.data[row][column] = 1;    //setting the diagonals to ones
                else
                    newMatrix.data[row][column] = 0;    //setting the rest of the elements to zero
            }
        }
        
        return newMatrix;                               //returns identity matrix
    }
 
    /*  Transposes the given matrix  */
    public Matrix transpose(){
        Matrix transposedMatrix = new Matrix(this.column, this.row);    //creates a new matrix setting the original's rows as columns and the original's columns as rows
        for (int i=0;i<this.row;i++) {
                for (int j=0;j<this.column;j++) {
                        transposedMatrix.data[j][i]= this.data[i][j];   //copy elements
                }
        }
        return transposedMatrix;

    }
    
    /*  Overloaded Transposes a specific matrix  */
    public static Matrix transpose(Matrix matrix){
        Matrix transposedMatrix = new Matrix(matrix.column, matrix.row);
        for (int i=0;i<matrix.row;i++) {
                for (int j=0;j<matrix.column;j++) {
                        transposedMatrix.data[j][i]= matrix.data[i][j];
                }
        }
        return transposedMatrix;
    }
    
    /*  Multiply two matrices  */
    public Matrix Multiply(Matrix matrix){ 
        if(this.validForMultiplication(matrix)){    //checks if matrix is valid for multiplication
            Matrix newMatrix = new Matrix(this.row, matrix.column);

            for(int i = 0;i < newMatrix.row;i++){
                for(int j = 0;j < newMatrix.column;j++){
                  for(int counter = 0; counter<matrix.row; counter++) //loops through matrix then loops through the each column and sums them
                      newMatrix.data[i][j] += this.data[i][counter] * matrix.data[counter][j];
                }
            }
            return newMatrix;
        }
        
        return zeroMatrix(this.row,matrix.column);
    }
    
    /*****************************************************/
    /*                  Matrix Inverse                   */
    /*****************************************************/
    
    
    public Matrix inverse()throws NotASquareException{ 
        if(this.row != this.column)   //Checking if its a square Matrix      
            throw new NotASquareException("ERROR: Cannot inverse a non squared Matrix!"); //Throws exception if its not a square matrix
        
        return (cofactor(this).transpose().ScalarMultiply(1/determinant(this))); //Preforms the equation of A^-1 = 1/det * (cofactor)^T
    }
    
    /*  changes the sign needed for the inverse  */
    public static int sign(int sign){    //Changes the sign of the matrix's data with inverse or determinant
        if(sign % 2 == 0)
            return 1;
        
        return -1;
    }
    
    /*  produce the cofactored matrix */
    public static Matrix cofactor(Matrix matrix)throws NotASquareException {
        Matrix cofacoredMatrix = new Matrix(matrix.row,matrix.column);
        
        for (int i=0 ;i< matrix.row;i++){
            for (int j =0;j<matrix.column;j++){
                cofacoredMatrix.data[i][j] = sign(i) * sign(j) * determinant(createSubMatrix(matrix, i,j)); //loops through the matrix
            }                                                                                               // creating sub matrices and get 
        }                                                                                                   //thier determinent while putting the original sign for the element
        
        return cofacoredMatrix;
    }
    
    /*  Creating Sub Matrices for the determinant */
    public static Matrix createSubMatrix(Matrix matrix, int execludedRow, int execludedColumn){
        Matrix subMatrix = new Matrix(matrix.row - 1, matrix.column - 1);   //every sub matrix is less than the original by one row and one column
        
        int r = -1;                                         //acts like a pointer for the rows to fill the rows from the original matrix starts at -1 to check when to increment it
        int c;                                              //acts like a pointer for the columns to fill the columns from the original matrix at -1 to check when to increment it
        
        for (int i = 0;i<matrix.row;i++){
            if(i == execludedRow)                           //if the row selected in the matrix is the excluded row skips it
                continue;
            
            r++;                                            // if not the excluded row increment the pointer by 1
            c = -1;
            for(int j = 0 ; j<matrix.column;j++){
                if(j == execludedColumn)                    //if the column selected in the matrix is the excluded column skips it
                    continue;
                
                c++;                                        // if not the excluded column increment the pointer by 1
                subMatrix.data[r][c] = matrix.data[i][j];   //puts the elements of the original matrix to the sub matrix
            }
        }
        
        return subMatrix;   
    }
    
    /* Produced the determinat of the matrix */
    public static float determinant (Matrix matrix) throws NotASquareException{
        if(matrix.row != matrix.column)
            throw new NotASquareException("ERROR: Cannot inverse a non squared Matrix!"); //throws an exception if the matrix isn't a square
        
        if(matrix.row == 1)
            return matrix.data[0][0];           //if the matrix consists of only one element returns that element 
        
        if(matrix.row == 2)
            return ( (matrix.data[0][0] * matrix.data[1][1]) - (matrix.data[1][0] * matrix.data[0][1]) );   //A stopping condition for the recursion when the matrix is a 2x2 matrix the eqaution process take place
        
        float sum = 0;
        
        for(int j = 0 ;j< matrix.column;j++){       //loops around the matrix columns
            if(matrix.data[0][j] == 0)              //to optimise the loop if the elemet is 0 skip because it'll get multiplied my zero anyway
                continue;
            sum += sign(j) * matrix.data[0][j] * determinant(createSubMatrix(matrix,0,j));  //a recursion so it'll breck down till it reaches a 2x2 marix and then rebuild finding the determinant of the original matrix 
        }
        return sum;
    }

    public static void main(String[] args) throws NotASquareException {
       
       Scanner input = new Scanner (System.in);
       int row, column;
       int choice;
       System.out.print("Enter the size of the matrix (row|column): ");
       row = input.nextInt();
       System.out.print(", ");
       column = input.nextInt();

       Matrix matrix = new Matrix(row,column);
       System.out.println("Insert Matrix: ");
       matrix.setValues();
       Matrix sameMatrix = new Matrix(row,column);
       sameMatrix.setValues(matrix);

       System.out.println("Your Matrix : \n");  
       matrix.display();
       
        System.out.println("What do you wanna do? ");
        System.out.println("1.Transpose Matrix");
        System.out.println("2.Multiply by a Scalar");
        System.out.println("3.Sum with another Matrix");
        System.out.println("4.Multiply with another Matrix");
        System.out.println("5.Check if Symmetric");
        System.out.println("6.Check if it equals another Matrix");
        System.out.println("7.Check if it's Valid For Multiplication with another Matrix");
        System.out.println("8.Generates a Zero Matrix");
        System.out.println("9.Generates an Identity Matrix");
        System.out.println("10.Display Matrix");
        System.out.println("11.Set New Values for the Matrix");
        System.out.println("12.Inverse Matrix");
        System.out.println("13.Insert equation and Solve it using the Cramer's rule");
        System.out.println("Any other number to exit");
       
        do{
            sameMatrix.setValues(matrix);
            
            choice = input.nextInt();
            switch(choice){
                case 1:
                System.out.println("Your Matrix transposed :");  
                display(transpose(sameMatrix));
                System.out.println("Enter Another Number: ");
                System.out.println();
                break;

            case 2:
                System.out.println("Enter Scalar value : "); 
                float scalar = input.nextFloat();
                System.out.println("Your Matrix Multiplied by "+ scalar +": "); 
                sameMatrix.ScalarMultiply(scalar);
                sameMatrix.display();
                System.out.println("Enter Another Number: ");
                System.out.println();
                 break;

            case 3:
                System.out.print("Enter the size of the matrix (row|column): ");
                row = input.nextInt();
                System.out.print(", ");
                column = input.nextInt();

                Matrix secMatrix = new Matrix(row,column);
                System.out.println("Insert another Matrix: ");
                secMatrix.setValues();
                display(sameMatrix.Sum(secMatrix));
                System.out.println("Enter Another Number: ");
                System.out.println();
                 break;

            case 4:
                System.out.print("Enter the size of the matrix (row|column): ");
                row = input.nextInt();
                System.out.print(", ");
                column = input.nextInt();

                Matrix sec = new Matrix(row,column);
                System.out.println("Insert another Matrix: ");
                sec.setValues();
                display(sameMatrix.Multiply(sec));
                System.out.println("Enter Another Number: ");
                System.out.println();
                 break;
            case 5:
                if(sameMatrix.isSymmetric())
                    System.out.println("Matrix is Symmetric");
                else
                    System.out.println("Matrix is NOT Symmetric");
                System.out.println("Enter Another Number: ");
                System.out.println();
                 break;
            case 6:
                System.out.print("Enter the size of the matrix (row|column): ");
                row = input.nextInt();
                System.out.print(", ");
                column = input.nextInt();

                Matrix eq = new Matrix(row,column);
                System.out.println("Insert another Matrix: ");
                eq.setValues();

                if(sameMatrix.equals(eq))
                    System.out.println("Matrices are Equal");
                else
                    System.out.println("Matrices are NOT equal");
                
                System.out.println("Enter Another Number: ");
                System.out.println();
                break;
            case 7:
                System.out.print("Enter the size of the other matrix (row|column): ");
                row = input.nextInt();
                System.out.print(", ");
                column = input.nextInt();

                Matrix va = new Matrix(row,column);
                System.out.println("Insert another Matrix: ");
                va.setValues();

                if(sameMatrix.validForMultiplication(va))
                    System.out.println("Matrix is Valid for multiplication");
                else
                    System.out.println("Matrix is NOT Valid for multiplication");
                
                System.out.println("Enter Another Number: ");
                System.out.println();
                break;

            case 8:
                System.out.print("Enter the size of the zero matrix (row|column): ");
                row = input.nextInt();
                System.out.print(", ");
                column = input.nextInt();
                Matrix zero = zeroMatrix(row,column);
                display(zero);
                System.out.println("Enter Another Number: ");
                System.out.println();
                break;

            case 9:
                System.out.print("Enter the size of the identity matrix : ");
                row = input.nextInt();

                Matrix id = identityMatrix(row);
                display(id);
                System.out.println("Enter Another Number: ");
                System.out.println();
                break;

            case 10:
                display(sameMatrix);
                System.out.println("Enter Another Number: ");
                System.out.println();
                 break;

            case 11:
                System.out.println("Enter new values for matrix: ");
                matrix.setValues();
                System.out.println("Enter Another Number: ");
                System.out.println();
                break;

            case 12:
                try{
                display(sameMatrix.inverse());
                }
                catch(NotASquareException ex){
                    System.out.println("Can't inverse a non squared Matrix");
                }
                System.out.println("Enter Another Number: ");
                System.out.println();
                
                break;
            case 13:
                MyEquation newEq = new MyEquation();
                newEq.insertNewEquation();
                newEq.display(newEq.solveSystem());
                System.out.println("Enter Another Number: ");
                System.out.println();
                
                break;
            default:
                System.out.println("Byeeeeeee");
                 break;
            } 
        }while(choice < 14);

    }
    
}
