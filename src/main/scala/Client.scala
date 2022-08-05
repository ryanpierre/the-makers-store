import controllers.ItemController
import main.model.{Item, Location}

import scala.collection.mutable.ArrayBuffer

object Client {
  //these are more or less temporary solutions as there is no user input currently.
  //So in this case it simply serves as all possible functionality.
  def allItems: ArrayBuffer[Item] = {
    ItemController.fetchAllItems
  }

  def itemById(id: Int): Item = {
    ItemController.fetchItem(id)
  }

  def itemsByLocation(locationName: String): ArrayBuffer[Item] = {
    ItemController.fetchItemsFromLocation(locationName)
  }

  def locationsByContinent(continentName: String): Seq[Location] = {
    ItemController.fetchLocationsFromContinent(continentName)
  }
}
