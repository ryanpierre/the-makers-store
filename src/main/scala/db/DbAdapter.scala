package db

class DbAdapter {
  def readAll = {
    val jsonString = os.read(os.pwd/"src"/"main"/"resources"/"products.json")
    val data = ujson.read(jsonString)
    data.value 
  }
}
