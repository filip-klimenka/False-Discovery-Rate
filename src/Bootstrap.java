

public class Bootstrap {
    // helper functions
    //static Stats stats = new Stats();
    // generate some pseudo data
    int n = 100;
    double[] x = Stats.simulateNormal(n);
    // bootstrap repetitions
    int B = 1000;


    public double[] getIIDBootstrapSample()
    {
        // Uniform random numbers over 1...n
        int u[] = Stats.simulateUniform(n, n);
        // x-star sample simulation
        double xStar[] = new double[n];
        for (int i : u)
            xStar[i] = x[i];
        return xStar;
    }

    public double[] getStationaryBootstrapSample()
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


    public double[] getIIDBootstrapMean()
    {
        // place holder for bootstrapped means
        double muStar[] = new double[B];
        for (int b = 0; b < B; b++)
        {
            double xStar[]  = getIIDBootstrapSample();
            muStar[b] = Stats.getMean(xStar);
        }
        return muStar;
    }

    public static void main(String[] args)
    {
        Bootstrap bs = new Bootstrap();
        double muStar[] = bs.getIIDBootstrapMean();
//        for (double m : muStar)
//            System.out.println(m);
        System.out.println("IID bootstrap mean is " + Stats.getMean(muStar));
//        Stats stats = new Stats();
    }







}
