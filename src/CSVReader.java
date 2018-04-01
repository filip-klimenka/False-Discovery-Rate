import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class CSVReader {

    int length;
    int width;
    ArrayList<String> uniqueTickers;

    public final int PERMNO = 0;
    public final int DATE = 1;
    public final int TICKER = 2;
    public final int PRICE = 3;
    public final int VOLUME = 4;
    public final int RETURN = 5;

    private ArrayList<Integer> permnos;
    private ArrayList<Integer> dates;
    private ArrayList<String> tickers;
    private ArrayList<Double> prices;
    private ArrayList<Double> volumes;
    private ArrayList<Double> returns;


    public CSVReader(String path)
    {
        permnos = new ArrayList<Integer>();
        dates = new ArrayList<Integer>();
        tickers = new ArrayList<String>();
        prices = new ArrayList<Double>();
        volumes = new ArrayList<Double>();
        returns = new ArrayList<Double>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try
        {
            br = new BufferedReader(new FileReader(path));
            br.readLine();  // skip first line

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] tokens = line.split(cvsSplitBy);

                permnos.add(Integer.valueOf(tokens[PERMNO]));
                dates.add(Integer.valueOf(tokens[DATE]));
                tickers.add(String.valueOf(tokens[TICKER]));
                prices.add(Double.valueOf(tokens[PRICE]));
                volumes.add(Double.valueOf(tokens[VOLUME]));
                returns.add(Double.valueOf(tokens[RETURN]));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                    length = permnos.size();
                    mapToArrays();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }


    }


    private HashMap<String, ArrayList<Double>> Prices;
    private HashMap<String, ArrayList<Double>> Volumes;
    private HashMap<String, ArrayList<Double>> Returns;
    private HashMap<String, ArrayList<Integer>> Dates;
    private HashMap<String, ArrayList<Integer>> Permnos;


    public void mapToArrays()
    {
        Prices = new HashMap<>();
        Volumes = new HashMap<>();
        Returns = new HashMap<>();
        Dates = new HashMap<>();
        Permnos = new HashMap<>();
        uniqueTickers = new ArrayList<>();


        for (int i = 0; i < length; i++)
        {
            String ticker = tickers.get(i);

            if (Prices.get(ticker) != null)
            {
                Prices.get(ticker).add(prices.get(i));
                Volumes.get(ticker).add(volumes.get(i));
                Returns.get(ticker).add(returns.get(i));
                Dates.get(ticker).add(dates.get(i));
                Permnos.get(ticker).add(permnos.get(i));
            }
            else
            {
                uniqueTickers.add(ticker);
                Prices.put(ticker, new ArrayList<>());
                Volumes.put(ticker, new ArrayList<>());
                Returns.put(ticker, new ArrayList<>());
                Dates.put(ticker, new ArrayList<>());
                Permnos.put(ticker, new ArrayList<>());
            }
        }
    }



    public HashMap<String, ArrayList<Integer>> getPermnos() {
        return Permnos;
    }

    public HashMap<String, ArrayList<Integer>> getDates() {
        return Dates;
    }

    public ArrayList<String> getTickers() {
        return uniqueTickers;
    }

    public HashMap<String, ArrayList<Double>> getPrices() {
        return Prices;
    }

    public HashMap<String, ArrayList<Double>> getVolumes() {
        return Volumes;
    }

    public HashMap<String, ArrayList<Double>> getReturns() {
        return Returns;
    }

    public static void main(String args[])
    {
        String path = "H:\\coding\\java\\stocks_data.csv";
        CSVReader reader = new CSVReader(path);

        ArrayList<String> tickers = reader.getTickers();
        System.out.println("Number of stocks = " + tickers.size());
        String ticker1 = tickers.get(0);
        System.out.println("1st stock is " + ticker1);

//        for (double p : reader.getPrices())
//            System.out.println(p);
    }
}
