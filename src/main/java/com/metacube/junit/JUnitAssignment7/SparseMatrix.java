package com.metacube.junit.JUnitAssignment7;
import java.util.*;
/**
 * This class is used to represent row index , column index and value at that index
 */
class pair{
	final int row;
	final int column;
	final int data;
	pair(int row,int column,int data) {
		this.row=row;
		this.column=column;
		this.data=data;
	}
}
//Main class that represent our sparse matrix
public class SparseMatrix {
	
	private final int rows;//Number of rows in matrix
	private final int cols;//Number of column in matrix
	private final ArrayList<pair>entries;//ArrayList contain all non zero entries
	//Constructor 
	public SparseMatrix(int rows,int cols,List<pair>list) {
		this.rows=rows;
		this.cols=cols;
		this.entries=new ArrayList<>();
		//IT iterates over the provided list and adds only non zero elements in the entries list
		for(pair p:list) {
			if(p.data!=0) {
				if(p.row>=rows || p.column>=cols) {
					throw new AssertionError();
				}
				entries.add(p);
			}

		}
	}
	/**
	 * Converting the sparse representation to 2D array
	 * Array have initially zero then only non zero values will add to it 
	 * @return Matrix
	 */
	public int[][] convert(){
		int [][]sparse=new int [rows][cols];
		for(pair ele:entries) {
			sparse[ele.row][ele.column]=ele.data;
		}
		return sparse;
	}
	/**
	 * This method computes the transpose of the sparse matrix
	 * first converts the current matrix to a 2D matrix then 
	 * creates a new 2D array for the transposed version
	 * @return Transposed_matrix
	 */
	public SparseMatrix transpose(){
		int [][] arr2=this.convert();
		int[][] transposed_arr=new int[cols][rows];
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				transposed_arr[j][i]=arr2[i][j];
			}
		}
		return fromDense(transposed_arr);
	}
	/**
	 * THis method is converting our matrix back to sparse format
	 * @param dense
	 * @return sparseMatrix From 2D array
	 */
	private static SparseMatrix fromDense(int[][] dense) {
		List<pair> entries = new ArrayList<>();
		//Iterate through each element of dense array
		for(int i=0; i<dense.length; i++) {
			for(int j=0; j<dense[0].length; j++) {
				if(dense[i][j] != 0) {//Adds non zero elements to the entries
					entries.add(new pair(i, j, dense[i][j]));
				}
			}
		}
		return new SparseMatrix(dense.length, dense[0].length, entries);
	}
	/**
	 * This Method add two sparse matrices together
	 * @param a1
	 * @return
	 */
	public SparseMatrix Add(SparseMatrix a1) {
		//Checking if both matrices have same dimensions
		if(rows != a1.rows || cols != a1.cols) {
			//If not it throws an error
			throw new AssertionError("Matrix dimensions mismatch");
		}
		//Convert matrices to dense form then perform addition on it
		int [][] Matrix1=this.convert();
		int [][] Matrix2=a1.convert();
		int[][] Sum = new int[ rows][cols];

		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				Sum[i][j]=Matrix1[i][j]+Matrix2[i][j];
			}
		}
		//Return Result as new Sparse Matrix
		return fromDense(Sum);
	}
	
	/**
	 * This method multiplies two sparse matrices together
	 * @param m1
	 * @return
	 */
	public  SparseMatrix MatrixMultiplication(SparseMatrix m1) {
		//Check of multiplication is possible by comparing dimensions
		if(cols != m1.rows) {
			//If not throws an error
			throw new AssertionError();
		}
		int [][] Matrix1=m1.convert();
		List<pair> result = new ArrayList<>();
		//Iterates through each pair from both matrices 
		for(pair a : entries) {
			for(pair b : m1.entries) {
				//Checks if they can be multiplied based on their indices
				if(a.column == b.row) {
					boolean exists = false;
					for(pair r : result) {
						if(r.row == a.row && r.column == b.column) {
							r = new pair(r.row, r.column, r.data + (a.data * b.data));
							exists = true;
							break;
						}
					}
					if(!exists) {
						result.add(new pair(a.row, b.column, a.data * b.data));
					}
				}
			}
		}

		return new SparseMatrix(rows, m1.cols, result);
	}

	public boolean isSymmetric() {
		
		return this.equals(this.transpose()); 
	}
	

	private static void printMatrix(int[][] matrix) {
		for (int[] row : matrix) {
			for (int val : row) {
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		List<pair> entries1 = List.of(
		        new pair(0,4,9),
		        new pair(1,1,8),
		        new pair(2,0,4),
		        new pair(2,3,2),
		        new pair(3,5,5),
		        new pair(4,2,2)
		    );
		SparseMatrix matrix1 = new SparseMatrix(5, 6, entries1);
		System.out.println("Matrix 1");
		    printMatrix(matrix1.convert());


		    List<pair> entries2 = List.of(
		        new pair(0,4,9),
		        new pair(1,1,8),
		        new pair(2,0,4),
		        new pair(2,3,2),
		        new pair(3,5,5),
		        new pair(4,2,2)
		    );
		    SparseMatrix matrix2 = new SparseMatrix(5, 6, entries2);
		    
		    List<pair> entries3 = List.of(
			        new pair(0,4,9),
			        new pair(1,1,8),
			        new pair(2,0,4),
			        new pair(2,3,2),
			        new pair(3,5,5),
			        new pair(4,2,2),
			        new pair(4,3,4),
			        new pair(5,2,4)
			    );
		    	System.out.println(" ");
			    SparseMatrix matrix3 = new SparseMatrix(6, 6, entries3);
			    printMatrix(matrix3.convert());

		    while(true) {
		    System.out.println("\nMenu");
		    System.out.println("Choose 1:To transpose");
		    System.out.println("Choose 2:To Check Symmetric");
		    System.out.println("Choose 3:To ADD");
		    System.out.println("Choose 4:To Multiply");
		    System.out.println("Enter your choice");
		    
		    int choose=sc.nextInt();
		    sc.nextLine();
		    switch(choose) {
		    case 1:
			    SparseMatrix transposed = matrix1.transpose();
			    System.out.println("Transposed Matrix:");
			    printMatrix(transposed.convert());
			    break;
		    case 2:
		    	System.out.println(matrix1.isSymmetric());
		    	break;
		    case 3:
			    SparseMatrix sum = matrix1.Add(matrix2);
			    System.out.println("Sum of Matrices:");
			    printMatrix(sum.convert());
			    break;
		    case 4:
			    SparseMatrix Multiply = matrix1.MatrixMultiplication(matrix3);
			    System.out.println("Matrices Multiplication:");
			    printMatrix(Multiply.convert());
			    break;
			default:
				System.out.println("Please Enter the correct Value");
		    	break;
		    }
		  }

	}

}
