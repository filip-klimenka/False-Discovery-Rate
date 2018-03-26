import java.util.Stack;

public class Bootstrap {
    // data parameters
    int n;
    double x[];
    // bootstrap repetitions
    int B = 1000;
    // container for bootstrapped distribution
    double[] bsample;
    
    public Bootstrap(double x[])
    {
        this.x = x;
        this.n = x.length;
    }

    // Functional interface to pass bootstrap a function
    interface BootstrapFunc {
        double computeStatistic(double data[]);
    }


    public double[] getIIDindex()
    {
        // Uniform random numbers over 1...n.
        int u[] = Stats.simulateUniform(n, n);
        // x-star sample simulation
        double xStar[] = new double[n];
        for (int i : u)
            xStar[i] = x[i];
        return xStar;
    }

    public double[] getStationaryIndex()
    {
        double xStar[] = new double[n];
        int w = 10;     // block size
        int[] indices = new int[n];
        // the code below makes block sizes in indices
        // exponentially distributed with mean m
        indices[0] = Stats.randomGenerator.nextInt(n-1);
        for (int i = 1; i < n; i++)
        {
            double rw = Stats.randomGenerator.nextDouble();
            if (rw < (1.0 / w))
                indices[i] = Stats.randomGenerator.nextInt(n-1);
            else
                indices[i] = indices[i-1] + 1;
                if (indices[i] > (n-1))
                    indices[i] = indices[i] - (n-1);
        }
        // get values associated with indices
        for (int i : indices)
            xStar[i] = x[i];
        return xStar;
    }


    public double[] IID(BootstrapFunc bf)
    {
        // placeholder for bootstrap statistics
        bsample = new double[B];
        for (int b = 0; b < B; b++)
        {
            double xStar[]  = getIIDindex();
            bsample[b] = bf.computeStatistic(xStar);
        }
        return bsample;
    }

    public double[] Stationary(BootstrapFunc bf)
    {
        // placeholder for bootstrap statistics
        bsample = new double[B];
        for (int b = 0; b < B; b++)
        {
            double xStar[]  = getStationaryIndex();
            bsample[b] = bf.computeStatistic(xStar);
        }
        return bsample;
    }

    public static void main(String[] args)
    {
        int n = 100;
        double[] x = Stats.simulateNormal(n);
        Bootstrap bs = new Bootstrap(x);
        double muStar[] = bs.IID(Stats :: getMean);
        System.out.println("IID bootstrap mean is " + Stats.getMean(muStar));
        double muStar2[] = bs.Stationary(Stats :: getMean);
        System.out.println("Stationary bootstrap mean is " + Stats.getMean(muStar2));
    }
}
