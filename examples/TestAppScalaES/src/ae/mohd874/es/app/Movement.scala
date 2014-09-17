package ae.mohd874.es.app

import ae.mohd874.es.Component
import ae.mohd874.es.app.Constants._
import ae.mohd874.es.Processor
import ae.mohd874.es.Entity

case class Position(var x: Float, var y: Float) extends Component(POSITION)
case class Velocity(var x: Float, var y: Float) extends Component(VELOCITY)

class MovementProcessor extends Processor {
  
  val componentsId: List[Long] = List(POSITION, VELOCITY)

  override def process(e: Entity): Unit =
  {
    var pos: Position = null
    var vel: Velocity = null
    e.components.map { c =>
          c match {
            case x: Position => pos = x
            case x: Velocity => vel = x
            case _      =>
          }
        }

        pos.x += vel.x
        pos.y += vel.y
  }
}