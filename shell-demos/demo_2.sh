echo ==================比较两个数大小=================

echo "please enter two number"
read a
read b

if test $a -eq $b
    then echo "NO.1 = NO.2"
elif test $a -gt $b
    then echo "NO.1 > NO.2"
else
    echo "NO.1 < NO.2"
fi

read a
read b

if [ $a -eq $b ]
    then echo "NO.1 = NO.2"
elif [ $a -gt $b ]
    then echo "NO.1 > NO.2"
else
    echo "NO.1 < NO.2"
fi


echo ====================================






