import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class CSVReader {

    public final int PERMNO = 0;
    public final int DATE = 1;
    public final int TICKER = 2;
    public final int PRICE = 3;
    public final int VOLUME = 4;
    public final int RETURNS = 5;

    private ArrayList<Integer> permno;
    private ArrayList<Integer> dates;
    private ArrayList<String> tickers;
    private ArrayList<Double> prices;
    private ArrayList<Double> volumes;
    private ArrayList<Double> returns;


    public CSVReader(String path)
    {
        permno = new ArrayList<Integer>();
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

                permno.add(Integer.valueOf(tokens[PERMNO]));
                dates.add(Integer.valueOf(tokens[DATE]));
                tickers.add(String.valueOf(tokens[TICKER]));
                prices.add(Double.valueOf(tokens[PRICE]));
                volumes.add(Double.valueOf(tokens[VOLUME]));

///*                for (String s : tokens)
//                {
//                    System.out.print(s + ", ");
//                }
//                System.out.println();*/
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
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }


    }

    public ArrayList<Integer> getPermno() {
        return permno;
    }

    public ArrayList<Integer> getDates() {
        return dates;
    }

    public ArrayList<String> getTickers() {
        return tickers;
    }

    public ArrayList<Double> getPrices() {
        return prices;
    }

    public ArrayList<Double> getVolumes() {
        return volumes;
    }

    public ArrayList<Double> getReturns() {
        return returns;
    }

    public static void main(String args[])
    {
        String path = "H:\\coding\\java\\stocks_data.csv";
        CSVReader reader = new CSVReader(path);

        for (Double p : reader.getPrices())
            System.out.println(p);
    }
}
