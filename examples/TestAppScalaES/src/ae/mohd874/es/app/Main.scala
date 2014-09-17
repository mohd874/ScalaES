package ae.mohd874.es.app

import processing.core._
import processing.core.PApplet._
import processing.core.PConstants._
import processing.event.MouseEvent
import processing.event.KeyEvent
import ae.mohd874.es.World
import ae.mohd874.es.EntityManager
import ae.mohd874.es.app.Constants._

object Main extends PApplet {
  def main(args: Array[String]) {
    PApplet.main(Array[String]("ae.mohd874.es.app.Main"))
  }
}

class Main extends PApplet {

  val world: World = new World
  val em = EntityManager

  override def setup() =
    {
      size(640, 480)
      fill(100)
      world.processors += new MovementProcessor
      world.processors += new RenderProcessor(this)

      for (i <- 1 to 1000) {
        val e = em.createEntity
        e.components ++= List(new Position(random(width), random(height)), 
                              new Velocity(random(-3, 3), random(-3, 3)), 
                              new Rect(random(50), random(50)))
      }
    }

  override def draw() =
    {
      background(255)
      fill(100)
      world.tick
      
      fill(0, 255, 255)
      text("FPS: "+frameRate, 20, 20)
    }

  override def mouseClicked(event: MouseEvent): Unit =
    {
    }

  override def mousePressed(event: MouseEvent): Unit =
    {
    }

  override def keyTyped(event: KeyEvent): Unit =
    {
    }
}
