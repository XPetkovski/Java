
public class PrimeExample {
	    static boolean checkPrime(int n) {
	        if (n < 0) { 
	            throw new RuntimeException("Number should not be negative"); //Node A
	        }
	        int m = n / 2; //Node B
	        if (n == 0 || n == 1) {
	            return false; //Node C
	        } else {
	            for (int i = 2; i <= m; i++) { //Node D
	                if (n % i == 0) {
	                    return true; //Node E
	                }
	            }
	            return false; //Node F
	        }
	    }
}
