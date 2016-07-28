/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.tracer

import me.hqythu.sparkraytracing.utils.Vector3

class Camera(position: Vector3, _front: Vector3, _up: Vector3, val width: Int, val height: Int, ratio: Double)
  extends Serializable {

  val front = _front % ()
  val up = _up % ()

  val right = (front ^ up) % ()

  def emit(x: Double, y: Double): Ray = {
    val direction = front + (right * (x - width / 2) / ratio) + up * ((y - height / 2) / ratio)
    new Ray(position, direction % ())
  }
}
