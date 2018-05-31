cmake_minimum_required(VERSION 3.10)
project(Quick_Sort_Three_Ways)

set(CMAKE_CXX_STANDARD 11)

add_executable(Quick_Sort_Three_Ways main.cpp InsertionSort.h MergeSort.h QuickSort.h SortTestHelper.h)