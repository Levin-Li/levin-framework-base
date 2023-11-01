#!/bin/bash
#Author Lilw @2012
### 代码生成哈希校验码：[6998d18b98dcd29ea1a71a42b2769923], 请不要修改和删除此行内容。

execDir=`pwd`

 #sh文件所在目录
shellDir=`dirname $0`

cd $shellDir

shellDir=`pwd`

echo "work dir : $shellDir"

chmod +x *.sh

./shutdown.sh
./startup.sh
tail -f *.out

###
