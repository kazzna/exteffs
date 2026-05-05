package types

import scala.{List => ScalaList}

object List {
  implicit val monad: Monad[List] = new Monad[List] {
    override def point[A](a: => A): List[A] = ScalaList(a)
    override def ap[A, B](fa: List[A])(ff: List[A => B]): List[B] = for { a <- fa; f <- ff } yield f(a)
    override def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)
    override def map2[A, B, C](fa: List[A], fb: List[B])(f: (A, B) => C): List[C] =
      for { a <- fa; b <- fb } yield f(a, b)
    override def bind[A, B](fa: List[A])(f: A => List[B]): List[B] = fa.flatMap(f)
    override def flatten[A](ffa: List[List[A]]): List[A] = ffa.flatten
  }

  implicit def semigroup[A]: Semigroup[List[A]] = new Semigroup[List[A]] {
    override def append(a1: List[A], a2: => List[A]): List[A] = a1 ++ a2
  }
}
