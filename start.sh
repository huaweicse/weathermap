#!/bin/sh

isInstalledNodejs()
{
	which node >/dev/null 2>&1
	return $?
}

InstallNodeJs()
{
	curPath=`pwd`
	nodejsPath="$curPath"/pkg
	if [ ! -d $nodejsPath ]; then
		echo "node js pkg not found"
		return 1
	fi
	which tar >/dev/null 2>&1
	rst=$?
	if [ $rst != 0 ];then
		echo "There is no tar, please first install tar!"
		return $rst
	fi
	nodejsPathName="$nodejsPath"/node-v8.9.4-linux-x64.tar.xz
	if [ ! -f $nodejsPathName ];then
		echo "There is no node pkg, the weather pkg is wrong!"
		return 1
	fi
	tar -xvf $nodejsPathName > /dev/null
	tarRst=$?
	if [ $tarRst != 0 ];then
	    echo "tar node js pkg failed!"
	    return 1
	fi
	nodeBinPath="$curPath"/node-v8.9.4-linux-x64/bin/node
	ln -s $nodeBinPath /usr/local/bin/
	npmBinPath="$curPath"/node-v8.9.4-linux-x64/bin/npm
	ln -s $npmBinPath /usr/local/bin/

	node -v
	rst=$?
	if [ $rst != 0 ];then
		echo "install nodejs failed, return code is $rst"
		return $rst
    fi
    echo "install nodejs successful"
}

main()
{
	isInstalledNodejs
	if [ $? != 0 ];then
	    InstallNodeJs
	    if [ $rst != 0 ]; then
		    return $rst
        fi
	fi

    curPath=`pwd`
    bash "$curPath"/startup_all.sh > /dev/null 2>&1 &
    rst=$?
    if [ $rst != 0 ];then
        echo "install weather failed"
        return $rst
    fi
    echo "install weather successful"
}

main
return $?
