package practica;

import java.util.Map;

public class Funciones {
	public void algoritmoWilson() {
		
	}
	
	public static void readBooleanList(boolean[] list) {
		for (int i=0; i<4 ;i++) {
			System.out.print(list[i]+" ");
		}
		System.out.println("");
	}
	
	public static int[] getRowsFromMap(Map<String, Cell> map) {
		int counter=0;
		String [] stringArray = new String[16];
		int[] rowsArray = new int[map.size()];
		
		for(Map.Entry pairEntry: map.entrySet()) {
			stringArray[counter]=((String) pairEntry.getKey()).replaceAll("\\D","");
			rowsArray[counter]=stringArray[counter].charAt(0);
			counter++;
		}
		
		return rowsArray;
	}
	
	public static int[] getColsFromMap(Map<String, Cell> map) {
		int counter=0;
		String [] stringArray = new String[16];
		int[] colsArray = new int[map.size()];
		
		for(Map.Entry pairEntry: map.entrySet()) {
			stringArray[counter]=((String) pairEntry.getKey()).replaceAll("\\D","");
			colsArray[counter]=stringArray[counter].charAt(1);
			counter++;
		}
		
		return colsArray;
	}
	
	public static Cell[] getCellsFromMap(Map<String, Cell> map) {
		int counter=0;
		Cell [] cellsArray = new Cell [map.size()];
		
		for(Map.Entry pairEntry: map.entrySet()) {
			cellsArray[counter]=(Cell) pairEntry.getValue();
			counter++;
		}
		
		return cellsArray;
	}
}
