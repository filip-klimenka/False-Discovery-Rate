import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DataFrame<Index, Value> {
    ArrayList<Index> index;
    HashMap<String, ArrayList<Value>> values;
    ArrayList<String> columns;
    int length;

    public DataFrame(ArrayList<Series<Index, Value>> seriesArrayList) {
        extractColumns(seriesArrayList);
        extractIndex(seriesArrayList);
        extractValues(seriesArrayList);

    }

    private void extractColumns(ArrayList<Series<Index, Value>> series) {
        columns = new ArrayList<>();
        for (Series<Index, Value> s : series) {
            columns.add(s.name);
        }
    }

    private void extractIndex(ArrayList<Series<Index, Value>> series) {
        ArrayList<ArrayList<Index>> indices = new ArrayList<>();

        // extract all the indices
        for (Series<Index, Value> s : series) {
            indices.add(s.index);
        }

        // check that all the indices are identical
        ArrayList<Index> refIndex = indices.get(0);  // get reference index

        // 1st check: see if all the indices are of the same length
        try {
            for (int i = 1; i < indices.size(); i++) {
                int indexSize = indices.get(i).size();
                if (refIndex.size() != indexSize) {
                    throw new Exception("Indices lengths are not the same");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2nd check: check each index row by row for each columns
        try {
            for (int row = 0; row < refIndex.size(); row++) {
                for (int col = 1; col < indices.size(); col++) {
                    if (refIndex.get(row) != indices.get(col).get(row)) {
                        throw new Exception("Indices are not the same");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // if these checks are passed set index of the DataFrame to the reference index
        this.index = refIndex;
        this.length = refIndex.size();

    }


    private void extractValues(ArrayList<Series<Index, Value>> series) {
        values = new HashMap<>();
        for (Series <Index, Value> s  : series) {
            values.put(s.name, s.values)
        }
    }


    public static void main(String[] args) throws Exception {
        String path = "H:\\coding\\java\\stocks_data.csv";
        CSVReader reader = new CSVReader(path);

        // DataFrame prices = new DataFrame(reader.getDates(), reader.getPrices(), reader.uniqueTickers);
    }


    public void getRow(Index index)
    {}

    public void getRow(Index i1, Index i2)
    {}

    public void get(String col)
    {}

    public void get(String[] cols)
    {}







    /**
     * DataFrame<Index, Value> loc(Index i1, Index i2)
     *
     * DataFrame<Index, Value> get(String[] columns)
     *
     * Add to String function
     *
     * */




}
