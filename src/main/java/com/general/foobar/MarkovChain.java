package com.general.foobar;

public class MarkovChain {
    static final class Rational implements Comparable<Rational> {
        private final long num, denom;

        // Default rational number = 0
        public Rational() {
            num = 0;
            denom = 1;
        }

        // Rational number with denominator 1
        public Rational(long num) {
            this(num, 1);
        }

        // Copy constructor for deep copy
        public Rational(Rational other) {
            this(other.num, other.denom);
        }

        // Rational number with numerator and denominator specified
        public Rational(long num, long denom) {
            if (denom == 0) throw new ArithmeticException("Zero denominator not allowed");
            this.num = num;
            this.denom = denom;
        }

        /**
         * Original source: https://jonisalonen.com/2012/converting-decimal-numbers-to-ratios/
         **/
        public Rational(double realNumber) {
            double temp = realNumber;
            realNumber = Math.abs(realNumber);
            double tolerance = 1.0E-6;
            double h1 = 1;
            double h2 = 0;
            double k1 = 0;
            double k2 = 1;
            double b = realNumber;
            do {
                double a = Math.floor(b);
                double aux = h1;
                h1 = a * h1 + h2;
                h2 = aux;
                aux = k1;
                k1 = a * k1 + k2;
                k2 = aux;
                b = 1 / (b - a);
            } while (Math.abs(realNumber - h1 / k1) > realNumber * tolerance);

            this.num = (Double.compare(temp, 0) < 0) ? (long) h1 * -1 : (long) h1;
            this.denom = (long) k1;
        }

        // Helper method to find GCD
        private long gcd(long a, long b) {
            return a == 0 ? b : gcd(b % a, a);
        }

        // Helper method to reduce any rational number to it's lowest terms
        private Rational reduce(Rational number) {
            long gcd = gcd(number.num, number.denom);
            return (gcd == 0) ? new Rational() : new Rational(number.num / gcd, number.denom / gcd);
        }

        public static Rational abs(Rational number) {
            long p = number.num < 0 ? number.num * -1 : number.num;
            long q = number.denom < 0 ? number.denom * -1 : number.denom;
            return new Rational(p, q);
        }

        public static int compare(Rational first, Rational second) {
            return first.compareTo(second);
        }

        // Addition
        public Rational add(Rational other) {
            long p = this.num;
            long q = this.denom;
            long r = other.num;
            long s = other.denom;

            return reduce(new Rational((s * p + r * q), (q * s)));
        }

        public Rational add(double other) {
            return add(new Rational(other));
        }

        // Multiplication
        public Rational multiply(Rational other) {
            long p = this.num;
            long q = this.denom;
            long r = other.num;
            long s = other.denom;

            return reduce(new Rational(p * r, q * s));
        }

        public Rational multiply(double other) {
            return multiply(new Rational(other));
        }

        // Subtraction
        public Rational subtract(Rational other) {
            long p = this.num;
            long q = this.denom;
            long r = other.num;
            long s = other.denom;

            return reduce(new Rational((s * p - r * q), (q * s)));
        }

        public Rational subtract(double other) {
            return subtract(new Rational(other));
        }

        // Division
        public Rational divide(Rational other) {
            long p = this.num;
            long q = this.denom;
            long r = other.num;
            long s = other.denom;
            if (q == 0 || r == 0) throw new ArithmeticException("Divide by zero attempt");

            return reduce(new Rational(p * s, q * r));
        }

        public Rational divide(double other) {
            return divide(new Rational(other));
        }

        @Override
        public String toString() {
            return (this.num + "/" + this.denom);
        }

        @Override
        public int compareTo(Rational other) {
            long p = this.num;
            long q = this.denom;
            long r = other.num;
            long s = other.denom;

            return Long.compare(p * s, r * q);
        }
    }

    /**
     * A matrix class which works with rational numbers
     */
    public static final class Matrix {
        private final Rational[][] matrix;

        // Create a zero matrix by default
        public Matrix(int rows, int cols) {
            Rational[][] matrix = new Rational[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = new Rational();
                }
            }

            this.matrix = matrix;
        }

