echo =============================
#将函数第1个参数赋给num变量，并打印
p_num ()
{
    num=$1
    echo $num
}
#$@ 遍历所有输入参数
for n in $@
    do
        p_num $n
    done

echo ====================================






