import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	        SharedResource resource = new SharedResource();
        HashSet<Thread> threads = new HashSet<>();
        for (int i=0;i<5;i++)
        {
            threads.add((new ThreadA(resource)));
            threads.add((new ThreadB(resource)));
        }

        for(Thread thread : threads)
        {
            thread.start();
        }

        for(Thread thread : threads)
        {
            thread.join();
        }

        int finalCounter = resource.getCounter();

        System.out.println(finalCounter);

    }

}

class Locks
{
    public static Semaphore semaphore0 = new Semaphore(0);
    public static Semaphore semaphore1= new Semaphore(1);
    public static Semaphore semaphore2 = new Semaphore(2);
}

class SharedResource
{
    private  int counter;
    public SharedResource()
    {
        this.counter = 0;
    }
    public  void increment()
    {
        this.counter++;
    }
    public int getCounter()
    {
        return counter;
    }
}

class ThreadA extends Thread
{
    private SharedResource resource;
    public ThreadA(SharedResource resource)
    {
        this.resource = resource;
    }


    @Override
    public void run() {
        try
        {
            this.execute();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void  execute() throws InterruptedException
    {
        Locks.semaphore0.acquire();
        System.out.println("A");
        Locks.semaphore1.release();

        Locks.semaphore2.acquire();
        this.resource.increment();
        Locks.semaphore2.release();
    }

}

class ThreadB extends Thread
{
    private SharedResource resource;
    public ThreadB(SharedResource resource)
    {
        this.resource = resource;
    }


    @Override
    public void run() {
        try
        {
            this.execute();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void  execute() throws InterruptedException
    {
        Locks.semaphore1.acquire();
        System.out.println("B");
        Locks.semaphore0.release();

        Locks.semaphore2.acquire();
        this.resource.increment();
        Locks.semaphore2.release();
    }

}
