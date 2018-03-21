import java.util.Random;

public class Stats {

    /**
     * Basic descriptive statistics functions
     *
     * getMean()
     * getMedian()
     * getMin()
     * getMax()
     * getVariance()
     *
     * Simulate arrays of different distributions
     *
     * simulateNormal()
     * simulateUniform()
     * */

    // random generator for bootstrap
    static Random randomGenerator = new Random(26031974);

    public double getMean(double[] a)
    {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum / a.length;
    }

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

}
