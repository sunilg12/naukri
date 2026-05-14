import java.util.ArrayList;

/******************************************************************************

 Welcome to GDB Online.
 GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
 C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
 Code, Compile, Run and Debug online from anywhere in world.

 *******************************************************************************/

//Given an array nums of distinct integers, return all the possible permutations.
//You can return the answer in any order.
//
//
//
//        Example 1:
//
//Input: nums = [1,2,3]
//Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

public class Main
{
    public static void main(String[] args) {
//        System.out.println("Hello World");

        int [] nums = {1,2,3};

        ArrayList<Integer> ans = new ArrayList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();


        res = permutations(nums, 0, ans, res);

        for(ArrayList<Integer> arr : res){
            for(int num : arr){
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static  ArrayList<ArrayList<Integer>> permutations(int[] nums, int idx, ArrayList<Integer> ans,
                                    ArrayList<ArrayList<Integer>> res){

        if(idx >= nums.length){
            res.add(new ArrayList<>(ans));
            return res;
        }

        for(int i=0; i < nums.length; i++){

            if(i != idx) continue;

            ans.add(nums[i]);
            permutations(nums, i+1, ans, res);
            ans.remove(ans.size()-1);
        }

        return res;
    }
}
