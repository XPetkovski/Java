 class TwoThreads {
    /*public static class ThreadAB implements Runnable
    {
        String s1;
        String s2;

        public ThreadAB(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public void run() {
            System.out.println(s1);
            System.out.println(s2);
        }
    }
    public static void main(String[] args) {
        ThreadAB th1 = new ThreadAB("A", "B");
        ThreadAB th2 = new ThreadAB("1", "2");

        Thread threadOne = new Thread(th1);
        Thread threadTwo = new Thread(th2);

        threadOne.start();
        threadTwo.start();
    }*/
	 public class PrimeExample {
		    static boolean checkPrime(int n) {
		        if (n < 0) {
		            throw new RuntimeException("Number should not be negative");
		        }
		        int m = n / 2;
		        if (n == 0 || n == 1) {
		            return false;
		        } else {
		            for (int i = 2; i <= m; i++) {
		                if (n % i == 0) {
		                    return true;
		                }
		            }
		            return false;
		        }
		    }
		}
}