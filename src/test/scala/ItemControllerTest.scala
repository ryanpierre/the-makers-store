import controllers.ItemController
import main.db.DbAdapter
import main.model.Item
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ItemControllerTest extends AnyWordSpec with Matchers {
  val db = DbAdapter
  "ItemController" should {
    "fetch all items" in {
      db.dropAndReset
      val controller = ItemController
      val itemNames = controller.fetchAllItems.map(x => x.name)
      itemNames should contain("Delicious Soup")
      itemNames should contain("Lovely Apple")
      itemNames should contain("Strange Gourd")
      itemNames should contain("Outrageous Tart")
      itemNames should contain("Yankee Salami")
      itemNames should contain("Questionable Coffee")
      itemNames should contain("The 'Best' Coffee")
      itemNames should contain("Orange Peel")
      //I am currently unaware of a better method than this... this is rather ugly.
    }
    "fetch specific item" in {
      db.dropAndReset
      val controller = ItemController
      val thisItem = controller.fetchItem(7)
      assert(thisItem.id == 7)
      assert(thisItem.name == "Orange Peel")
      assert(thisItem.price == 0.4)
      assert(thisItem.quantity == 8)
      assert(thisItem.availableLocales.head == "EU")
      assert(thisItem.availableLocales.last == "UK")
      //again, there must be a better way
    }
    "fetch items available in a specific location" in {
      db.dropAndReset
      val controller = ItemController
      val itemNames = controller.fetchItemsFromLocation("London").map(x => x.name)
      itemNames should contain("Delicious Soup")
      itemNames should contain("Lovely Apple")
      itemNames should contain("Strange Gourd")
      itemNames should contain("Outrageous Tart")
      itemNames should contain("The 'Best' Coffee")
      itemNames should contain("Orange Peel")
    }
    "fetch all locations from specific continent" in {
      db.dropAndReset
      val controller = ItemController
      val locationsNames = controller.fetchLocationsFromContinent("EU").map(x => x.name)
      locationsNames should contain("London")
      locationsNames should contain("Manchester")
      locationsNames should contain("Paris")
      locationsNames should contain("Lyon")
      locationsNames should contain("Madrid")
      locationsNames should contain("Berlin")
      locationsNames should contain("Dusseldorf")
      locationsNames should contain("Milan")
      assert(locationsNames.length == 8)
    }
    "create a new Item" in {
      db.dropAndReset
      val controller = ItemController
      val thisNewItem: Item = new Item(id = 999999, name = "Tom's Energy Drink", price = 1.5, quantity = 1, availableLocales = List("UK"))
      controller.createItem(thisNewItem)
      val fetchThisItem = controller.fetchItem(999999)
      assert(fetchThisItem.name == thisNewItem.name)
      assert(fetchThisItem.price == thisNewItem.price)
      assert(fetchThisItem.quantity == thisNewItem.quantity)
      assert(fetchThisItem.availableLocales == thisNewItem.availableLocales)
    }
    "update an existing item" in {
      db.dropAndReset
      val controller = ItemController
      val thisNewItem: Item = new Item(id = 0, name = "Bob's Energy Drink", price = 1, quantity = 1, availableLocales = List("UK"))
      controller.updateItem(0, thisNewItem)
      val updated = controller.fetchItem(0)
      assert(updated.name == thisNewItem.name)
      assert(updated.price == thisNewItem.price)
    }
  }
}
