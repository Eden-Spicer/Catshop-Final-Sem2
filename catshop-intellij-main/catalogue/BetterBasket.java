package catalogue;

import java.io.Serializable;
import java.util.Collection;
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
		super.add(pr);
		Collections.sort(this,this);
		// if doesn't exist
		return true;
		
	
	
}
	public int compare(Product p1, Product p2) {
		return p1.getProductNum().compareTo(p2.getProductNum());
	}
	
	
	@Override
	 public boolean remove( Product pr )
	  {
		if(pr == null) return false;
	    for ( Product oldpr: this ){ //loop through products in betterBasket
	        if (oldpr.getProductNum().equals(((Product) pr).getProductNum())&oldpr.getQuantity()>1){
	        	oldpr.setQuantity(oldpr.getQuantity() - 1);
	        	return true;
	        }
	    }
	    
	    return super.remove( pr );     // Call remove in Basket
	  }
	

}