package object ext {
  type Eff[R[_], A] = Free[R, A]
}
