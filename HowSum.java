import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * 
 */
public class HowSum {


    /**
     * The function should return an array containing any combination of
     * elements that add up to exactly the targetSum. If there is no
     * combination that adds up to the targetSUm, then return null.
     * 
     * If there are multiple combinations possible, you may return any
     * single one.
     * 
     * m = targetSum
     * n = numbers.length
     * Time: O(n^m * m)  Space: O(m)
     */
    static int[] howSum0(int targetSum, int[] numbers) {

        // **** base case(s) ****
        if (targetSum == 0) return new int[0];
        if (targetSum < 0 ) return null;

        // **** initialization ****
        int[] arr = null;

        // **** loop making recursive call ****
        for (int i = 0; i < numbers.length && arr == null; i++) {

            // **** remove this number from the target sum ****
            int rem = targetSum - numbers[i];

            // **** recursive call ****
            arr = howSum0(rem, numbers);

            // **** add this number to the array ****
            if (arr != null) {

                // **** create and populate new array ****
                int[] tmp = new int[arr.length + 1];
                for (int j = 0; j < arr.length; j++)
                    tmp[j] = arr[j];
                tmp[arr.length] = numbers[i];

                // **** replace array ****
                arr = tmp;
            }
        }

        // **** return int[] ****
        return arr;
    }


    /**
     * The function should return an array containing any combination of
     * elements that add up to exactly the targetSum. If there is no
     * combination that adds up to the targetSUm, then return null.
     * 
     * If there are multiple combinations possible, you may return any
     * single one.
     * 
     * Entry point for recursive call.
     * 
     * m = targetSum
     * n = numbers.length
     * Time: O(n * m^2)  Space: O(m^2) 
     */
    static int[] howSum(int targetSum, int[] numbers) {

        // **** sanity check(s) ****
        if (targetSum == 0) return new int[0];
        
        // **** initialization ****
        HashMap<Integer, List<Integer>> memo = new HashMap<>();

        // **** start recursive call ****
        List<Integer> lst = howSum(targetSum, numbers, memo);

        // **** return int[] array ****
        return lst == null ? null : lst.stream().mapToInt(i -> i).toArray();
    }


    /**
     * The function should return an array containing any combination of
     * elements that add up to exactly the targetSum. If there is no
     * combination that adds up to the targetSUm, then return null.
     * 
     * If there are multiple combinations possible, you may return any
     * single one.
     * 
     * Recursive call.
     */
    static List<Integer> howSum(int targetSum,
                                int[] numbers,
                                HashMap<Integer, List<Integer>> memo) {

        // **** base case(s) ****
        if (targetSum == 0) return new ArrayList<Integer>();
        if (targetSum < 0 ) return null;

        // **** initialization ****
        List<Integer> lst = null;

        // **** loop making recursive call(s) ****
        for (int i = 0; i < numbers.length && lst == null; i++) {

            // **** remove this number from the target sum ****
            int rem = targetSum - numbers[i];

            // **** check if in memo (recursive call) ****
            if (!memo.containsKey(rem))
                memo.put(rem, howSum(rem, numbers, memo));

            // **** get list from memo ****
            lst = memo.get(rem);

            // **** add this number to the list ****
            if (lst != null)
                lst.add(numbers[i]);
        }

        // **** return lst ****
        return lst;
    }


    /**
     * Test scaffold.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read target sum ****
        int targetSum = Integer.parseInt(br.readLine().trim());

        // **** read array of integers ****
        int[] numbers = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** close buffered reader ****
        br.close();
        
        // ???? ????
        System.out.println("main <<< targetSum: " + targetSum);
        System.out.println("main <<< numbers: " + Arrays.toString(numbers));

        // **** call function of interest ****
        int[] arr = howSum0(targetSum, numbers);

        // **** display result ****
        System.out.println("main <<< arr: " + Arrays.toString(arr));

        // **** call function of interest ****
        arr = howSum(targetSum, numbers);

        // **** display result ****
        System.out.println("main <<< arr: " + Arrays.toString(arr));
    }
}