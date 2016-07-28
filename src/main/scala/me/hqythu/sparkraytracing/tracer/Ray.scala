/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.tracer

import me.hqythu.sparkraytracing.utils.Vector3

class Ray(val start: Vector3, val direction: Vector3) {

  def reflect(normal: Vector3, position: Vector3): Ray = {
    new Ray(position, (direction - normal * 2 * (direction $ normal)) % ())
  }

  def refract(normal: Vector3, position: Vector3, n: Double): Ray = {
    val cos_i = direction $ normal
    val inner = cos_i > 0
    val sin_i = math.sqrt(1 - cos_i * cos_i)
    val sin_r = if (inner) sin_i * n else sin_i / n
    val cos_r = math.sqrt(1 - sin_i * sin_i)
    if (inner) {
      new Ray(position, ((direction + normal * (direction $ normal)) % () * sin_r + normal * cos_r) % ())
    } else {
      new Ray(position, ((direction - normal * (direction $ normal)) % () * sin_r - normal * cos_r) % ())
    }
  }

}
