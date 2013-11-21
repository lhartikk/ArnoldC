public class myClass {


    public static void main(String args[]) {

        fib(8);
    }

    static int fib(int a) {
        if (a < 2) return a;
        else if(a == 2)
        {
            return a;
        }
        return fib(a - 1) + fib(a - 2);
    }

}
