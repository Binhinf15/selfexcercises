package kickstart.ware;

import java.util.List;

import org.salespointframework.inventory.Inventory;
import org.salespointframework.inventory.InventoryItem;

public interface InventoryItemRepository extends Inventory<InventoryItem>{
	public List<InventoryItem> findAllByOrderByProductName();
	public List<InventoryItem> findAllByOrderByProductPrice();
	public List<InventoryItem> findAllByOrderByQuantity();
	public InventoryItem findByProductName(String name);
}
