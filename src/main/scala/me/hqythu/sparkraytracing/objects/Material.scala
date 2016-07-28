/**
  * Created by hqythu on 7/27/16.
  */

package me.hqythu.sparkraytracing.objects

import java.awt.image.BufferedImage

import me.hqythu.sparkraytracing.utils.Color

class Material(val color: Color, val diff: Double, val refl: Double, val refr: Double, val index: Double,
              val texture: BufferedImage = null)
  extends Serializable{

}
