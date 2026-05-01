package test

import types.Monad

case class Box[A](value: A)

object Box {
  implicit val monad: Monad[Box] = new Monad[Box] {
    override def point[A](a: => A): Box[A] = Box(a)
    override def ap[A, B](fa: Box[A])(ff: Box[A => B]): Box[B] = Box(ff.value(fa.value))
    override def map[A, B](fa: Box[A])(f: A => B): Box[B] = Box(f(fa.value))
    override def map2[A, B, C](fa: Box[A], fb: Box[B])(f: (A, B) => C): Box[C] = Box(
      f(fa.value, fb.value)
    )
    override def bind[A, B](fa: Box[A])(f: A => Box[B]): Box[B] = f(fa.value)
    override def flatten[A](ffa: Box[Box[A]]): Box[A] = ffa.value
  }
}
