package com.flexipgroup.app.uploadprocess;

import java.util.List;

public class ProcessLogic <E>{

	
	private void compareFiles(List<E>a,List<E>b,List<E>c,List<E>d) {
		
		if(a.size() > b.size())
		{
			sort(a, b,c,d);
		}else if(a.size() < b.size())
		{
			sort(b,a,c,d);
		}else {
			sortEqual(a,b,c,d);
		}
	}
	
	private void sort(List<E>a,List<E>b,List<E>c,List<E>d)
	{
		for(int i = 0; i < a.size(); i++)
		{
			for(int j = 0; j < b.size(); j++)
			{
				if(a.get(i).equals(b.get(j))) {
					c.add(b.get(j));
				}else {
					d.add(b.get(j));
				}
			}
		}
	}
	
	private void sortEqual(List<E>a,List<E>b,List<E>c,List<E>d)
	{
		for(int j = 0; j < b.size(); j++)
		{
			if(a.get(j).equals(b.get(j))) {
				c.add(b.get(j));
			}else {
				d.add(b.get(j));
			}
		}
	}
	
	public void getcompareFiles(List<E>a,List<E>b,List<E>c,List<E>d) {
		
		compareFiles(a,b,c,d);
	}
	
	
}
