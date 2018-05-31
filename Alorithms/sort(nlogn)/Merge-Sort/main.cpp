#include <iostream>
#include "SortTestHelper.h"
#include "InsertionSort.h"
#include "MergeSort.h"

using namespace std;

template<typename T>
void __mergeSort2(T arr[], int l, int r){
    if (r-l <= 15){
        insertionSort(arr,l,r);
        return;
    }

    int mid = (l+r)/2;
    __mergeSort2(arr, l , mid);
    __mergeSort2(arr,mid+1,r);

    //排序好不用排
    if(arr[mid] > arr[mid+1])
        __merge(arr,l,mid,r);
}

template<typename T>
void mergeSort2(T arr[] , int n){

    __mergeSort2( arr , 0 , n-1);
}



int main() {

    int n = 50000;

    // 测试1 一般性测试
    cout<<"Test for random array, size = "<<n<<", random range [0, "<<n<<"]"<<endl;
    int* arr1 = SortTestHelper::generateRandomArray(n,0,n);
    int* arr2 = SortTestHelper::copyIntArray(arr1, n);
    int* arr3 = SortTestHelper::copyIntArray(arr1, n);

    SortTestHelper::testSort("Insertion Sort", insertionSort, arr1, n);
    SortTestHelper::testSort("Merge Sort",     mergeSort,     arr2, n);
    SortTestHelper::testSort("Merge Sort 2",   mergeSort2,    arr3, n);

    delete[] arr1;
    delete[] arr2;
    delete[] arr3;

    cout<<endl;


    // 测试2 测试近乎有序的数组

    int swapTimes = 10;
    assert( swapTimes >= 0 );

    cout<<"Test for nearly ordered array, size = "<<n<<", swap time = "<<swapTimes<<endl;
    arr1 = SortTestHelper::generateNearlyOrderedArray(n,swapTimes);
    arr2 = SortTestHelper::copyIntArray(arr1, n);
    arr3 = SortTestHelper::copyIntArray(arr1, n);

    SortTestHelper::testSort("Insertion Sort", insertionSort, arr1, n);
    SortTestHelper::testSort("Merge Sort",     mergeSort,     arr2, n);
    SortTestHelper::testSort("Merge Sort 2",   mergeSort2,    arr3, n);

    delete[] arr1;
    delete[] arr2;
    delete[] arr3;

    return 0;

}