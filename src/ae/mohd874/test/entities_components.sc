package ae.mohd874.test

import ae.mohd874.es.EntityManager
import ae.mohd874.es.Component

object worksheet {

  val em = EntityManager                          //> em  : ae.mohd874.es.EntityManager.type = ae.mohd874.es.EntityManager$@a16b7c
                                                  //| 

  val e1 = em.createEntity                        //> e1  : ae.mohd874.es.Entity = ae.mohd874.es.Entity@17da562
  val e2 = em.createEntity                        //> e2  : ae.mohd874.es.Entity = ae.mohd874.es.Entity@1f4ff23
  val e3 = em.createEntity                        //> e3  : ae.mohd874.es.Entity = ae.mohd874.es.Entity@19a8c41
  val e4 = em.createEntity                        //> e4  : ae.mohd874.es.Entity = ae.mohd874.es.Entity@16e1eff

  em.entities.size == 4                           //> res0: Boolean = true

  case class Pos extends Component(1)
  case class Mov extends Component(2)
  case class Siz extends Component(3)

  val c1 = new Pos                                //> c1  : ae.mohd874.test.worksheet.Pos = Pos()
  val c3 = new Mov                                //> c3  : ae.mohd874.test.worksheet.Mov = Mov()
  val c6 = new Siz                                //> c6  : ae.mohd874.test.worksheet.Siz = Siz()

  e1.components ++= List(c1, c3, c6)              //> res1: ae.mohd874.test.worksheet.e1.components.type = Set(Mov(), Pos(), Siz()
                                                  //| )
  e2.components ++= List(c1, c3, c1)              //> res2: ae.mohd874.test.worksheet.e2.components.type = Set(Mov(), Pos())
  e3.components += c1                             //> res3: ae.mohd874.test.worksheet.e3.components.type = Set(Pos())

  em.getAllEntitiesByComponentType(c3).size       //> res4: Int = 3
  em.getAllEntitiesByComponentType(c3).size == 3  //> res5: Boolean = true
  
  em.getAllEntitiesByComponentId(c3.id).size      //> res6: Int = 2
  em.getAllEntitiesByComponentId(c3.id).size == 2 //> res7: Boolean = true

  em.getAllEntitiesByComponentId(c1.id).size      //> res8: Int = 3
  em.getAllEntitiesByComponentId(c1.id).size == 3 //> res9: Boolean = true

  em.getAllEntitiesByComponentId(c6.id).size      //> res10: Int = 1
  em.getAllEntitiesByComponentId(c6.id).size == 1 //> res11: Boolean = true
  
  em.getAllEntitiesByComponentIds(List(2,3)).size //> res12: Int = 1
}