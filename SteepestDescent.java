public class SteepestDescent {
    int whichFunction = 1;

    public static void main(String[] args) {
        Double[] initValue = {1.15, 1.15};
        double acceptedTolerance = Math.pow(10, -9);

        steepestDescent(initValue, acceptedTolerance);
    }

    public static void steepestDescent(Double[] initValue, double acceptedTolerance) {
        double k = 1.0, a_k = 0.0;
        Double[] x = initValue;
        double[] gradient = new double[2];

        System.out.println("k \t x_k \t <Fx, Fy> \t ||<Fx, Fy>||\n");
        
        while ( (normOf(gradient(f_x(x[0],x[1]), f_y(x[0],x[1]))) > acceptedTolerance) && (k < 1000)) {
            
            /* Choose a_k > 0 (stepsize) */
            a_k = 1/k;

            /* Calculate <Fx(x_k), Fy(x_k)> */
            gradient = gradient(f_x(x[0],x[1]), f_y(x[0],x[1]));

            System.out.print(Math.round(k) + "\t");
            System.out.print("(" + ((double) Math.round( x[0] * 100 ) / 100) + ", " + ((double) Math.round( x[1] * 100 ) / 100) + ")\t");
            System.out.print("<" + ((double) Math.round(gradient[0] * 100) / 100) + ", " + ((double) Math.round(gradient[1] * 100) / 100) + ">\t");
            System.out.println(normOf(gradient) + "\n");

            /* Set x_k = x_k - a_k * <Fx(x_k), Fy(x_k)> */
            x[0] = x[0] - a_k * gradient[0];
            x[1] = x[1] - a_k * gradient[1];

            k++;

        }

    }

    /* ||<Fx,Fy>|| = sqrt(Fx^2+Fy^2) */
    public static double normOf(double[] vector) {
        double f_x = vector[0];
        double f_y = vector[1];
        double result = Math.sqrt(Math.pow(f_x, 2) + Math.pow(f_y, 2)); 
        /* returns the rounded result */
        return (double) Math.round( result * 100 ) / 100;
    }

    /* <Fx,Fy> */
    public static double[] gradient(double f_x, double f_y) {
        double[] vector = {f_x, f_y};
        return vector;
    }

    /* Fx = lim (h->0) (f(x+h,y)-f(x,y))/h */
    public static double f_x(double x, double y) {
        double h = Math.pow(10, -9);
        double result = (function(x+h,y)-function(x,y))/h;
        /* returns the rounded result */
        return (double) Math.round( result * 100 ) / 100; 
    }

    /* Fy = lim (h->0) (f(x,y+h)-f(x,y))/h */
    public static double f_y(double x, double y) {
        double h = Math.pow(10, -9);
        double result = (function(x,y+h)-function(x,y))/h;
        /* returns the rounded result */
        return (double) Math.round( result * 100 ) / 100;
    }

    /* f(x,y) */
    public static double function(double x, double y) {
        int whichFunction = 2;
        switch (whichFunction) {
            case 1:
                /* i)   f(x,y) = 16x² + 4y² + 2x - 4y - 4 */
                return 16*Math.pow(x, 2) + 4*Math.pow(y, 2) + 2*x - 4*y - 4;
            case 2:
                /* ii)  f(x,y) = sin(xy) + 81x² + 16y² */
                return Math.sin(x*y) + 81*Math.pow(x, 2) + 16*Math.pow(y, 2);
            case 3:
                /* iii) f(x,y) = sqrt(x² - 4x + y² - 6y + 13) + 4 */
                return Math.sqrt(Math.pow(x, 2) - 4*x + Math.pow(y, 2) - 6*y + 13) + 4;
            case 4:
                /* iv)  f(x,y) = (1 - x)² + 400(y-x²)² Rosenbrock's Function*/
                return Math.pow(1 - x, 2) + 400*Math.pow(y - Math.pow(x, 2), 2);  
            default:
                return 0.0;
        }
    }

}