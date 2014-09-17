package ae.mohd874.es

import scala.collection.mutable.HashSet

class Component(val id: Long)

class Entity(val id: Long) {
  val components: HashSet[Component] = HashSet()

  def hasComponent[T](cType: T): Boolean =
    {
      components.toList.filter { x =>
        x match {
          case c: T => true
          case _    => false
        }
      }.size > 0
    }

  def hasComponentWithId(cId: Long): Boolean =
  {
    components.filter(_.id == cId).size > 0
  }

  def hasComponentsWithIds(cIds: List[Long]): Boolean =
  {
    components.filter(x => cIds.contains(x.id)).size == cIds.size
  }
  
  def removeComponent[T](clazz: Class[T]): Unit =
  {
    val c = components.filter(_.getClass() == clazz)
    components --= c
  }
}