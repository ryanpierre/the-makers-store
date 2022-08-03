package db

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import scala.collection.mutable.ArrayBuffer

class DbAdapterTest extends AnyWordSpec with Matchers with MockFactory {
  "A DbAdapter" should {
    "fetch data from the database" which {
      "returns abstract (json) array data as native ArrayBuffer" in {
        val subject = new DbAdapter()
        subject.readAll shouldBe a[ArrayBuffer[_]]
      }
    }
  }
}