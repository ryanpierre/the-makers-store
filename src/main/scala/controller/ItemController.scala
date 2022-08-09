package main.controller

import main.model.Item
import main.db.DbAdapter
import scala.collection.mutable.LinkedHashMap
import main.model.Location
import main.db.DbAdapterBase
import main.helper.LocationHelper
class ItemController(dbAdapter: DbAdapterBase = DbAdapter) {
  def getAvailableItemsByLocation(location: String): List[Item] = {
    val locations = dbAdapter.getLocations()
    val items = dbAdapter.getItems()
    val locale = LocationHelper.getContinentFromLocation(locations, location)

    return items.filter(i => i.availableLocales.contains(locale)).toList
  }
}
