/**
 * 
 */
package my.code.practice.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Harish Krishnan
 * Date 07-16-15
 * 
 * Given coins of certain denominations (infinite supply),
 * find different ways (coins combinations) to get the total amount
 * 
 * Reference: http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 *
 */
public class CoinsCombination {

	public int numWays(int total, int coins[]){
		int matrix[][] = new int[coins.length+1][total+1];
		for(int i=0; i <= coins.length; i++){
			matrix[i][0] = 1;
		}
		for(int i=1; i <= coins.length; i++){
			for(int j=1; j <= total ; j++){
				if(coins[i-1] > j){
					matrix[i][j] = matrix[i-1][j];
				}
				else{
					matrix[i][j] = matrix[i][j-coins[i-1]] + matrix[i-1][j];
				}
			}
		}
		return matrix[coins.length][total];
	}


	public void printSol(int total,int coins[]){
		List<Integer> result = new ArrayList<Integer>();
		printSolUtil(result, total, coins, 0);
	}

	private void printSolUtil(List<Integer> result,int total,int coins[],int pos){
		if(total == 0){
			for(int r : result){
				System.out.print(r + " ");
			}
			System.out.print("\n");
		}
		for(int i=pos; i < coins.length; i++){
			if(total >= coins[i]){
				result.add(coins[i]);
				printSolUtil(result,total-coins[i],coins,i);
				result.remove(result.size()-1);
			}
		}
	}

	public static void main(String args[]){
		CoinsCombination coin = new CoinsCombination();
		int total = 15;
		int coins[] = {3,4,6,7,9};
		System.out.println("Num ways: "+coin.numWays(total, coins));
		coin.printSol(total, coins);
	}

}
