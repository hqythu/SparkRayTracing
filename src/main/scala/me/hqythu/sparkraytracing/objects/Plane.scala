/**
  * Created by hqythu on 7/27/16.
  */

package me.hqythu.sparkraytracing.objects

import me.hqythu.sparkraytracing.Constants
import me.hqythu.sparkraytracing.tracer.Ray
import me.hqythu.sparkraytracing.utils.Vector3

class Plane(position: Vector3, normal: Vector3, material: Material) extends AbstractObject(material) {

  override def intersects(ray: Ray): Intersects = {
    val D = -(position $ normal)
    val t = -(D + (ray.start $ normal)) / (ray.direction $ normal)
    if (t > Constants.EPSILON) {
      val pos = ray.start + ray.direction * t
      return new Intersects(this, true, t, pos, normal)
    }
    Intersects.noHit
  }

}
