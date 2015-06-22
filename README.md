# ViewPageBackgroundChanged
实现ViewPage背景颜色随着滑动渐变
原理介绍：
对于两个颜色Color1,Color2，每个颜色有三个主要颜色通道red通道,green通道,blue通道;
如果这两个颜色作为前景色和背景色，前景色以透明度a显示，那么显示屏所得到的颜色按以下公式计算
Color(最终颜色) = Color1（前景色） * a + Color2（背景色） * ( 1 - a )
注意，在程序是现实，需要将三个颜色通道分开按公式计算

