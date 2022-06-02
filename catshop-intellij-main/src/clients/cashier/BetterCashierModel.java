package clients.cashier;
import catalogue.BetterBasket;
import middle.MiddleFactory;
import catalogue.Basket;


public class BetterCashierModel extends CashierModel{

	public BetterCashierModel(MiddleFactory mf) {
		super(mf);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected Basket makeBasket()
	  {
		System.out.println("made betterbasket");
	    return new BetterBasket();
	  }

}

