package types

object Either {
  implicit def monadWithLeftSemigroup[E](implicit E: Semigroup[E]): Monad[[A] =>> Either[E, A]] =
    new Monad[[A] =>> Either[E, A]] {
      override def point[A](a: => A): Either[E, A] = Right(a)

      override def ap[A, B](fa: Either[E, A])(ff: Either[E, A => B]): Either[E, B] =
        (fa, ff) match {
          case (Right(a), Right(f)) => Right(f(a))
          case (Left(e1), Left(e2)) => Left(E.append(e1, e2))
          case (Left(e), _) => Left(e)
          case (_, Left(e)) => Left(e)
        }

      override def map[A, B](fa: Either[E, A])(f: A => B): Either[E, B] =
        fa.map(f)

      override def map2[A, B, C](fa: Either[E, A], fb: Either[E, B])(f: (A, B) => C): Either[E, C] =
        (fa, fb) match {
          case (Right(a), Right(b)) => Right(f(a, b))
          case (Left(e1), Left(e2)) => Left(E.append(e1, e2))
          case (Left(e), _) => Left(e)
          case (_, Left(e)) => Left(e)
        }

      override def bind[A, B](fa: Either[E, A])(f: A => Either[E, B]): Either[E, B] =
        fa.flatMap(f)

      override def flatten[A](ffa: Either[E, Either[E, A]]): Either[E, A] =
        ffa.flatMap(identity)
    }
}
