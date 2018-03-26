

public class Bootstrap {
    // data
    int n;
    double x[];
    // bootstrap repetitions
    int B = 1000;
    // container for boostrapped distribution
    double[] bsample;
    
    public Bootstrap(double x[])
    {
        this.x = x;
        this.n = x.length;
    }


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
        /**
         *   w = 10
             indices = np.zeros(P, dtype=np.int64)
             indices[0] = np.random.randint(low = 0, high = P-1)
             for i in range(1, P):
             if np.random.uniform() < (1 / w):
             indices[i] = np.random.randint(low = 0, high = P-1)
             else:
             indices[i] = indices[i-1] + 1
             indices[indices > (P-1)] = indices[indices > (P-1)] - (P-1)
             return indices
         * */
        return xStar;
    }


    public double[] IID(BootstrapFunc bf)
    {
        // place holder for bootstrapped means
        bsample = new double[B];
        for (int b = 0; b < B; b++)
        {
            double xStar[]  = getIIDindex();
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
//        for (double m : muStar)
//            System.out.println(m);
        System.out.println("IID bootstrap mean is " + Stats.getMean(muStar));
//        Stats stats = new Stats();
    }
}
