//package mochia.vai.da.certo;
//
//import java.util.Arrays;
//import java.util.Map;
//
//import br.puc.rio.inf.paa.fractionalKnapsack.Item;
//
//public class MochilaDaDepressao {
//	
//	Map<Item, Double> itemsAdded;
//	
//	public Map<Item, Double> knaspack(FractionalKnapsack knap){
//		
//	}
//	
//	public Map<Item, Double> getItems(Item[] items, double capacity){
//		
//		if(items.length == 1){
//			if(items[0].weight < capacity){
//				itemsAdded.put(items[0], 1.0);
//			}else{
//				itemsAdded.put(items[0], capacity/items[0].weight);
//			}
//	        return itemsAdded;
//		}
//
//		int middle = items.length / 2;
//		
//		Item itemPivo = kthValue(items, middle);
//		
//	  
//	    int partitionIdx = partition(items, itemPivo);
//	        
//	}
//
//	private Item kthValue(Item[] items,int k, int start, int end) {
//		// TODO Auto-generated method stub
//		int size = items.length;
//		if(items.length < 5){
//			//ordena items		
//			return items[size/2];
//			
//		}
//		int groupsQuantity = 0;
//		int rest = size % 5;
//		if (rest == 0) {
//			groupsQuantity = size / 5;
//		} else {
//			groupsQuantity = (size / 5) + 1;
//		}
//
//		// Creating auxiliar array to find medians in each group
//		Item[] medians = new Item[groupsQuantity];
//		
//		int indexItems;
//		int medianIndex = 0; 
//		for (indexItems = 0; indexItems < rest; indexItems = indexItems + 5) {
//			//sort(itemsAux, indexItems, indexItems + 4);
//
//			medians[medianIndex] = items[indexItems + 2];
//			medianIndex++;
//		}
//		
//		if (rest != 0) {
//		//	sort(itemsAux, indexItems, n - 1);
//			int indexMedianRestant = indexItems + (rest / 2);
//
//			medians[medianIndex] = items[indexMedianRestant];
//		}
//		
//		Item pivo = kthValue(medians, medians.length/2, 0, medians.length - 1);
//		int indexPivo = partition(items, pivo); //get index pivo
//		int p = indexPivo + 1;
//		
//		if(k == p){
//			return pivo;
//		}
//		else
//			if(k > p){
//				return kthValue(items, k-p, p, end);
//			}
//		return kthValue(items, k, start, indexPivo);
//	
//	}
//
//	private int partition(Item[] items, Item pivo) {
//		// TODO Auto-generated method stub
//		
//		int sameValue = 0;
//		int i = 0;
//		Item temp = null;
//		
//		for(int j = 0; j < items.length; j++){
//			if (items[j].ratio < pivo.ratio){
//				 temp = items[i];
//				 items[i] = items[j];
//				 items[j] = temp;
//				  i += 1;
//			}
//			else
//				if(items[j].ratio == pivo.ratio){
//					 if(sameValue%2 == 0){
//		                temp = items[i];
//		                items[i] = items[j];
//		                items[j] = temp;
//		                i += 1;
//		               sameValue += 1;
//					 }
//				}
//	           
//		}
//	         
//
//	    // por pivot no devido lugar
//	    temp = items[i];
//	    items[i] = pivo;
//	    int size = items.length -1;
//	    items[size] = temp;
//		
//		return i;
//	}
//	
//	
//	
//	
//
//
//
//}
