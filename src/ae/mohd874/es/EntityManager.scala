package ae.mohd874.es

import scala.collection.mutable.ListBuffer

object EntityManager {

  val entities: ListBuffer[Entity] = ListBuffer()

  class IdGen(var startId: Long) {
    def nextId: Long =
      {
        startId += 1
        startId
      }
  }

  val idGen: IdGen = new IdGen(1)

  def createEntity: Entity =
    {
      val e = new Entity(idGen.nextId)
      entities += e
      e
    }
  
  def getAllEntitiesByComponentType[T](cType: T): List[Entity] = 
    entities.toList.filter(_ hasComponent cType)
    
  def getAllEntitiesByComponentId(cId: Long): List[Entity] =
    entities.toList.filter(_ hasComponentWithId cId)

  def getAllEntitiesByComponentIds(cIds: List[Long]): List[Entity] =
    entities.toList.filter(_ hasComponentsWithIds cIds)
}