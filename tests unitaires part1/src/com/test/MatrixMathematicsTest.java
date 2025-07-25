package com.test;

import com.exception.NoSquareException;
import com.model.Matrix;
import com.service.MatrixMathematics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixMathematicsTest {
@Test
    public void testDeterminantM1x1() throws NoSquareException {
   double data[][]={{5}};
   Matrix matrice=new Matrix(data);
   Assertions.assertEquals(MatrixMathematics.determinant(matrice),5);
}
    @Test
    public void testDeterminantM2x2() throws NoSquareException {
        double data[][]={{1,2},{1,2}};
        Matrix matrice=new Matrix(data);
        assertEquals(MatrixMathematics.determinant(matrice),0);
    }
    @Test
    public void testDeterminantFor3x3Matrix() throws NoSquareException {
        double[][] data = {{6, 1, 1}, {4, -2, 5}, {2, 8, 7}};
        Matrix matrix = new Matrix(data);
        assertEquals(-306.0, MatrixMathematics.determinant(matrix));
    }

    @Test
    public void testNonSquareMatrixThrowsException() {
        double[][] data = {{1, 2, 3}, {4, 5, 6}};
        Matrix matrix = new Matrix(data);
        assertThrows(NoSquareException.class, () -> {
            MatrixMathematics.determinant(matrix);
        });
    }
}