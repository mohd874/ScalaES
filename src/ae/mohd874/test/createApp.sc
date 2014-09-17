package ae.mohd874.test

import ae.mohd874.es._

object createApp {
  import ae.mohd874.test.TestObjects._

  val world = new World                           //> world  : ae.mohd874.es.World = ae.mohd874.es.World@a4effe

  val em = EntityManager                          //> em  : ae.mohd874.es.EntityManager.type = ae.mohd874.es.EntityManager$@1ec264
                                                  //| c
  
  val mp = new Movement                           //> mp  : ae.mohd874.test.TestObjects.Movement = ae.mohd874.test.TestObjects$Mov
                                                  //| ement@5b38d6
  val pp = new Printer                            //> pp  : ae.mohd874.test.TestObjects.Printer = ae.mohd874.test.TestObjects$Prin
                                                  //| ter@1090c56
  world.processors ++= List(mp, pp)               //> res0: ae.mohd874.test.createApp.world.processors.type = ListBuffer(ae.mohd87
                                                  //| 4.test.TestObjects$Movement@5b38d6, ae.mohd874.test.TestObjects$Printer@1090
                                                  //| c56)

  val e1 = em.createEntity                        //> e1  : ae.mohd874.es.Entity = ae.mohd874.es.Entity@8ef465
  
  e1.components ++= List(new Pos(10,30), new Mov(-1,0))
                                                  //> res1: ae.mohd874.test.createApp.e1.components.type = Set(Pos(10,30), Mov(-1,
                                                  //| 0))
  for(i <- 1 to 8) world.tick                     //> Entity: 2 pos: Pos(9,30)
                                                  //| Entity: 2 pos: Pos(8,30)
                                                  //| Entity: 2 pos: Pos(7,30)
                                                  //| Entity: 2 pos: Pos(6,30)
                                                  //| Entity: 2 pos: Pos(5,30)
                                                  //| Entity: 2 pos: Pos(4,30)
                                                  //| Entity: 2 pos: Pos(3,30)
                                                  //| Entity: 2 pos: Pos(2,30)
  
}