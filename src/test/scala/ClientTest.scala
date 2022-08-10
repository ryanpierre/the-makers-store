import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

//these tests make the item control tests irrelevant currently. However I
//wont delete the item control tests just yet as more functionality will
//most likely be added to this making them great if you just want to test specifics
class ClientTest extends AnyWordSpec with Matchers {
  "Client" should {
    "receive all items when calling client -> all items" in {
      val thisClient = Client
      val itemNames = thisClient.allItems.map(x => x.name)
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
    "receive a specific item when calling client -> itemById" in {
      val thisClient = Client
      val thisItem = thisClient.itemById(7)
      assert(thisItem.id == 7)
      assert(thisItem.name == "Orange Peel")
      assert(thisItem.price == 0.4)
      assert(thisItem.quantity == 8)
      assert(thisItem.availableLocales.head == "EU")
      assert(thisItem.availableLocales.last == "UK")
      //again, there must be a better way
    }
    "receive items available in a specific location when calling client -> itemsByLocation" in {
      val thisClient = Client
      val itemNames = thisClient.itemsByLocation("London").map(x => x.name)
      assert(itemNames.length == 1) // we only have one item available in the UK
      itemNames should contain("Orange Peel")
    }
    "receive all locations from specific continent when calling client -> locationsByContinent" in {
      val thisClient = Client
      val locationsNames = thisClient.locationsByContinent("EU").map(x => x.name)
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
