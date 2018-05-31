//
// Created by 刘傲宇 on 2018/5/31.
//

#ifndef QUICK_SORT_THREE_WAYS_QUICKSORT_H
#define QUICK_SORT_THREE_WAYS_QUICKSORT_H


#include <iostream>
#include <ctime>
#include <algorithm>
#include "InsertionSort.h"

using namespace std;

template <typename T>
int _partition(T arr[], int l, int r){

    swap( arr[l] , arr[rand()%(r-l+1)+l] );

    T v = arr[l];
    int j = l;
    for( int i = l + 1 ; i <= r ; i ++ )
        if( arr[i] < v ){
            j ++;
            swap( arr[j] , arr[i] );
        }

    swap( arr[l] , arr[j]);

    return j;
}

// 双路快速排序的partition
template <typename T>
int _partition2(T arr[], int l, int r){


    swap( arr[l] , arr[rand()%(r-l+1)+l] );
    T v = arr[l];

    int i = l+1, j = r;
    while( true ){

        while( i <= r && arr[i] < v )
            i ++;

        while( j >= l+1 && arr[j] > v )
            j --;

        if( i > j )
            break;

        swap( arr[i] , arr[j] );
        i ++;
        j --;
    }

    swap( arr[l] , arr[j]);

    return j;
}


template <typename T>
void _quickSort(T arr[], int l, int r){

    // 对于小规模数组, 使用插入排序进行优化
    if( r - l <= 15 ){
        insertionSort(arr,l,r);
        return;
    }

    // 调用双路快速排序的partition
    int p = _partition2(arr, l, r);
    _quickSort(arr, l, p-1 );
    _quickSort(arr, p+1, r);
}

template <typename T>
void quickSort(T arr[], int n){

    srand(time(NULL));
    _quickSort(arr, 0, n-1);
}




#endif //QUICK_SORT_THREE_WAYS_QUICKSORT_H
