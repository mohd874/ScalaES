package ae.mohd874.test

import ae.mohd874.es.Entity
import ae.mohd874.es.Processor
import ae.mohd874.es.Component

object TestObjects {
  case class Pos(var x: Int, var y: Int) extends Component(1)
  case class Mov(var vx: Int, var vy: Int) extends Component(2)
  case class Siz(w: Int, h: Int) extends Component(3)
  case class Dis extends Component(4)

  class Printer extends Processor {
    val componentsId: List[Long] = List(1, 2)

    override def process(e: Entity): Unit =
      {
        var pos: Pos = null
        var mov: Mov = null
        e.components.map { c =>
          c match {
            case x: Pos => pos = x
            case x: Mov => mov = x
            case _      =>
          }
        }

        println("Entity: " + e.id + " pos: " + pos)
      }
  }

  class Movement extends Processor {
    val componentsId: List[Long] = List(1, 2)

    override def process(e: Entity): Unit =
      {
        var pos: Pos = null
        var mov: Mov = null
        e.components.map { c =>
          c match {
            case x: Pos => pos = x
            case x: Mov => mov = x
            case _      =>
          }
        }

        pos.x += mov.vx
        pos.y += mov.vy
      }
  }
}