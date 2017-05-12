echo ==================模拟linnux登录shell=================
#/bin/bash
echo -n "login:"
#用read 命令读取输入，并用变量name 接收输入
read name
echo -n "password:"
read passwd
if [ $name = "cht" -a $passwd = "abc" ];then
    echo "the host and password is right!"
else
    echo "input is error!"
fi

echo ====================================






