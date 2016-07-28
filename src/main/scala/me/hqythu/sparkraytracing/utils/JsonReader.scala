/**
  * Created by wk on 16/7/27.
  */

package me.hqythu.sparkraytracing.utils

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import me.hqythu.sparkraytracing.objects._
import me.hqythu.sparkraytracing.tracer.{Camera, Scene}
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods._

import scala.collection.mutable.ArrayBuffer

class JsonReader {
  private def parseVecter3(obj: List[Double]): Vector3 = {
    new Vector3(obj(0), obj(1), obj(2))
  }

  private def parseColor(obj: List[Double]): Color = {
    new Color(obj(0), obj(1), obj(2))
  }

  private def parseMaterial(obj: Map[String, _]): Material = {
    val img = if (obj.get("texture").nonEmpty) {
      ImageIO.read(new File(obj.get("texture").get.asInstanceOf[String]))
    } else {
      null
    }
    new Material(
      parseColor(obj.get("color").get.asInstanceOf[List[Double]]),
      obj.get("diff").get.asInstanceOf[Double],
      obj.get("refl").get.asInstanceOf[Double],
      obj.get("refr").get.asInstanceOf[Double],
      obj.get("index").get.asInstanceOf[Double],
      img
    )
  }

  def parser(fileName: String): (Camera, Scene) = {
    val json = parse(new File(fileName))
    val objs = new ArrayBuffer[AbstractObject]
    val lights = new ArrayBuffer[Light]
    for (obj <- (json \ "Objects").values.asInstanceOf[List[Map[String, _]]]) {
      if (obj.get("type").get.equals("Sphere")) {
        objs.append(new Sphere(
          parseVecter3(obj.get("center").get.asInstanceOf[List[Double]]),
          obj.get("radius").get.asInstanceOf[Double],
          parseMaterial(obj.get("material").get.asInstanceOf[Map[String, _]])
        ))
      } else if (obj.get("type").get.equals("Plane")) {
        objs.append(new Plane(
          parseVecter3(obj.get("position").get.asInstanceOf[List[Double]]),
          parseVecter3(obj.get("normal").get.asInstanceOf[List[Double]]),
          parseVecter3(obj.get("dx").get.asInstanceOf[List[Double]]),
          parseMaterial(obj.get("material").get.asInstanceOf[Map[String, _]])
        ))
      }
    }
    for (obj <- (json \ "Lights").values.asInstanceOf[List[Map[String, _]]]) {
      lights.append(new Light(
        parseVecter3(obj.get("position").get.asInstanceOf[List[Double]]),
        parseColor(obj.get("color").get.asInstanceOf[List[Double]])
      ))
    }
    implicit val formats = DefaultFormats
    val tmp = (json \ "Camera").values.asInstanceOf[Map[String, _]]
    val camera = new Camera(
      parseVecter3(tmp.get("position").get.asInstanceOf[List[Double]]),
      parseVecter3(tmp.get("front").get.asInstanceOf[List[Double]]),
      parseVecter3(tmp.get("up").get.asInstanceOf[List[Double]]),
      (json \ "width").values.asInstanceOf[Double].toInt,
      (json \ "height").values.asInstanceOf[Double].toInt,
      tmp.get("ratio").get.asInstanceOf[Double]
    )
    val backgroundColor = parseColor((json \ "BackgroundColor").values.asInstanceOf[List[Double]])
    (camera, new Scene(objs.toArray, lights.toArray, backgroundColor))
  }
}
