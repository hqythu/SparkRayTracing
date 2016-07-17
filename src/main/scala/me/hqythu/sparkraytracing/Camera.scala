/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing

class Camera(position: Vector3, front: Vector3, up: Vector3, width: Int, height: Int, ratio: Double) {

  val right = (front ^ up) % ()

  def emit(x: Double, y: Double): Ray = {
    val direction = front + (right * (x - width / 2) / ratio) + up * ((y - height / 2) / ratio)
    new Ray(position, direction % ())
  }
}
