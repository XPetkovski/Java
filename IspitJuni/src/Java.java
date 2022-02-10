import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// TODO: 8/30/20 Worker Node must be a Thread
        class WorkerNode extends Thread{
            private SharedResource resource;
            public WorkerNode(SharedResource resource)
            {
                this.resource = resource;
            }
            private int [] array;
            private int [] sharedResults;
            private int workerIndex;

            public WorkerNode(int arr[], int[] sharedResults, int workerIndex) {
                this.array = arr;
                this.sharedResults = sharedResults;
                this.workerIndex = workerIndex;
            }

            public void run() {
                int sum = 0;
                // TODO: 8/30/20 Compute sum of elements in array
                for(int i=0; i<array.length;i++)
                {
                    sum += array[i];
                }

                // Write result to shared memory
                sharedResults[workerIndex] = sum;

                // TODO: 8/30/20 Print sum
                System.out.println(sum);
            }
        }


// TODO: 8/30/20 Core Node must be a Thread
        class CoreNode extends Thread{

            private SharedResource resource;
            public CoreNode(SharedResource resource)
            {
                this.resource = resource;
            }

            private int [] array;

            public CoreNode(int arr[]) {
                this.array = arr;
            }

            public void run() throws InterruptedException {
                int [] sharedIntermediateResults = {0, 0, 0, 0};
                List<WorkerNode> workerNodeList = new ArrayList();

                for(int i = 0, workerIndex=0; i < array.length; i += 5, workerIndex++) {
                    int[] slice = Arrays.copyOfRange(array, i, i + 5);
                    WorkerNode workerNode = new WorkerNode(slice, sharedIntermediateResults, workerIndex);
                    // TODO: 8/30/20 Run worker node in a separate thread
                    @Override
                    public void run()
                    {
                        try
                        {
                            WorkerNode.activeCount();
                        }
                        catch(InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    }

                }

                // TODO: 8/30/20 Wait for all worker nodes to finish


                // TODO: 8/30/20 Compute and print sum
                // TODO: 8/30/20  Compute and print remainder of division with 5
            }

            public int getSum(int[] array) {
                int totalSum = 0;
                for (int sharedResult : array) {
                    totalSum += sharedResult;
                }
                return totalSum;
            }

        }

        public class DistributedSum2 {

            public static void main(String[] args) throws InterruptedException {
                int [] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2};

                // TODO: 8/30/20 Start a Core Node in a new Thread
                SharedResource resource = new SharedResource();
                HashSet<Thread> threads = new HashSet<>();
                for (int i=0;i<5;i++)
                {
                    threads.add((new WorkerNode(resource)));
                    threads.add((new CoreNode(resource)));
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
