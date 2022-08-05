import controllers.ItemController
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ItemControllerTest extends AnyWordSpec with Matchers {
  "ItemController" should {
    "fetch all items" in {
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
      val controller = ItemController
      val itemNames = controller.fetchItemsFromLocation("UK").map(x => x.name)
      assert(itemNames.length == 1) // we only have one item available in the UK
      itemNames should contain("Orange Peel")
    }
    "fetch all locations from specific continent" in {
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
  }
}
