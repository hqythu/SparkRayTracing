/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.tracer

import me.hqythu.sparkraytracing.objects.Intersects
import me.hqythu.sparkraytracing.utils.{Color, Vector3}
import org.apache.spark.SparkContext


class Tracer(camera: Camera, scene: Scene) extends Serializable {

  val width = camera.width
  val height = camera.height
  val traceDepth = 5

  def raytrace(in: Ray, depth: Integer): Color = {
    if (depth >= traceDepth) {
      new Color()
    } else {
    }
  }

  def runspark(sc: SparkContext) {
    val pointList = sc.parallelize((0 until width * height).map((i: Int) => (i / width, i % width)))

    val colors = pointList
      .map((p: (Int, Int)) => (p, camera.emit(p._1, p._2)))
      .map(r => (r._1, raytrace(r._2, 0)))
  }

}
