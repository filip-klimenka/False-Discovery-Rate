import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DataFrame<Index, Value> {
    ArrayList<Index> index;
    HashMap<String, ArrayList<Value>> values;
    ArrayList<String> columns;
    int length;


//    public DataFrame(HashMap<String, ArrayList<Index>> Indices, HashMap<String,
//                     ArrayList<Value>> values, ArrayList<String> columns) throws Exception
//    {
//        checkEqualIndex(Indices);
//
//        // set columns
//        this.columns = columns;
//        // set rows
//        this.index = Indices.get(columns.get(0));
//        // set length
//        length = this.index.size();
//    }

    public DataFrame(ArrayList<Series<Index, Value>> seriesArrayList) {
        extractColumns(seriesArrayList);
        extractIndex(seriesArrayList);
        extractValues(seriesArrayList);
        extactLength(seriesArrayList);

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

    }


    private void extractValues() {
        values = new HashMap<>();
//        for (int i  : columns) {
//            values.put(c, series.)
//        }
    }










    public void checkEqualIndex(HashMap<String, ArrayList<Index>> Indices) throws Exception
    {

        // get keys
        Set keys_set = Indices.keySet();
        String keys[] = (String[]) keys_set.toArray(new String[keys_set.size()]);
        int keys_length = keys.length;

        // get reference index
        String reference_key = keys[0];
        ArrayList<Index> reference_index = Indices.get(reference_key);

        for (int i = 1; i < keys_length; i++)
        {
            String key_to_compare = keys[i];
            if (!isEqualIndex(Indices.get(reference_key), Indices.get(key_to_compare)))
            {
                int size1 = Indices.get(reference_key).size();
                int size2 = Indices.get(key_to_compare).size();
                System.out.println("sizes are not the same: " + size1 + " vs " + size2);
                throw new Exception("Indices are not the same");
            }
        }
        System.out.println("Indices are identical");
    }

    public boolean isEqualIndex(ArrayList<Index> index1, ArrayList<Index> index2)
    {
        int index1_length = index1.size();

        if (index1_length != index2.size())
            return false;

        for (int i = 0; i < index1_length; i++)
        {
            if (!index1.get(i).equals(index2.get(i)))
            {
                System.out.println(index1.get(i) + " != " + index2.get(i));
                return false;
            }
        }
        return true;
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
