package main.db

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import scala.collection.mutable.ArrayBuffer
import main.model.Item

class DbAdapterTest extends AnyWordSpec with Matchers with MockFactory {
  "A DbAdapter" should {
    "fetch data from the database" in {
      val subject = new DbAdapter()
      subject.read("items") shouldBe a[ArrayBuffer[_]]
    }

    "create Item models from it's respective ArrayBuffer data" in {
      val subject = new DbAdapter()
      val items = subject.getItems()
      items shouldBe a[ArrayBuffer[_]]
      items.foreach(p => p shouldBe a[Item])
    }
  }
}
