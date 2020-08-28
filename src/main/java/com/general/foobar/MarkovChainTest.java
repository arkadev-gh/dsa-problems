package com.general.foobar;

import com.general.foobar.MarkovChain.*;

public class MarkovChainTest {
    public static void main(String[] args) {
        Rational a = new Rational(3, 4);
        Rational b = new Rational(7, 8);
        Rational c = new Rational(16, 64);
        Rational d = new Rational(12);
        Rational e = new Rational(197.654);

        double[][] test1 = new double[][]{{2.0, 3.4, 5.8}, {1.2, 8, 0}, {0, 1, 197.654}};
        double[][] test2 = new double[][]{{0}, {0}, {1}};
        double[][] test3 = new double[][]{{-0.25, 0.5, 1}, {1, 1, 1}, {4, -2, 1}};

        Matrix m = new Matrix(test1);
        Matrix n = new Matrix(test2);
        Matrix p = new Matrix(test3);
        Matrix iden = Matrix.getIdentityMatrix(3, 3);
        Matrix invP = Matrix.invert(p);
        Matrix result = p.subtract(m);

//        System.out.println(a.add(b));
//        System.out.println(a.subtract(b));
//        System.out.println(a.multiply(b));
//        System.out.println(a.divide(b));
//        System.out.println(c);
//        System.out.println(a.multiply(-0.5));
//        System.out.println(a.divide(d));
//        System.out.println(e);
//        System.out.println(m);
//        System.out.println(m.multiply(n));
//        System.out.println(invP);
        System.out.println(p.multiply(Matrix.invert(p)));
    }
}
