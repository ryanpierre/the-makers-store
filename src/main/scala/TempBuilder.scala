import main.db.DbAdapter

class TempBuilder {
  val db = DbAdapter

  //fetch all items
  def fetchAllItems = db.getItems()
  //fetch a specific item
  def fetchItem(id: Int) = {
    fetchAllItems.filter(item => item.id == id)
  }
  //fetch all from specific location
  def fetchItemsFromLocation(location: String) = {
    fetchAllItems.filter(item => item.availableLocales.contains(location))
  }
  //fetch all locations given a specific continent
  def fetchLocationsFromContinent(continent: String) = {
    //locations.json looks like this:
    /*
    * continent
    * |  country
    * |  |  city
    * |  |  |  id etc
    */
    db.getLocations().filter(continentItem => continentItem._1 == continent).map(continentCollection => continentCollection._2.map(location => location._2))
  }
}
