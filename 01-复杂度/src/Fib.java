
public class Fib {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 System.out.println("Hello World ! 侯森魁 ");
	}
	//时间复杂度:O(2^n)
    public  static int fib1(int N) {
        if ( N <= 1) return N;
        return  fib1(N - 1) + fib1(N - 2);

    }
    //时间复杂度:O(n)
    public  static int fib2(int N) {
        if ( N <= 1) return N;
        int first = 0;
        int second = 1;
        for (int i = 0; i < N - 1;i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    //时间复杂度:O(n)
    public  static int fib3(int N) {
        if ( N <= 1) return N;
        int first = 0;
        int second = 1;
        for (int i = 0; i < N - 1;i++) {
            second += first;
            first = second - first;
        }
        return second;
    }

    //时间复杂度:O(n)
    public  static int fib4(int N) {
        if ( N <= 1) return N;
        int first = 0;
        int second = 1;
        //这里会执行N-1次
        while (N-- >1){
            second += first;
            first = second - first;
        }
        return second;
    }
    //时间复杂度:O(1) 菲波那切数列的线性代数解法 --特征方程
    public  static int fib5(int n) {
        double c = Math.sqrt(5);
        return (int)((Math.pow((1+c) / 2, n)- Math.pow((1-c) / 2, n)) / c);
    }

}
