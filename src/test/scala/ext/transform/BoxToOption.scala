package ext.transform

import test.Box
import types.NaturalTransformation

object BoxToOption extends NaturalTransformation[Box, Option] {
  override def apply[A](fa: Box[A]): Option[A] = Some(fa.value)
}
