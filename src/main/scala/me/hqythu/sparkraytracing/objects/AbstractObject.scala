/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.objects

import me.hqythu.sparkraytracing.tracer.Ray

abstract class AbstractObject(val material: Material) extends Serializable {

  def intersects(ray: Ray): Intersects

}
