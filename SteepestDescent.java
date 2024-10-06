import java.util.Scanner;

public class SteepestDescent {
    int whichFunction = 1;

    public static void main(String[] args) {
        Double[] initValue = {1.15, 1.15};
        double acceptedTolerance = Math.pow(10, -9);

        int functionNo = whichFunction(new Scanner(System.in));
        int a_kType = chooseStepSize(new Scanner(System.in));

        steepestDescent(initValue, acceptedTolerance, functionNo, a_kType);
    }

    public static void steepestDescent(Double[] initValue, double acceptedTolerance, int functionNo, int a_kType) {
        double k = 0.0, a_k = 0.0;
        Double[] x = initValue;
        double[] gradient = new double[2];

        /* if you wish, you can change the iteration limit. */
        /* beware that changing it, could increase the time it takes to get an answer. */
        /* with a bigger number the program might get stuck and a fast computer might be required. */
        /* default: 10³ (1000)  */
        int interationLimit = (int) Math.pow(10, 3);

        System.out.println("k\tx_k\t\t\t<Fx, Fy>\t\t||<Fx, Fy>||\n");
        
        while ( (normOf(gradient(f_x(x[0],x[1], functionNo), f_y(x[0],x[1], functionNo))) > acceptedTolerance) && (k < interationLimit)) {
            
            /* Choose (stepsize) a_k > 0 */
            /* small stepsize = it takes longer to converge */
            /* big stepsize = it takes less time to converge */
            /* depending on the chosen stepsize, it can overshoot the desired x_k and diverge! */
            /* default: a_k = stepSize(a_kType, k) */
            a_k = stepSize(a_kType, k);

            /* Calculate <Fx(x_k), Fy(x_k)> */
            gradient = gradient(f_x(x[0],x[1], functionNo), f_y(x[0],x[1], functionNo));

            System.out.print(Math.round(k) + "\t");
            System.out.print("(" + String.format("%.2E", ((double) Math.round( x[0] * 100 ) / 100)) + ", " 
                                + String.format("%.2E", ((double) Math.round( x[1] * 100 ) / 100)) + ")\t");
            System.out.print("<" + String.format("%.2E", ((double) Math.round(gradient[0] * 100) / 100)) + ", " 
                                + String.format("%.2E", ((double) Math.round(gradient[1] * 100) / 100)) + ">\t");
            System.out.println( String.format("%.2E", ((double) Math.round(normOf(gradient) * 100) / 100)) + "\n");

            /* Set x_k = x_k - a_k * <Fx(x_k), Fy(x_k)> */
            x[0] = x[0] - a_k * gradient[0];
            x[1] = x[1] - a_k * gradient[1];

            k++;

        }

            /* Printing k + 1 */
            gradient = gradient(f_x(x[0],x[1], functionNo), f_y(x[0],x[1], functionNo));
            System.out.print(Math.round(k) + "\t");
            System.out.print("(" + String.format("%.2E", ((double) Math.round( x[0] * 100 ) / 100)) + ", " 
                                + String.format("%.2E", ((double) Math.round( x[1] * 100 ) / 100)) + ")\t");
            System.out.print("<" + String.format("%.2E", ((double) Math.round(gradient[0] * 100) / 100)) + ", " 
                                + String.format("%.2E", ((double) Math.round(gradient[1] * 100) / 100)) + ">\t");
            System.out.println( String.format("%.2E", ((double) Math.round(normOf(gradient) * 100) / 100)) + "\n");

    }

    static int whichFunction(Scanner sc) {

        System.out.println("Which function would you like to use?");
        System.out.println("\t1) f(x,y) = 16x² + 4y² + 2x - 4y - 4");
        System.out.println("\t2) f(x,y) = sin(xy) + 81x² + 16y²");
        System.out.println("\t3) f(x,y) = sqrt(x² - 4x + y² - 6y + 13) + 4");
        System.out.println("\t4) f(x,y) = (1 - x)² + 400(y-x²)² Rosenbrock Function\n");
        System.out.print("Enter 1, 2, 3 or 4: ");

        int functionNo = 0; 
        Boolean correctNo = false;

        while (!correctNo) {
            functionNo = Integer.parseInt(sc.nextLine());
            switch (functionNo) {
                case 1:
                    System.out.println();
                    correctNo = true;
                    return 1;

                case 2:
                    System.out.println();
                    correctNo = true;
                    return 2;

                case 3:
                    System.out.println();
                    correctNo = true;
                    return 3;

                case 4:
                    System.out.println();
                    correctNo = true;
                    return 4;
            
                default:
                    System.out.println("Invalid Input!\n");
                    System.out.print("Enter 1, 2, 3 or 4: ");
                    break;
            }
        }
        
        return functionNo;
    }

