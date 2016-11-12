#!/bin/sh
mount -o remount,rw /system
cp ~/sdcard/Android/data/com.umang96.yurekathermalcontrol/files/thermal ~/sdcard/thermal
str="$(getprop ro.product.device)"
sti="$(getprop ro.build.product)"
stj='YUREKA'
if [ $str = $stj ] || [ $sti = $stj ];
then
cp ~/sdcard/Android/data/com.umang96.yurekathermalcontrol/files/thermal-engine2.conf ~/system/etc/thermal-engine.conf
chmod 644 ~/system/etc/thermal-engine.conf
echo "Balanced" > ~/sdcard/thermal
else
echo "Error: build prop doesn't tell device model yureka"
fi
