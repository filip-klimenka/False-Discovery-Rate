import java.util.ArrayList;
import java.util.HashSet;

public class Series<Index, Values> {

    ArrayList<Index> index;
    ArrayList<Values> values;
    int length;

    public Series(ArrayList<Index> index, ArrayList<Values> values) throws Exception
    {
        // check 1: equivalent length of index and values
        if (index.size() != values.size())
            throw new Exception("index and values have different sizes");

        // check 2: uniqueness of indices
        if (new HashSet<>(index).size() != index.size())
            throw new Exception("index values are not unique");

        this.index = index;
        this.values = values;
        length = index.size();
    }

    public static void main(String[] args)
    {
        String path = "H:\\coding\\java\\stocks_data.csv";
        CSVReader reader = new CSVReader(path);

        try {
            Series<Integer, Double> price_series = new Series(reader.getDates(), reader.getPrices());
        } catch (Exception e){
            e.printStackTrace();
        }


//        System.out.println(price_series);
//        for (Double v : price_series.values)
//            System.out.println(v);

    }


}
