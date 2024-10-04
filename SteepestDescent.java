import java.util.Stack;

public class SteepestDescent {

    public static void main(String[] args) {
        double[] initValue = new double[2];
        initValue[0] = 1.15;
        initValue[1] = 1.15;
        System.out.println("Norm: " + normOf(gradient(f_x(initValue[0], initValue[1]), f_y(initValue[0], initValue[1]))));
        System.out.println("Gradient Vector: <" + gradient(f_x(initValue[0], initValue[1]), f_y(initValue[0], initValue[1]))[0] + ", " + gradient(f_x(initValue[0], initValue[1]), f_y(initValue[0], initValue[1]))[1] + ">" );
    }

    public void steepestDescent(double[] initValue, int acceptedTolerance) {
        Stack<Double[]> x = new Stack<>();
        //x.push( [1.15, 1.15] );

        int k = 0;
        /*while ( normOf( gradient( x.peek() ) ) > acceptedTolerance ) {
            x.push(x.peek() - chooseStepSize(i) * gradient( x.peek() ) );
            i++;
        }*/

    }

    public static double chooseStepSize(int i) {
        return 0.0d;


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
        double[] vector = new double[2];
        vector[0] = f_x;
        vector[1] = f_y;
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
        int whichFunction = 1;
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
                /* iv)  f(x,y) = (1 - x)² + 400(y-x²)² */
                return Math.pow(1 - x, 2) + 400*Math.pow(y - Math.pow(x, 2), 2);  
            default:
                return 0.0;
        }
    }

}