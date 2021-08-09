// package io.isomarcte.sbt.version.scheme.enforcer.core.project

// import scala.collection.immutable.SortedSet
// import io.isomarcte.sbt.version.scheme.enforcer.core._
// import io.isomarcte.sbt.version.scheme.enforcer.core.internal.toSortedSet

// trait VersionSchemableClass[F[_], A] extends Serializable { self =>
//   def scheme(versionScheme: VersionScheme, value: F[A]): Either[String, F[versionScheme.VersionType]]
// }

// object VersionSchemableClass {
//   type VersionChangeTypeClassId[A] = VersionSchemableClass[Id, A]

//   def apply[F[_], A](implicit F: VersionSchemableClass[F, A]): VersionSchemableClass[F, A] = F

//   implicit val versionInstance: VersionSchemableClass[Id, Version] =
//     new VersionSchemableClass[Id, Version] {
//       override def scheme(versionScheme: VersionScheme, value: Id[Version]): Either[String,Id[versionScheme.VersionType]] =
//         versionScheme.fromVersion(value)
//     }

//   implicit def vectorInstance[F[_], A](implicit F: VersionSchemableClass[F, A]): VersionSchemableClass[Lambda[A => Vector[F[A]]], A] =
//     new VersionSchemableClass[Lambda[A => Vector[F[A]]], A] {
//       override def scheme(versionScheme: VersionScheme, value: Vector[F[A]]): Either[String,Vector[F[versionScheme.VersionType]]] =
//         value.foldLeft(Right(Vector.empty): Either[String, Vector[F[versionScheme.VersionType]]]){
//           case (acc, value) =>
//             acc.flatMap(acc =>
//               F.scheme(versionScheme, value).map(value =>
//                 acc ++ Vector(value)
//               )
//             )
//         }
//     }

//   implicit def sortedSetInstance[F[_], A](implicit F: VersionSchemableClass[F, A], G: Ordering[F[A]]): VersionSchemableClass[Lambda[A => SortedSet[F[A]]], A] =
//     new VersionSchemableClass[Lambda[A => SortedSet[F[A]]], A] {
//       override def scheme(versionScheme: VersionScheme, value: SortedSet[F[A]]): Either[String,SortedSet[F[versionScheme.VersionType]]] =
//         vectorInstance[F, A].scheme(versionScheme, value.toVector).map(value => toSortedSet(value))
//     }
// }
