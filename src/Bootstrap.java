import java.util.Random;

public class Bootstrap {
    // random generator for bootstrap
    static Random randomGenerator = new Random();
    // generate some pseudo data
    int n = 100;
    double[] data = simulateNormal(n);

    //double theta1 = randomGenerator.nextDouble ();

    public double[] simulateNormal(int size)
    {
        double[] normal = new double[size];
        for (int i = 0; i < size; i++)
            normal[i] = randomGenerator.nextGaussian();
        return normal;
    }

    public int[] simulateUniform(int size, int K)
    {
        int[] uniform = new int[size];
        for (int i = 0; i < size; i++)
            uniform[i] = randomGenerator.nextInt(K);
        return uniform;
    }

    public static void main(String[] args)
    {
        Bootstrap b = new Bootstrap();
        double[] a = b.simulateNormal(10);
        //for (double i : a)
        //    System.out.println(i);

        int[] u = b.simulateUniform(10, 4);
        for (int i : u)
            System.out.println(i);
    }
    /**
     *n = 100; x = randn(n,1);
     % Mean of x
     mu = mean(x);
     B = 1000;
     % Initialize muStar
     muStar = zeros(B,1);
     % Loop over B bootstraps
     for b=1:B
     % Uniform random numbers over 1...n
     u = ceil(n*rand(n,1));
     % x-star sample simulation
     xStar = x(u);
     % Mean of x-star
     muStar(b) = mean(xStar);
     end
     s2 = 1/(n-1)*sum((x-mu).^2);
     stdErr = s2/n
     bootstrapStdErr = mean((muStar-mu).^2)
     */



}
