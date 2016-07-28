/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.objects

import me.hqythu.sparkraytracing.tracer.Ray
import me.hqythu.sparkraytracing.utils.Color

abstract class AbstractObject(val material: Material) extends Serializable {

  def intersects(ray: Ray): Intersects

  def getColor(intersect: Intersects): Color

}
