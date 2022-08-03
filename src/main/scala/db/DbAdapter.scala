package db

class DbAdapter {
  // Reads an entire 'database' into a LinkedList
  def readAll = {
    val jsonString = os.read(os.pwd/"src"/"main"/"resources"/"products.json")
    val data = ujson.read(jsonString)
    println(data.value)
    data.value
  }

  def getRelevantData = {

  }
}
