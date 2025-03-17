package com.metacube.junit.JUnitAssignment7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SparseMatrixTest {
	@Test
	public void testConvert() {
		 List<pair> entries = List.of(
		            new pair(0, 1, 5),
		            new pair(1, 0, 3),
		            new pair(1, 2, 7)
		        );
		 SparseMatrix matrix = new SparseMatrix(3, 3, entries);
	        int[][] dense = matrix.convert();
	        assertEquals(5, dense[0][1]);
	        assertEquals(3, dense[1][0]);
	        assertEquals(7, dense[1][2]);
	    }
	@Test
	public void testTranspose() {
        List<pair> entries = List.of(
            new pair(0, 1, 5),
            new pair(1, 0, 3),
            new pair(1, 2, 7)
        );
        SparseMatrix matrix = new SparseMatrix(3, 3, entries);
        SparseMatrix transposed = matrix.transpose();
        int[][] transposedDense = transposed.convert();
        assertEquals(5, transposedDense[1][0]);
        assertEquals(3, transposedDense[0][1]);
        assertEquals(7, transposedDense[2][1]);
    }
	
	@Test
    public void testAddMatrices() {
        List<pair> entries1 = List.of(
            new pair(0, 1, 5),
            new pair(1, 0, 3),
            new pair(1, 2, 7)
        );
        List<pair> entries2 = List.of(
            new pair(0, 1, 2),
            new pair(1, 0, 4),
            new pair(1, 2, 6)
        );
        SparseMatrix matrix1 = new SparseMatrix(3, 3, entries1);
        SparseMatrix matrix2 = new SparseMatrix(3, 3, entries2);
        SparseMatrix sum = matrix1.Add(matrix2);
        int[][] sumDense = sum.convert();
        assertEquals(7, sumDense[0][1]);
        assertEquals(7, sumDense[1][0]);
        assertEquals(13, sumDense[1][2]);
    }
	@Test
    public void testMatrixMultiplication() {
        List<pair> entries1 = List.of(
            new pair(0, 1, 5),
            new pair(1, 0, 3),
            new pair(1, 2, 7)
        );
        List<pair> entries2 = List.of(
            new pair(0, 0, 1),
            new pair(1, 1, 2),
            new pair(2, 2, 3)
        );
        SparseMatrix matrix1 = new SparseMatrix(3, 3, entries1);
        SparseMatrix matrix2 = new SparseMatrix(3, 3, entries2);
        SparseMatrix product = matrix1.MatrixMultiplication(matrix2);
        int[][] productDense = product.convert();
        assertEquals(10, productDense[0][1]); 
        assertEquals(3, productDense[1][0]); 
        assertEquals(21, productDense[1][2]); 
    }
	
	
		
	

}