        // Create a rational matrix from a double matrix
        public Matrix(double[][] m) {
            this.matrix = new Rational[m.length][m[0].length];

            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    this.matrix[i][j] = new Rational(m[i][j]);
                }
            }
        }

        // Create a matrix from a 2D array of rational numbers
        public Matrix(Rational[][] m) {
            this.matrix = m;
        }

        // Copy constructor for a deep copy
        public Matrix(Matrix m) {
            this.matrix = new Rational[m.matrix.length][m.matrix[0].length];
            for (int i = 0; i < this.matrix.length; i++) {
                for (int j = 0; j < this.matrix[0].length; j++) {
                    this.matrix[i][j] = new Rational(m.matrix[i][j]);
                }
            }
        }

        // Get an identity matrix
        public static Matrix getIdentityMatrix(int rows, int cols) {
            Rational[][] identityMatrix = new Rational[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (i == j) identityMatrix[i][j] = new Rational(1);
                    else identityMatrix[i][j] = new Rational(0);
                }
            }

            return new Matrix(identityMatrix);
        }

        // Perform matrix inversion
        public static Matrix invert(Matrix input) {
            int n = input.matrix.length;
            Matrix x = new Matrix(n, n);
            Matrix b = new Matrix(n, n);
            int[] index = new int[n];
            for (int i = 0; i < n; ++i)
                b.matrix[i][i] = new Rational(1);

            Matrix a = performGaussianElimination(input, index);

            for (int i = 0; i < n - 1; ++i)
                for (int j = i + 1; j < n; ++j)
                    for (int k = 0; k < n; ++k)
                        b.matrix[index[j]][k] = b.matrix[index[j]][k].subtract(a.matrix[index[j]][i].multiply(b.matrix[index[i]][k]));

            for (int i = 0; i < n; ++i) {
                x.matrix[n - 1][i] = b.matrix[index[n - 1]][i].divide(a.matrix[index[n - 1]][n - 1]);
                for (int j = n - 2; j >= 0; --j) {
                    x.matrix[j][i] = b.matrix[index[j]][i];
                    for (int k = j + 1; k < n; ++k) {
                        x.matrix[j][i] = x.matrix[j][i].subtract(a.matrix[index[j]][k].multiply(x.matrix[k][i]));
                    }
                    x.matrix[j][i] = x.matrix[j][i].divide(a.matrix[index[j]][j]);
                }
            }

            return x;
        }

        // Perform gaussian elimination on a matrix
        public static Matrix performGaussianElimination(Matrix input, int[] index) {
            Matrix m = new Matrix(input);

            int n = index.length;
            Rational[] c = new Rational[n];
            for (int i = 0; i < index.length; i++) index[i] = i;
            for (int i = 0; i < n; ++i) {
                Rational c1 = new Rational(0);
                for (int j = 0; j < n; ++j) {
                    Rational c0 = Rational.abs(m.matrix[i][j]);
                    if (Rational.compare(c0, c1) > 0) c1 = c0;
                }
                c[i] = c1;
            }

            int k = 0;
            for (int j = 0; j < n - 1; ++j) {
                Rational pi1 = new Rational();
                for (int i = j; i < n; ++i) {
                    Rational pi0 = Rational.abs(m.matrix[index[i]][j]);
                    pi0 = pi0.divide(c[index[i]]);
                    if (Rational.compare(pi0, pi1) > 0) {
                        pi1 = pi0;
                        k = i;
                    }
                }

                int tmp = index[j];
                index[j] = index[k];
                index[k] = tmp;

                for (int i = j + 1; i < n; ++i) {
                    Rational pj = m.matrix[index[i]][j].divide(m.matrix[index[j]][j]);
                    m.matrix[index[i]][j] = pj;
                    for (int l = j + 1; l < n; ++l)
                        m.matrix[index[i]][l] = m.matrix[index[i]][l].subtract(pj.multiply(m.matrix[index[j]][l]));
                }
            }

            return m;
        }

        // Multiply this matrix with another matrix
        public Matrix multiply(Matrix other) {
            int r1 = this.matrix.length;
            int c1 = this.matrix[0].length;
            int c2 = other.matrix[0].length;
            Matrix product = new Matrix(r1, c2);
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c2; j++) {
                    for (int k = 0; k < c1; k++) {
                        product.matrix[i][j] = product.matrix[i][j].add(this.matrix[i][k].multiply(other.matrix[k][j]));
                    }
                }
            }

            return product;
        }

        public Matrix subtract(Matrix other) {
            Matrix difference = new Matrix(this.matrix.length, this.matrix[0].length);
            for (int i = 0; i < difference.matrix.length; i++) {
                for (int j = 0; j < difference.matrix[0].length; j++) {
                    difference.matrix[i][j] = this.matrix[i][j].subtract(other.matrix[i][j]);
                }
            }

            return difference;
        }

        // String representation of this matrix
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (Rational[] rationals : matrix) {
                for (int j = 0; j < matrix[0].length; j++) {
                    sb.append(rationals[j]).append(" ");
                }
                sb.append("\n");
            }

            return sb.toString();
        }
    }
}
