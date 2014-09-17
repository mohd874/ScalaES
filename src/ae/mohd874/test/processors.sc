package ae.mohd874.test

import ae.mohd874.es.Processor
import ae.mohd874.es.EntityManager
import ae.mohd874.es.Component
import ae.mohd874.es.Entity

object processors {
  val em = EntityManager                          //> em  : ae.mohd874.es.EntityManager.type = ae.mohd874.es.EntityManager$@dc5434
                                                  //| 

  val e1 = em.createEntity                        //> e1  : ae.mohd874.es.Entity = ae.mohd874.es.Entity@f4b65d
  val e2 = em.createEntity                        //> e2  : ae.mohd874.es.Entity = ae.mohd874.es.Entity@1f0c45f
  val e3 = em.createEntity                        //> e3  : ae.mohd874.es.Entity = ae.mohd874.es.Entity@1ec4333
  val e4 = em.createEntity                        //> e4  : ae.mohd874.es.Entity = ae.mohd874.es.Entity@2fdbb1

  em.entities.size == 4                           //> res0: Boolean = true

  case class Pos(var x: Int, var y: Int) extends Component(1)
  case class Mov(var vx: Int, var vy: Int) extends Component(2)
  case class Siz(w: Int, h: Int) extends Component(3)
  case class Dis extends Component(4)

  val p = new Pos(10,15)                          //> p  : ae.mohd874.test.processors.Pos = Pos(10,15)
  val p2 = new Pos(1,21)                          //> p2  : ae.mohd874.test.processors.Pos = Pos(1,21)
  val m = new Mov(2,1)                            //> m  : ae.mohd874.test.processors.Mov = Mov(2,1)
  val m2 = new Mov(-3,7)                          //> m2  : ae.mohd874.test.processors.Mov = Mov(-3,7)
  val s = new Siz(30,60)                          //> s  : ae.mohd874.test.processors.Siz = Siz(30,60)
  val d = new Dis                                 //> d  : ae.mohd874.test.processors.Dis = Dis()
  
  e1.components ++= List(p,m,s)                   //> res1: ae.mohd874.test.processors.e1.components.type = Set(Mov(2,1), Siz(30,6
                                                  //| 0), Pos(10,15))
  e2.components ++= List(m,d)                     //> res2: ae.mohd874.test.processors.e2.components.type = Set(Mov(2,1), Dis())
  e3.components ++= List(p2,m2)                   //> res3: ae.mohd874.test.processors.e3.components.type = Set(Pos(1,21), Mov(-3,
                                                  //| 7))

  class Movement extends Processor
  {
    val componentsId: List[Long] = List(1,2)

    override def process(e: Entity): Unit =
    {
      var pos: Pos = null
      var mov: Mov = null
      e.components.map{ c => c match
	      {
	         case x: Pos => pos = x
	         case x: Mov => mov = x
	         case _ =>
	      }
      }
      
      pos.x += mov.vx
      pos.y += mov.vy
    }
  }
  
  class Printer extends Processor
  {
    val componentsId: List[Long] = List(1,2)

    override def process(e: Entity): Unit =
    {
      var pos: Pos = null
      var mov: Mov = null
      e.components.map{ c => c match
	      {
	         case x: Pos => pos = x
	         case x: Mov => mov = x
	         case _ =>
	      }
      }
      
      println("Entity: "+e.id+" pos: "+pos)
    }
  }
  
  val ms = new Movement                           //> ms  : ae.mohd874.test.processors.Movement = ae.mohd874.test.processors$$ano
                                                  //| nfun$main$1$Movement$1@1ddc3ea
  val pr = new Printer                            //> pr  : ae.mohd874.test.processors.Printer = ae.mohd874.test.processors$$anon
                                                  //| fun$main$1$Printer$1@5f6ed4
  pr.entities.map(pr.process(_))                  //> Entity: 2 pos: Pos(10,15)
                                                  //| Entity: 4 pos: Pos(1,21)
                                                  //| res4: List[Unit] = List((), ())
  ms.entities.map(ms.process(_))                  //> res5: List[Unit] = List((), ())
  pr.entities.map(pr.process(_))                  //> Entity: 2 pos: Pos(12,16)
                                                  //| Entity: 4 pos: Pos(-2,28)
                                                  //| res6: List[Unit] = List((), ())
  ms.entities.map(ms.process(_))                  //> res7: List[Unit] = List((), ())
  pr.entities.map(pr.process(_))                  //> Entity: 2 pos: Pos(14,17)
                                                  //| Entity: 4 pos: Pos(-5,35)
                                                  //| res8: List[Unit] = List((), ())
  e1.id                                           //> res9: Long = 2
  val rm = ms.getEntityById(2)                    //> rm  : Option[ae.mohd874.es.Entity] = Some(ae.mohd874.es.Entity@f4b65d)
  rm match
  {
    case Some(e) => e.removeComponent(new Mov(0,0).getClass()); println("removed")
    case _ => println("None")
  }                                               //> removed
  ms.entities.map(ms.process(_))                  //> res10: List[Unit] = List(())
  pr.entities.map(pr.process(_))                  //> Entity: 4 pos: Pos(-8,42)
                                                  //| res11: List[Unit] = List(())
   
}