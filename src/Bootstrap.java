
public class Bootstrap {
    // helper functions
    static Stats stats = new Stats();
    // generate some pseudo data
    int n = 100;
    double[] x = stats.simulateNormal(n);
    // bootstrap repetitions
    int B = 1000;


    public double[] getIIDBootstrapSample()
    {
        // Uniform random numbers over 1...n
        int u[] = stats.simulateUniform(n, n);
        // x-star sample simulation
        double xStar[] = new double[n];
        for (int i : u)
            xStar[i] = x[i];
        return xStar;
    }

    public double[] getIIDBootstrapMean()
    {
        // place holder for bootstrapped means
        double muStar[] = new double[B];
        for (int b = 0; b < B; b++)
        {
            double xStar[]  = getIIDBootstrapSample();
            muStar[b] = stats.getMean(xStar);
        }
        return muStar;
    }

    public static void main(String[] args)
    {
        Bootstrap b = new Bootstrap();
        double muStar[] = b.getIIDBootstrapMean();
//        for (double m : muStar)
//            System.out.println(m);
        System.out.println("IID bootstrap mean is " + stats.getMean(muStar));
//        Stats stats = new Stats();
    }







}
