import java.util.Random;

public class Bootstrap {
    // random generator for bootstrap
    static Random randomGenerator = new Random();
    // generate some pseudo data
    int n = 100;
    double[] x = simulateNormal(n);
    // bootstrap repetitions
    int B = 1000;

    public double[] simulateNormal(int size)
    {
        double[] normal = new double[size];
        for (int i = 0; i < size; i++)
            normal[i] = randomGenerator.nextGaussian();
        return normal;
    }

    public int[] simulateUniform(int size, int N)
    {
        int[] uniform = new int[size];
        for (int i = 0; i < size; i++)
            uniform[i] = randomGenerator.nextInt(N);
        return uniform;
    }

    public double getMean(double[] a)
    {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum / a.length;
    }

    public double[] getIIDBootstrapSample()
    {
        // Uniform random numbers over 1...n
        int u[] = simulateUniform(n, n);
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
            muStar[b] = getMean(xStar);
        }
        return muStar;
    }

    public static void main(String[] args)
    {
        Bootstrap b = new Bootstrap();
        double muStar[] = b.getIIDBootstrapMean();
//        for (double m : muStar)
//            System.out.println(m);
        System.out.println("IID bootstrap mean is " + b.getMean(muStar));
    }







}
