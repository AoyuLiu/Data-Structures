#include <iostream>
#include <algorithm>
#include <ctime>
#include "SortTestHelper.h"
#include "MergeSort.h"
#include "InsertionSort.h"

using namespace std;


template<typename T>
int __partition(T arr[], int l , int r){
    //随机数错误
    swap( arr[l] , arr[rand()%(r-l+1)+l] );

    T v = arr[l];
    //j 为 分界点  arr[l+1...j] < v    arr[j+1...i-1]> v
    int j=l;
    for(int i =l+1; i<=r ; i++)
        if(arr[i] < v){
         swap(arr[j+1],arr[i]);
         j++;
        }
     swap(arr[l] , arr[j]);

    return j;
}

// 二路排序
template<typename T>
int __partition2(T arr[], int l, int r){

    swap(arr[l], arr[rand()%(r-l+1)+l] );
    T v = arr[l];
    // arr[i+1...i] <= v ; arr(j..r] >= v
    int i = l+1, j = r;
    while(true){

        // 为什么不写 arr[i] <= v 因为当重复元素多的时候, 会加重一侧,导致俩颗子树的不平衡
        // 最后等于的情况,左右互换平摊了重复元素
        while( i <= r && arr[i] < v)
            i++;
        while(j>=l+1 && arr[j]>v)
            j--;

        if (i > j)
            break;

        swap(arr[i], arr[j]);
        i ++;
        j --;
    }
    swap(arr[l], arr[j]);

    return j;
}





template<typename T>
void __quickSort(T arr[], int l, int r){

    if(r - l <= 15){
        insertionSort(arr,l,r);
        return;
    }


    int p = __partition2(arr,l,r);
    __quickSort(arr,l,p-1);
    __quickSort(arr,p+1,r);
}

template<typename T>
void quickSort(T arr[], int n){
    srand(time(NULL));
    __quickSort(arr,0,n-1);
}


int main() {
    int n = 1000000;

    // 测试1 一般性测试
    cout<<"Test for random array, size = "<<n<<", random range [0, "<<n<<"]"<<endl;
    int* arr1 = SortTestHelper::generateRandomArray(n,0,n);
    int* arr2 = SortTestHelper::copyIntArray(arr1,n);

    SortTestHelper::testSort("Merge Sort", mergeSort, arr1, n);
    SortTestHelper::testSort("Quick Sort", quickSort, arr2, n);

    delete[] arr1;
    delete[] arr2;

    cout<<endl;


    // 测试2 测试近乎有序的数组
    int swapTimes = 100;
    cout<<"Test for nearly ordered array, size = "<<n<<", swap time = "<<swapTimes<<endl;
    arr1 = SortTestHelper::generateNearlyOrderedArray(n,swapTimes);
    arr2 = SortTestHelper::copyIntArray(arr1, n);

    SortTestHelper::testSort("Merge Sort", mergeSort, arr1, n);
    SortTestHelper::testSort("Quick Sort", quickSort, arr2, n);

    delete[] arr1;
    delete[] arr2;

    cout<<endl;


    // 测试3 测试存在包含大量相同元素的数组
    // 使用双快速排序后, 我们的快速排序算法可以轻松的处理包含大量元素的数组
    cout<<"Test for random array, size = "<<n<<", random range [0,10]"<<endl;
    arr1 = SortTestHelper::generateRandomArray(n,0,10);
    arr2 = SortTestHelper::copyIntArray(arr1, n);

    SortTestHelper::testSort("Merge Sort", mergeSort, arr1, n);
    SortTestHelper::testSort("Quick Sort", quickSort, arr2, n);

    delete[] arr1;
    delete[] arr2;


    return 0;
}