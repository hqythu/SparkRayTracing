/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.tracer

import me.hqythu.sparkraytracing.objects.{AbstractObject, Intersects, Light}
import me.hqythu.sparkraytracing.utils.Color

class Scene(val objects: Array[AbstractObject], val lights: Array[Light], val backgroundColor: Color) extends Serializable {

  def find_nearest_object(ray: Ray): Intersects = {
    val inters = objects.map(o => o.intersects(ray)).filter(o => o.intersects)
    if (inters.length > 0) {
      inters.reduceLeft((a, b) => if (a.distance < b.distance) a else b)
    } else {
      Intersects.noHit
    }
  }

}
