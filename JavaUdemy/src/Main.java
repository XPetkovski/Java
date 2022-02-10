

public class Main {

    public static void main(String[] args)
    {
            int[] intArray = { 20, 35, -15, 7,55, 1,-22};

        for(int i=0;i<intArray.length;i++)
        {
            System.out.println(intArray[i]);
        }
//            for(int lastunsortedindex =intArray.length-1;lastunsortedindex>0;lastunsortedindex--)
//            {
//                for(int i=0;i<lastunsortedindex;i++)
//                {
//                    if(intArray[i]<intArray[i+1])
//                    {
//                        swap(intArray, i, i+1);
//                    }
//                }
//            } // BUBBLE SORT

//            for(int lastunsortedindex = intArray.length-1;lastunsortedindex>0;lastunsortedindex--)
//            {
//                int largestindex = 0;
//
//                for(int i=1;i<=lastunsortedindex;i++)
//                {
//                    if(intArray[i]>intArray[largestindex])
//                    {
//                        largestindex = i;
//
//                    }
//                }
//                swap(intArray, largestindex, lastunsortedindex);
//            } // SELECTION SORT

//              for(int firstunsorted=1; firstunsorted<intArray.length;firstunsorted++)
//              {
//                  int newElement = intArray[firstunsorted];
//
//                  int i;
//                  for(i=firstunsorted;i>0 && intArray[i-1]>newElement;i--)
//                  {
//                      intArray[i] = intArray[i-1];
//                  }
//                  intArray[i] = newElement;
//              } // INSERTION SORT
        



    }

//    public static void swap ( int [] array , int i , int j)
//    {
//        if(i == j)
//        {
//            return;
//        }
//        int temp = array[i];
//        array[i] = array[j];
//        array[j] = temp;
//    }
}
