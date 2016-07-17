/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.objects

import scala.math.sqrt

import me.hqythu.sparkraytracing.{Ray, Vector3, Constants}

class Sphere(center: Vector3, radius: Double) extends AbstractObject {
  def intersects(ray: Ray): Intersects = {
    val v = this.center - ray.start
    val DdotV = ray.direction $ v

    val inside = !(v.norm2 > (radius * radius) + Constants.EPSILON)

    if (DdotV > Constants.EPSILON) {
      val discr = v.norm2 - DdotV * DdotV
      val a0 = radius * radius - discr
      if (a0 > Constants.EPSILON) {
        var dist = 0.0
        if (inside) {
          dist = DdotV + sqrt(a0)
        } else {
          dist = DdotV - sqrt(a0)
        }
        val pos = ray.start + ray.direction * dist
        val normal = (pos - this.center) % () * (if (inside) -1 else 1)
        return new Intersects(this, true, dist, pos, normal)
      }
    }

    Intersects.noHit
  }
}
