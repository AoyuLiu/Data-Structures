cmake_minimum_required(VERSION 3.10)
project(Merge_Sort)

set(CMAKE_CXX_STANDARD 11)

add_executable(Merge_Sort main.cpp InsertionSort.h SortTestHelper.h MergeSort.h)