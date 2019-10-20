package com.rlsoft.stockman.persistence.mysql.abstraction

import slick.dbio.{Effect, NoStream}
import slick.jdbc.JdbcBackend.Database
import com.rlsoft.stockman.persistence.mysql.AppMySqlProfile.api._
import slick.lifted.{Rep, TableQuery}

import scala.concurrent.{ExecutionContext, Future}

abstract class Repository[E <: EntityWithId[PK], T <: Table[E] with TableWithId[PK], PK: BaseColumnType](
    implicit ec: ExecutionContext
) {
  val tableQuery: TableQuery[T]
  val database: Database

  def add(entity: E): Future[E] = {
    mapActionAndRun(tableQuery += entity)(_ => entity)
  }

  def update(entity: E): Future[Int] = {
    runAction(tableQuery.update(entity))
  }

  def save(entity: E): Future[E] = findById(entity.id).flatMap {
    case Some(_) => update(entity).map(_ => entity)
    case None => add(entity)
  }

  def find(criteria: T => Rep[Boolean]): Future[Option[E]] = {
    mapActionAndRun(tableQuery.filter(criteria(_)).result)(_.headOption)
  }

  def findAll: Future[Seq[E]] = runAction(tableQuery.result)

  def findById(id: PK): Future[Option[E]] = find(_.id === id)

  // Helpers
  def runAction[R, S <: NoStream, EF <: Effect](action: DBIOAction[R, S, EF]): Future[R] =
    database.run(action)

  def mapActionAndRun[R, S <: NoStream, EF <: Effect, R2](action: DBIOAction[R, S, EF])(fn: R => R2): Future[R2] = {
    runAction(action.map(fn))
  }
}
