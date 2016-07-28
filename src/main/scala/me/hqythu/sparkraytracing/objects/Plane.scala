/**
  * Created by hqythu on 7/27/16.
  */

package me.hqythu.sparkraytracing.objects

import me.hqythu.sparkraytracing.Constants
import me.hqythu.sparkraytracing.tracer.Ray
import me.hqythu.sparkraytracing.utils.{Color, Vector3}

class Plane(position: Vector3, normal: Vector3, dx:Vector3, material: Material) extends AbstractObject(material) {

  override def intersects(ray: Ray): Intersects = {
    val D = -(position $ normal)
    val t = -(D + (ray.start $ normal)) / (ray.direction $ normal)
    if (t > Constants.EPSILON) {
      val pos = ray.start + ray.direction * t
      return new Intersects(this, true, t, pos, normal)
    }
    Intersects.noHit
  }

  override def getColor(intersect: Intersects): Color = {
//    if (material.texture != null) {
//      val dy = dx ^ intersect.normal
//      val u = (dx $ intersect.position) / dx.norm2
//      val v = (dy $ intersect.position) / dy.norm2
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
