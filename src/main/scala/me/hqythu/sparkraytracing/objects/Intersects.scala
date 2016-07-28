/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.objects

import me.hqythu.sparkraytracing.utils.Vector3

class Intersects(val object_ptr: AbstractObject, val intersects: Boolean, val distance: Double,
                 val position: Vector3, val normal: Vector3) extends Serializable {

}

object Intersects {

  val noHit = new Intersects(null, false, 0, Vector3.zero, Vector3.zero)

}
