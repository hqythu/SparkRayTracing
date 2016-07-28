/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing.tracer

import java.awt.image.BufferedImage

import me.hqythu.sparkraytracing.objects.Intersects
import me.hqythu.sparkraytracing.utils.{Color, Vector3}
import org.apache.spark.SparkContext


class Tracer(camera: Camera, scene: Scene) extends Serializable {

  val width = camera.width
  val height = camera.height
  val traceDepth = 5

  def raytrace(ray: Ray, depth: Integer): Color = {
    if (depth >= traceDepth) {
      new Color()
    } else {
      val inter = scene.find_nearest_object(ray)

      if (!inter.intersects) {
        return new Color()
      }

      val obj = inter.object_ptr
      val material = obj.material

      val color_bg = scene.backgroundColor * material.color * material.diff

      val color_refl = if (material.refl > 0) {
        raytrace(ray.reflect(inter.normal, inter.position), depth + 1) * material.color * material.refl
      } else {
        new Color()
      }

      val color_refr = if (material.refr > 0) {
        raytrace(ray.refract(inter.normal, inter.position, material.index), depth + 1) * material.color * material.refr
      } else {
        new Color()
      }

      (color_bg + color_refl + color_refr).confine()
    }
  }

  def runspark(sc: SparkContext): BufferedImage = {
    val pointList = sc.parallelize((0 until width * height).map((i: Int) => (i / width, i % width)))

    val colors = pointList
      .map((p: (Int, Int)) => (p, camera.emit(p._1, p._2)))
      .map(r => (r._1, raytrace(r._2, 0)))
      .collect()

    val image: BufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    for (color <- colors) {
      val rgb = (color._2.r * 256).toInt * 256 * 256 + (color._2.g * 256).toInt * 256 + (color._2.b * 256).toInt
      image.setRGB(color._1._1, color._1._2, rgb.toInt)
    }

    image
  }

}
