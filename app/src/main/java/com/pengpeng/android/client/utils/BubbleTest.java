package com.pengpeng.android.client.utils;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title NN
 * @Package com.pengpeng.android.client.utils
 * @Description:
 * @date 2017/2/17 10:22
 */

public class BubbleTest
{
    public static int binary(int[] array, int value)
    {
        int low = 0;
        int high = array.length - 1;
        while(low <= high)
        {
            int middle = (low + high) / 2;
            if(value == array[middle])
            {
                return middle;
            }
            if(value > array[middle])
            {
                low = middle + 1;
            }
            if(value < array[middle])
            {
                high = middle - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args)
    {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int value = binary(a, 9);
        System.out.println(value);
    }
}