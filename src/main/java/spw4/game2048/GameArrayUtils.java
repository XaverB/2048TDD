package spw4.game2048;

public class GameArrayUtils {

  public static void moveZerosToRight(int arr[], int n) {

    // Count of non-zero elements
    int count = 0;

    // Traverse the array. If arr[i] is non-zero, then
    // update the value of arr at index count to arr[i]
    for (int i = 0; i < n; i++)
      if (arr[i] != 0)
        arr[count++] = arr[i];

    // Update all elements at index >=count with value 0
    for (int i = count; i<n;i++)
      arr[i]=0;
  }

  public static void mergeValuesLeft(int arr[], int n){
    for (int i = n-1; i >= 0; i++) {
      if(i > 0){
        if(arr[i] == arr[i-1]){
          arr[i] = arr[i] * 2;
          arr[i-1] = 0;
        }
      }
    }
  }

  public static void mergeValuesRight(int arr[], int n){
    for (int i = 0; i < n-1; i++) {
      if(i < n-1){
        if(arr[i] == arr[i+1]){
          arr[i] = arr[i] * 2;
          arr[i+1] = 0;
        }
      }
    }
  }

  public static void moveZerosToLeft(int arr[], int n) {

    // Count of non-zero elements
    int count = n-1;

    // Traverse the array. If arr[i] is non-zero, then
    // update the value of arr at index count to arr[i]
    for (int i = n-1; i >= 0; i--)
      if (arr[i] != 0)
        arr[count--] = arr[i];

    // Update all elements at index >=count with value 0
    for (int i = count; i>= 0;i--)
      arr[i]=0;
  }

}
