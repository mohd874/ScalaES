package ae.mohd874.es

import scala.collection.mutable.HashSet

trait Processor
{
    val componentsId: List[Long]
    val em = EntityManager
    def entities: List[Entity] = em.getAllEntitiesByComponentIds(componentsId)
    
    def process(e: Entity): Unit
    def processEntities = entities.map(process(_))
    def getEntityById(id: Long): Option[Entity] = 
    {
      val es = entities.filter(_.id == id)
      es match
      {
        case x::Nil => Some(x)
        case _ => None
      }
    }
}
