package cn.zhao.util;

public class PageUtil {
	
	public static int pageSize = 3;
	
	public static int getTotalNum(int total){
		int totalNum;
		if(total%pageSize==0)
			totalNum =total/pageSize;
		else
			totalNum =total/pageSize + 1;
		return totalNum;
	}
}
