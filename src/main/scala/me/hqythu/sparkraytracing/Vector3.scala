/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing

import scala.math.{Pi, cos, sin, sqrt, random => rnd}

class Vector3(val x: Double, val y: Double, val z: Double) {

  def norm: Double = sqrt(x * x + y * y + z * z)

  def norm2: Double = x * x + y * y + z * z

  def +(that: Vector3): Vector3 = new Vector3(x + that.x, y + that.y, z + that.z)

  def -(that: Vector3): Vector3 = new Vector3(x - that.x, y - that.y, z - that.z)

  def *(factor: Double): Vector3 = new Vector3(x * factor, y * factor, z * factor)

  def /(factor: Double): Vector3 = new Vector3(x / factor, y / factor, z / factor)

  def ^(that: Vector3): Vector3 = new Vector3(y * that.z - z * that.y, z * that.x - z * that.z, x * that.y - y * that.x)

  def %(): Vector3 = if (norm == 0) Vector3.zero else new Vector3(x / norm, y / norm, z / norm)

  def $(that: Vector3): Double = x * that.x + y * that.y + z * that.z

}

object Vector3 {

  val zero = new Vector3(0, 0, 0)

  def -(vec: Vector3): Vector3 = new Vector3(-vec.x, -vec.y, -vec.z)

  def product(a: Vector3, b: Vector3, c: Vector3): Double = b ^ c $ a

  def random(r: Double = 1.0): Vector3 = {
    val theta = (rnd - 0.5) * Pi
    val phi = rnd * Pi * 2
    new Vector3(r * cos(phi) * cos(theta), r * cos(phi) * sin(theta), r * sin(phi))
  }

}
