package com.raylinetech.ssh2.logistics.common.util;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.entity.Sku;

public class SortUtil {   
   /**  
      * @param pData 需要排序的数组  
      * @param left  左边的位置,初始值为0  
      * @param right 右边的位置,初始值为数组长度  
      */   
     public static void QuickSort(int[] pData,int left,int right)   
     {   
       int i,j;   
       int first,temp;   
       i = left;   
       j = right;   
       first = pData[left]; //这里选其他的数也行，不过一般选第一个  
       //一趟快速排序   
       while(true)   
       {   
       //从第二个数开始找大于中枢的数 ,从前面开始找大于pData[left]的数  
           while((++i)<right-1 && pData[i]<first);   
           //从最后一个数开始找第一个小于中枢pData[left]的数   
           while((--j)>left && pData[j]>first);   
           if(i>=j)   
               break;   
           //交换两边找到的数   
           temp = pData[i];   
           pData[i] = pData[j];   
           pData[j] = temp;   
    
       }   
       //交换中枢   
       pData[left] = pData[j];   
       pData[j] = first;   
       //递归快排中枢左边的数据   
       if(left<j)   
         QuickSort(pData,left,j);   
       //递归快排中枢右边的数据   
       if(right>i)   
         QuickSort(pData,i,right);   
     }   
     
     public static void QuickSortSku(Sku[] skus,int left,int right)   
     {   
       int i,j;   
       Sku first = null;
       Sku temp = null;
       i = left;   
       j = right;   
       first = skus[left]; //这里选其他的数也行，不过一般选第一个  
       //一趟快速排序   
       while(true)   
       {   
       //从第二个数开始找大于中枢的数 ,从前面开始找大于pData[left]的数  
           while((++i)<right-1 && skus[i].getWeight()<first.getWeight());   
           //从最后一个数开始找第一个小于中枢pData[left]的数   
           while((--j)>left && skus[j].getWeight()>first.getWeight());   
           if(i>=j)   
               break;   
           //交换两边找到的数   
           temp = skus[i];   
           skus[i] = skus[j];   
           skus[j] = temp;   
       }   
       //交换中枢   
       skus[left] = skus[j];   
       skus[j] = first;   
       //递归快排中枢左边的数据   
       if(left<j)   
    	   QuickSortSku(skus,left,j);   
       //递归快排中枢右边的数据   
       if(right>i)   
    	   QuickSortSku(skus,i,right);   
     }   
     
     public static void QuickSortItem(RLOrderItem[] items,int left,int right)   
     {   
       int i,j;   
       RLOrderItem first = null;
       RLOrderItem temp = null;
       i = left;   
       j = right;   
       first = items[left]; //这里选其他的数也行，不过一般选第一个  
       //一趟快速排序   
       while(true)   
       {   
       //从第二个数开始找大于中枢的数 ,从前面开始找大于pData[left]的数  
           while((++i)<right-1 && items[i].getSku().getWeight()<first.getSku().getWeight());   
           //从最后一个数开始找第一个小于中枢pData[left]的数   
           while((--j)>left && items[j].getSku().getWeight()>first.getSku().getWeight());   
           if(i>=j)   
               break;   
           //交换两边找到的数   
           temp = items[i];   
           items[i] = items[j];   
           items[j] = temp;   
       }   
       //交换中枢   
       items[left] = items[j];   
       items[j] = first;   
       //递归快排中枢左边的数据   
       if(left<j)   
    	   QuickSortItem(items,left,j);   
       //递归快排中枢右边的数据   
       if(right>i)   
    	   QuickSortItem(items,i,right);   
     }   
//    public static List<RLOrder> getRLOrders(){
//    	
//    }
     
     
     public static void main(String[] args){   
      
        int [] pData = new int[5];   
        for(int i = 0; i< 5; i++)   
             pData[i] = (int)(Math.random()*100);//Produce 10 random integers   
          
         for(int i = 0; i<pData.length; i++){   
             System.out.print(pData[i]+" ");    
         }  
         SortUtil.QuickSort(pData, 0, pData.length);   
          
         System.out.println("\n***********************");   
          
         for(int i = 0; i<pData.length; i++){   
             System.out.print(pData[i]+" ");   
         }  
     }    
}  