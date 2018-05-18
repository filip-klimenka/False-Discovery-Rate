import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StepM {

    /**
     * Need operations of deletion and removal on those
     * */
    HashSet<String> A;     // Active set
    HashSet<String> S;     // Superior set



    double alpha = 0.05;    // Significance level
    int B = 1000;           // Bootstrap repetitions
    HashMap<String, Double> delta_bar;
    HashMap<String, Double> delta_star;


    int k;
    ArrayList<Double> T_k;   // placeholder for bootstrapped statistics T_kb for step k
    double T_kb;            // value to be stored
    double quantile_k;      // empirical (1 - alpha) quantile of T_kb

    double getTk()
    {
        return T_kb;
    }


//    public void getSuperiorModels()
//    {
//        while (true)
//        {
//            if (A.isEmpty())
//                break;
//
//            ArrayList<Double> T_k = new ArrayList<>();
//
//            // get T_k
//            for (int b = 0; b < B; b++)
//            {
//                delta_star = getDeltaStar();
//                T_kb = getTk(); // Max of delta star
//                T_k.add(T_kb);
//            }
//
//            quantile_k = getQuantile(T_k, 1 - alpha);
//
//            if (true)//(maxActive() < quantile_k)
//                break;
//            else
//            {
//                for (String m : A)
//                {
//                    if (delta_bar.get(m) >= quantile_k)
//                    {
//                        S.add(m);       // Adding a model to Superior set
//                        A.remove(m);    // and removing it from Active set
//                        k++;
//                    }
//                }
//            }
//        }
//    }

}
