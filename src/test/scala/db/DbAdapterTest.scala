package main.db

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import scala.collection.mutable.ArrayBuffer
import main.model.Item

class DbAdapterTest extends AnyWordSpec with Matchers with MockFactory {
  "A DbAdapter" should {
    // "fetch data from the database" in {
    //   val subject = new DbAdapter()
    //   subject.read("items") shouldBe a[ArrayBuffer[_]]
    // }

    // "create Item models from it's respective ArrayBuffer data" in {
    //   val subject = new DbAdapter()
    //   val items = subject.getItems()
    //   items shouldBe a[ArrayBuffer[_]]
    //   items.foreach(p => p shouldBe a[Item])
    // }

    // "reset the database when required" in {
    //     val subject = new DbAdapter()

    //     subject.reset

    //     val templateItems = os.read(os.pwd/"src"/"main"/"resources"/"items.json")
    //     val replacedItems = os.read(os.pwd/"src"/"main"/"resources"/"items.base.json")

    //     templateItems.toString() shouldBe replacedItems.toString()
    // }

    // "update an item in the database" in {
    //    val subject = new DbAdapter()

    //    subject.updateItem(1, new Item(1, "New Name", 500.50, 30, List("NA", "EU")))

    //    val items = os.read(os.pwd/"src"/"main"/"resources"/"items.json")
    //    val data = ujson.read(items)
    //    val newItem = data.arr.find(i => i("id").value.asInstanceOf[Double].toInt == 1)

    //    newItem("id") shouldBe 1
    //    newItem("name") shouldBe "New Name"
    //    newItem("price") shouldBe 500.50
    //    newItem("quantity") shouldBe 30
    // }

    "create an item in the database" in {
       val subject = new DbAdapter()
       subject.createItem(new Item(1, "New Name", 500.50, 30, List("NA", "EU")))
    }


  }
}
