/**
  * Created by wk on 16/7/27.
  */

package me.hqythu.sparkraytracing.utils

import com.esotericsoftware.kryo.Kryo
import me.hqythu.sparkraytracing.objects._
import me.hqythu.sparkraytracing.tracer.{Camera, Ray, Scene, Tracer}
import me.hqythu.sparkraytracing.utils.Vector3
import org.apache.spark.serializer.KryoRegistrator

class MyRegisterKryo extends KryoRegistrator {
  override def registerClasses(kryo: Kryo): Unit = {
    kryo.register(classOf[Light])
    kryo.register(classOf[AbstractObject])
    kryo.register(classOf[Ray])
    kryo.register(classOf[Scene])
    kryo.register(classOf[Vector3])
    kryo.register(classOf[Color])
    kryo.register(classOf[Image])
    kryo.register(classOf[Material])
    kryo.register(classOf[Sphere])
    kryo.register(classOf[Plane])
    kryo.register(classOf[Camera])
    kryo.register(classOf[Tracer])
  }
}