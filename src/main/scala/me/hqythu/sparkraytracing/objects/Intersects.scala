/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.objects

import me.hqythu.sparkraytracing.utils.Vector3

class Intersects(_object_ptr: Object, _intersects: Boolean, _distance: Double,
                 _position: Vector3, _normal: Vector3) extends Serializable {
  val object_ptr = _object_ptr
  val intersects = _intersects
  val distance = _distance
  val position = _position
  val normal = _normal
}

object Intersects {
  val noHit = new Intersects(null, false, 0, Vector3.zero, Vector3.zero)
}