    static int chooseStepSize(Scanner sc) {

        System.out.println("Choose the stepsize (a_k):");
        System.out.println("\t1) constant a = 1");
        System.out.println("\t2) constant a = 0.1");
        System.out.println("\t3) constant a = 0.001");
        System.out.println("\t4) variable a_k = 1/k");
        System.out.print("\nEnter 1, 2, 3 or 4: ");

        int a_kType = 0; 
        Boolean correctNo = false;
        
        while (!correctNo) {
            a_kType = Integer.parseInt(sc.nextLine());
            switch (a_kType) {
                case 1:
                    System.out.println();
                    correctNo = true;
                    return 1;

                case 2:
                    System.out.println();
                    correctNo = true;
                    return 2;

                case 3:
                    System.out.println();
                    correctNo = true;
                    return 3;

                case 4:
                    System.out.println();
                    correctNo = true;
                    return 4;
            
                default:
                    System.out.println("Invalid Input!\n");
                    System.out.print("Enter 1, 2, 3 or 4: ");
                    break;
            }
        }

        return 0;
    }

    static double stepSize(int a_kType, double k) {
     
        switch (a_kType) {
            case 1:
                return 1;

            case 2:
                return 0.1;

            case 3:
                return 0.001;

            case 4:
                return 1/(k + 1);
        
            default:
                break;
        }

        return 0.0;
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
    public static double f_x(double x, double y, int whichFunction) {
        double h = Math.pow(10, -9);
        double result = (function(x+h,y, whichFunction)-function(x,y, whichFunction))/h;

        switch (whichFunction) {
            case 1:
                /* i)   Fx = 32x + 2*/
                result = 32*x + 2;
                break;

            case 2:
                /* ii)  Fx = ycos(xy) + 162x */
                result = y*Math.cos(x*y) + 162*x;
                break;

            case 3:
                /* iii) Fx = (x - 2) / sqrt(x² - 4x + y² - 6y + 13)  */
                result = (x - 2) / Math.sqrt(Math.pow(x, 2) - 4*x + Math.pow(y, 2) - 6*y + 13);
                break;

            case 4:
                /* iv)  Fx = 2(800x³ - 800xy + x - 1) */
                result = 2*(800*Math.pow(x, 3) - 800*x*y + x - 1);
                break;  

            default:
                result = 0.0;
                break;
        }

        /* returns the rounded result */
        return (double) Math.round( result * 100 ) / 100; 
    }

    /* Fy = lim (h->0) (f(x,y+h)-f(x,y))/h */
    public static double f_y(double x, double y, int whichFunction) {
        double h = Math.pow(10, -9);
        double result = (function(x,y+h, whichFunction)-function(x,y, whichFunction))/h;

        switch (whichFunction) {
            case 1:
                /* i)   Fy = 8y - 4 */
                result = 8*y - 4;
                break;

            case 2:
                /* ii)  Fy = xcos(xy) + 32y */
                result = x*Math.cos(x*y) + 32*y;
                break;

            case 3:
                /* iii) Fy = (y - 3) / sqrt(x² - 4x + y² - 6y + 13) */
                result = (y - 3) / Math.sqrt(Math.pow(x, 2) - 4*x + Math.pow(y, 2) - 6*y + 13);
                break;

            case 4:
                /* iv)  Fy = 800*(y - x²)*/
                result = 800*(y - Math.pow(x, 2));
                break;

            default:
                result = 0.0;
                break;
        }

        /* returns the rounded result */
        return (double) Math.round( result * 100 ) / 100;
    }

    /* /*******************************************************************\ */
    /*                              NOT IN USE                               */
    
    /* f(x,y) */
    public static double function(double x, double y, int whichFunction) {
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
                /* iv)  f(x,y) = (1 - x)² + 400(y-x²)² Rosenbrock Function*/
                return Math.pow(1 - x, 2) + 400*Math.pow(y - Math.pow(x, 2), 2);  
            default:
                return 0.0;
        }
    }

}