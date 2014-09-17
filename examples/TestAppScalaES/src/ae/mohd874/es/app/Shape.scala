package ae.mohd874.es.app

import ae.mohd874.es.Component
import ae.mohd874.es.app.Constants._
import ae.mohd874.es.Processor
import ae.mohd874.es.Entity
import processing.core.PApplet

case class Rect(var w: Float, var h: Float) extends Component(RECT)
case class Ellipse(var w: Float, var h: Float) extends Component(ELLIPSE)

class RenderProcessor(ps: PApplet) extends Processor
{
  val componentsId: List[Long] = List(RECT)

  override def process(e: Entity): Unit =
  {
    var pos: Position = null
    var rect: Rect = null
    e.components.map { c =>
          c match {
            case x: Position => pos = x
            case x: Rect => rect = x
            case _      =>
          }
        }
    
    ps.rect(pos.x, pos.y, rect.w, rect.h)
  }
}