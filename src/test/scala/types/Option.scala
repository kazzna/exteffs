package types

object Option {
  implicit val monad: Monad[Option] = new Monad[Option] {
    override def point[A](a: => A): Option[A] = Some(a)
    override def ap[A, B](fa: Option[A])(ff: Option[A => B]): Option[B] = for { a <- fa; f <- ff } yield f(a)
    override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
    override def map2[A, B, C](fa: Option[A], fb: Option[B])(f: (A, B) => C): Option[C] = for {
      a <- fa; b <- fb
    } yield f(a, b)
    override def bind[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)
    override def flatten[A](ffa: Option[Option[A]]): Option[A] = ffa.flatten
  }
}
