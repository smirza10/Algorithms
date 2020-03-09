//Samran Mirza
public class MidtermPractice
{
   public static int [][] memo;
   public static void main(String[] args)
   {
      String str1 = "alksdjhaksj";
      String str2 = "ajsdhjkasd";
      int n;
      n = LCS(str1,str2);
      System.out.println("Longest common string length in " + str1 + " and " + str2 + " : " + n);
      n = lcsRec(0, 0, str1, str2);
      System.out.println("Longest common string length in " + str1 + " and " + str2 + " using recursive : " + n);
      str1 = "racecar";
      str2 = "racecars";
      System.out.println(str1 + " is a palindrome: " + isPalindrome(str1, 0, str1.length() - 1));
      System.out.println(str2 + " is a palindrome: " + isPalindrome(str2, 0, str2.length() - 1)); 
      int [] arr = {1, 4, 6, 3, 2, 5, 7, 9, 8};
      System.out.print("Array before quicksort:");
      for(int i = 0; i < arr.length; i++)
      {
         System.out.print(" " + arr[i]);
      }
      System.out.println();
      quickSort(arr, 0, arr.length -1);
      System.out.print("Array after quicksort:");
      for(int i = 0; i < arr.length; i++)
      {
         System.out.print(" " + arr[i]);
      }
      System.out.println();
      arr = new int [] {1, 4, 6, 3, 2, 5, 7, 9, 8};
      System.out.print("Array before mergesort:");
      for(int i = 0; i < arr.length; i++)
      {
         System.out.print(" " + arr[i]);
      }
      System.out.println();
      mergeSort(arr);
      System.out.print("array after mergesort:");
      for(int i = 0; i < arr.length; i++)
      {
         System.out.print(" " + arr[i]);
      }
      System.out.println();
      int [] value = {0,3, 6, 4, 6};
      int [] weight = {0,1,3,2,2};
      n = 5;
      int maxVal = knapSack(n, weight, value);
      System.out.println("Maxmium value of knapsack with weight of 6: " + maxVal);
      n = 6;
      int k = 4;
      memo = new int [n + 1][k + 1];
      int b = bc(n,k);
      System.out.println("Binomial coefficient of " + n + ", " + k + " is: " + b);
      arr = new int [] { 1, 9, 10, 13, 17, 18, 22, 25, 35, 40, 45};
      n = 35;
      System.out.print("current array:");
      for(int i = 0; i < arr.length; i++)
      {
         System.out.print(" " + arr[i]);
      }
      System.out.println();
      System.out.println("Does array contain " + n + ": " + binarySearch(arr, n, 0, arr.length -1));
  
      
   }
   //Longest common string
   public static int LCS(String a, String b)
   {
      int count = 0;
      int [][] arr = new int [a.length() + 1][b.length() +1];
      for(int i = a.length(); i >= 0; i--)
      {
         for(int j = b.length(); j >= 0; j--)
         {
            if(i == a.length() || j == b.length())
               arr[i][j] = 0;
            else if(a.charAt(i) == b.charAt(j))
               arr[i][j] = 1 + arr[i+1][j+1];
            else
               arr[i][j] = Math.max(arr[i+1][j],arr[i][j+1]);   
         }
      }
      return (arr[0][0]);
   }
   // Palindrome
   public static boolean isPalindrome(String a, int low, int high)
   {
      if(low >= high)
         return true;
      else if(a.charAt(low) != a.charAt(high))
         return false;
      else
         return isPalindrome(a, low + 1, high -1);      
   }
   //Quicksort
   public static void quickSort(int [] arr, int low,int high)
   {
      if( low < high)
      {
         int p = partition(arr, low, high);
         quickSort(arr, low, p -1);
         quickSort(arr, p + 1, high);
      }
      return;   
   }
   public static int partition(int [] arr,int low,int high)
   {
      int pivot = arr[high];
      int i = low - 1;
      int temp;
      for(int j = low; j < high; j++)
      {
         if(arr[j] < pivot)
         {
            i++;
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
         }
      }
      i++;
      temp = arr[i];
      arr[i] = pivot;
      arr[high] = temp;
      return i;
   }
   //Mergesort
   public static int[] mergeSort(int [] a)
   {
      if(a.length < 2)
         return a;
      int [] first = new int[a.length / 2];
      int [] last = new int[a.length - first.length];
      int j = 0;
      for(int i = 0; i< a.length; i++)
      {
         if(i < first.length)
            first[i] = a[i];
         else
         {
            last[j] = a[i];
            j++;
         }   
      }
      
      mergeSort(first);
      mergeSort(last);
      int [] arr = merge(first, last, a);   
      return arr;   
   }
   //Merge method for mergeSort
   public static int[] merge(int [] first, int [] last, int [] result)
   {
      int i = 0;
      int j = 0;
      for(int k = 0; k < result.length; k++)
      {
         if(i < first.length && j < last.length)
         {
            if(first[i] <= last[j])
            {
               result[k] = first[i];
               i++;
            }
            else 
            {
               result[k] = last[j];
               j++;
            }
         }
         else if(i < first.length)
         {
            result[k] = first[i];
            i++;
         }
         else
         {
            result[k] = last[j];
            j++;
         }  
      }
      return result;
      
   }
   //Binomial coefficent recursive
   public static int bc(int n, int k)
   {
      if(n == k || k == 0)
         return 1;
      if(memo[n][k] != 0) return memo[n][k];
      int temp = bc(n -1, k-1) + bc(n -1, k);
      memo[n][k] = temp;
      return memo[n][k];   
   }
   //Knapsack problem, maximum value that can be carried with n weight
   public static int knapSack(int n, int [] weight , int [] val)
   {
      int [][] result = new int [val.length][n + 1];
      int i;
      int j = 0;
      for(i = 0; i < result.length; i++)
      {
         for(j = 0; j < result[i].length; j++)
         {
            if(i == 0 || j == 0)
               result[i][j] = 0;
            else if(weight[i] <= j)
            {
               result[i][j] = Math.max(val[i] + result[i - 1][j - weight[i]], result[i -1][j] );
            }
            else
               result[i][j] = result[i-1][j];   
         }
      }
      return result[i-1][j -1];
   }
   //LCS recursive
   public static int lcsRec(int i, int j, String a, String b)
   {
      if(i == a.length() || j == b.length())
         return 0;
      else if(a.charAt(i) == b.charAt(j))
         return 1 + lcsRec(i + 1, j + 1, a, b);
      else
      {
         int x = lcsRec(i + 1, j, a, b);
         int y = lcsRec(i, j+1, a, b);
         return(Math.max(x,y));
      }      
   }
   //Binary Search
   public static boolean binarySearch(int [] arr, int k, int low, int high )
   {
   
      while(low <= high)
      {
         int mid = (high - low) / 2 + low;
         if(arr[mid] == k)
            return true;
         else if(arr[mid] > k)
            high = mid - 1;
         else
            low = mid + 1;   
      }
      return false;
   }
}