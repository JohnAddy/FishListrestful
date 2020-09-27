package util;

import java.io.PrintWriter;

import java.util.List;

import data.Fish;

public class HTML {
	public static void printStart(PrintWriter out) {
		out.println("<!DOCTYPE html><html><head><title>FishList</title></head><body>");
	}

	public static void printEnd(PrintWriter out) {
		out.println("</body></html>");
	}
	public static void printTable(PrintWriter out, List<Fish> fishlist) {
		out.println("<h1>Fished fish</h1>");
		out.println("<table border='1'>");
		for (int i=0;i<fishlist.size();i++) {
			Fish f=fishlist.get(i);
		
			
		
			out.println("<tr><td>"+f.getId()+"<td>"+f.getBreed()+"<td>"+f.getWeight()+"<td>"+f.getLength()+"<td>"+f.getCity()+"<td>"+f.getWater());
		}
		out.println("</table>");
//		out.println("<a href='./index.html'>Back to form</a>");
	}

}
