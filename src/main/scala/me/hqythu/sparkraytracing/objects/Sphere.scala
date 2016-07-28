/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.objects

import me.hqythu.sparkraytracing.tracer.Ray

import scala.math.sqrt
import me.hqythu.sparkraytracing.Constants
import me.hqythu.sparkraytracing.utils.{Color, Vector3}

class Sphere(center: Vector3, radius: Double, material: Material) extends AbstractObject(material) {

  override def intersects(ray: Ray): Intersects = {
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
        val normal = (pos - this.center) % ()
        return new Intersects(this, true, dist, pos, normal)
      }
    }

    Intersects.noHit
  }

  override def getColor(intersect: Intersects): Color = {
//    if (material.texture != null) {
//      val a = math.acos(-(intersect.normal $ new Vector3(0, 0, 1)))
//      val b = math.acos(math.min(math.max((intersect.normal $ new Vector3(0, 1, 0)) / math.sin(a), -1.0), 1.0))
//      val u = a / math.Pi
//      val v = b / 2 / math.Pi
//      val x = if (u - u.toInt < 0) {
//        u - u.toInt + 1
//      } else {
//        u - u.toInt
//      }
//      val y = if (v - v.toInt < 0) {
//        v - v.toInt + 1
//      } else {
//        v - v.toInt
//      }
//      val rgb = material.texture.getRGB((x * material.texture.getWidth()).toInt,
//        (y * material.texture.getHeight()).toInt) & (256 * 256 * 256 - 1)
//      Color.fromRGB(rgb)
//    } else {
//      material.color
//    }
    material.color
  }

}
