package catalogue;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

/**
 * Write a description of class BetterBasket here.
 * 
 * @author Your Name
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable, Comparator<Product> {
	private static final long serialVersionUID = 1L;

	/** Check if product already exists on the basket
	 * and increase the quantity
	*/
	
	@Override
	public boolean add(Product pr) {
		if(pr == null) return false;
		
		//Check the the list of items
		for(Product old_pr: this) {
		
		if(old_pr.getProductNum().equals(pr.getProductNum())) {
			old_pr.setQuantity(old_pr.getQuantity()+ pr.getQuantity());
		
			return true;
		}	
	}
		
		Collections.sort(this,this);
		// if doesn't exist
		return super.add(pr);
		
	
	
}
	public int compare(Product p1, Product p2) {
		return p1.getProductNum().compareTo(p2.getProductNum());
	}
	
	
	
	public boolean remove(Product pr) {
		if(pr == null) return false;
		
		for(Product old_pr: this) {
			if(old_pr.getProductNum() == pr.getProductNum()) {
				if(old_pr.getQuantity()== 1) return super.remove(old_pr);
				
				old_pr.setQuantity(old_pr.getQuantity()-1);
				old_pr.setPrice(pr.getPrice()/old_pr.getQuantity()*(old_pr.getQuantity()-1));
				return true;
			}	
		}
			return false;
	}
	

}