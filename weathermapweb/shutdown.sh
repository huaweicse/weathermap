#!/bin/sh
##
# Copyright@2017 Huawei Technologies Co., Ltd. 
##

#stop Server
ps -ef | grep node | grep weathermapweb | awk -F ' ' '{print $2}'|while read line
do
  eval "kill -9 $line"
done
