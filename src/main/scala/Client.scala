import controllers.ItemController
import main.model.{Item, Location}

import scala.collection.mutable.ArrayBuffer

object Client {
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
