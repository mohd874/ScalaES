package ae.mohd874.es

import scala.collection.mutable.ListBuffer

class World {

    val processors: ListBuffer[Processor] = ListBuffer()
    
    val em = EntityManager
    
    def tick: Unit =
    {
      processors.foreach(_.processEntities)
    }
}