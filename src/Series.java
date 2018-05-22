import java.util.ArrayList;
import java.util.HashSet;

public class Series<Index, Value> {

    ArrayList<Index> index;
    ArrayList<Value> values;
    String name;
    int length;

    public Series(ArrayList<Index> index, ArrayList<Value> values, String name) throws Exception
    {
        // check 1: equivalent length of index and values
        if (index.size() != values.size())
            throw new Exception("index and values have different sizes");

        // check 2: uniqueness of indices
        if (new HashSet<>(index).size() != index.size())
            throw new Exception("index values are not unique");

        this.index = index;
        this.values = values;
        this.name = name;
        length = index.size();
    }

    public static void main(String[] args) throws Exception
    {
        String path = "H:\\coding\\java\\stocks_data.csv";
        CSVReader reader = new CSVReader(path);
        // get ticker
        String ticker = reader.getPrices().keySet().toArray()[0].toString();
        // get prices
        ArrayList<Double> prices = reader.getPrices().get(ticker);
        // get dates
        ArrayList<Integer> dates = reader.getDates().get(ticker);
        // create series
        Series<Integer, Double> price_series = new Series(dates, prices, ticker);
        // printing prices
        System.out.println(price_series);
        for (Double v : price_series.values)
            System.out.println(v);


    }


}
