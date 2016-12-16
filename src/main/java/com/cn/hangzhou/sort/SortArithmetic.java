package com.cn.hangzhou.sort;

/**
 * http://blog.csdn.net/zgrjkflmkyc/article/details/11639091
 * http://blog.csdn.net/xiazdong/article/details/8462393
 * 几种常见排序算法总结（java版）
 * @author Administrator
 *
 */
public class SortArithmetic {
	
	public static void main(String args[]) {
		
//	    int ordata[] = {12,13,27,34,38,49,65,76,78,97};
	    int indata[] = {49,38,65,76,97,13,27,78,34,12}; 
	    int bubdata[] = {49,38,65,76,97,13,27,78,34,12};
	    int heapdata[] = {49,38,65,76,97,13,27,78,34,12};
	    System.out.println("-----------------数据------------------");  
		System.out.print("排序前是:");  
		for(int i=0; i<= indata.length-1; i++){  
			System.out.print(indata[i]+" ");  
		}  
		System.out.println();  
	    insort(indata);				  //直接插入排序
	    bubsort(bubdata);			 //冒泡排序
	    heapsort(heapdata);			//堆排序

	}

	/*-------------------------直接插入排序法-------------------------------- 
    	像是玩朴克一样，我们将牌分作两堆，每次从后面一堆的牌抽出最前端的牌，然后插入前面一堆牌的适当位置 
	-----------------------------------------------------------------*/  
	public static void insort(int data[]){  
		int i, j, temp;  
		long start,end;  
		  
		start = System.nanoTime();  
		int length = data.length;
		for(j = 1; j < length; j++) {  
		    temp = data[j];  
		    i = j - 1;  
		    while(temp < data[i]) {  
		        data[i+1] = data[i];  
		        i--;  
		        if(i == -1) {  
		            break;  
		        }  
		    }  
		    data[i+1] = temp;  
		}  
		end = System.nanoTime();  
		  
		System.out.println("-----------------插入排序法------------------");  
		System.out.print("排序后是:");  
		for(i=0; i<= length-1; i++){  
		    System.out.print(data[i]+" ");  
		}  
		System.out.println();  
		System.out.println("排序使用时间："+(end-start)+" ns");  
	}  
	
	 /*--------------------------------冒泡排序法---------------------------------------- 
	          顾名思义，就是排序时，最大的元素会如同气泡一样移至右端，其利用比较相邻元素的方法，将大的元素交换至右端， 
	    所以大的元素会不断的往右移动，直到适当的位置为止。 
            基本的气泡排序法可以利用旗标的方式稍微减少一些比较的时间，当寻访完阵列后都没有发生任何的交换动作， 
	    表示排序已经完成，而无需再进行之后的回圈比较与交换动作。 
	----------------------------------------------------------------------------------------*/  
	public static void bubsort(int data[]){  
		int i, j,temp, flag = 1;  
		long start,end;  
		
		start = System.nanoTime();  
		int length = data.length; 
		int count = 0;
		for(i = 0; i < length-1 && flag == 1; i++) {  
			flag = 0;  
			for(j = 0; j < length-i-1; j++) {  
				if(data[j+1] < data[j]) {  
				    temp = data[j+1];  
				    data[j+1] = data[j];  
				    data[j] = temp;  
				    flag = 1;  
				}  
			}  
			count++;
		}  
		end = System.nanoTime();  
		
		System.out.println(count);
		
		System.out.println("-----------------冒泡排序法------------------");  
		System.out.print("排序后是:");  
		for(i=0; i<=length-1; i++){  
			System.out.print(data[i]+" ");  
		}  
		System.out.println();  
		System.out.println("排序使用时间："+(end-start)+" ns");  
	}  
	
	/*-----------------------heap排序（堆排序法--改进的选择排序）---------------------------- 
        利用堆积树的原理，先构造一个堆积树（看堆积树的定义，笔记本上有），然后将根节点与最后的叶子节点交换，并屏蔽掉最后一个叶子节点， 
        然后再将未被屏蔽的部分重新构造堆积树，然后再重复上面的步骤，直到所有的数被按顺序排好。 
	--------------------------------------------------------------------------------*/  
	public static void heapsort(int number[]) {  
		int i, m, p, s, temp;  
		long start,end;  
		  
		start=System.nanoTime();  
		int length = number.length;
		int number_temp[] = new int[length+1];  
		for(int temp_i = 1; temp_i < length + 1; temp_i++){  
		    number_temp[temp_i] = number[temp_i-1];  
		}  
		createheap(number_temp);  
		m = length;  
		while(m > 1) {  
			//交换
		    temp = number_temp[1];  
		    number_temp[1] = number_temp[m];  
		    number_temp[m] = temp;  
		    m--;  
		    p = 1;  
		    s = 2 * p;  
		    //建堆
		    while(s <= m) {  
		        if(s < m && number_temp[s+1] > number_temp[s])  
		            s++;  
		        if(number_temp[p] >= number_temp[s])  
		            break;  
		        temp=number_temp[p];  
		        number_temp[p]=number_temp[s];  
		        number_temp[s]=temp;  
		        p = s;  
		        s = 2 * p;  
		    }  
		}  
		for(int temp_j=1; temp_j < length + 1; temp_j++){  
		    number[temp_j-1] = number_temp[temp_j];  
		}  
		end=System.nanoTime();  
		  
		  
		 System.out.println("-------heap排序（堆排序法--改进的选择排序）--------");  
		 System.out.print("排序后是:");  
		 for(i=0; i < length; i++){  
		     System.out.print(number[i]+" ");  
		 }  
		 System.out.println();  
		 System.out.println("排序使用时间："+(end-start)+" ns");  
	}  
		
	//将原数组构造为从下标1开始的一个新数组，便于处理，同时将这个新数组构造为最初始的堆积树结构  
	public static void createheap(int number[]) {  
		int i, s, p, temp;  
		int length = number.length;
		int heap[] = new int[length]; 
		//建堆
		for(i = 1; i < length; i++) {  
		    heap[i] = number[i];  
		    s = i;  
		    p = i / 2;  
		    while(s >= 2 && heap[p] < heap[s]) {  
		        temp=heap[p];  
		        heap[p]=heap[s];  
		        heap[s]=temp;  
		        s = p;  
		        p = s / 2;  
		    }  
		}  
		for(i = 1; i < length; i++){  
		   number[i] = heap[i];   
		}  
	}  
}












