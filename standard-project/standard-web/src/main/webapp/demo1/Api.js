//应用根目录
var app_root_path = "";
//数据格式
var MediType_FORM_URLENCODE = "application/x-www-form-urlencoded; charset=UTF-8";
var MediType_JSON = "application/json; charset=UTF-8";

if (!Api) {
    //接口定义处
    var Api = {
        debug: true,
        //接口demo，若api没指定，method:默认为get方法提交
        ajaxDemo: {
            formAdd: {
                //form 表单提交例子
                url: app_root_path + "/api/ajax/formAdd",
                method: "POST",
                contentType: MediType_FORM_URLENCODE,
                //需要提交的参数模型
                paramModel: {
                    id:undefined,//
                    text:undefined,
                    name: undefined,
                    age: undefined,
                    school: undefined,
                    teacher: {
                        name: undefined,
                        address: undefined,
                        level: undefined,
                    }
                }

            },
            // json body 提交提交例子
            postBody: {
                url: app_root_path + "/api/ajax/postBody",
                method: "POST",
                contentType: MediType_JSON,
                descript: "post body 提交json demo接口",
                paramModel: {
                    id:undefined,
                    text:undefined,
                    name: undefined,
                    age: undefined,
                    school: undefined,
                    teacher: {
                        name: undefined,
                        address: undefined,
                        level: undefined
                    }
                }
            },
            //普通get请求
            getRequest: {
                url: app_root_path + "/api/ajax/getRequest",
                user:{
                    id:undefined,
                    name:undefined,
                    age:undefined,
                    school:undefined,
                    teacher:{
                        teacherName:undefined,
                        cource:undefined,
                        student:{
                            teacherName:undefined,
                            cource:undefined
                        }
                    }
                }

            }
        },
    }
}

if (!NetUtis) {
    var NetUtis = {
        /**
         * @param api 对象
         * @param params 参数，统一json格式
         * @param callback 请求回调，据需求是否传入
         */
        request: function (api, params, callback) {
            switch (api.method) {
                case "GET":
                    this.doGet(api, params, callback);
                    break;
                case "POST":
                    if (api.contentType == MediType_JSON) {
                        this.doPostBody(api, params, callback);
                    } else {
                        this.doPostForm(api, params, callback);
                    }
                    break;
                default:
                    //默认为get
                    this.doGet(api, params, callback);
                    break;
            }
        },
        /**
         * get方法提交数据
         * @param api 对象
         * @param params 参数，统一json格式
         * @param callback 请求回调，据需求是否传入
         */
        doGet: function (api, params, callback) {
            api.method = "GET";
            api.contentType = MediType_JSON;
            this.doSend(api, params, callback);
        },
        /**
         * post方法 form表单提交数据，
         * @param api 对象
         * @param params 参数，统一json格式
         * @param callback 请求回调，据需求是否传入
         */
        doPostForm: function (api, params, callback) {
            api.method = "POST";
            api.contentType = MediType_FORM_URLENCODE;
            this.doSend(api, params, callback);
        },
        /**
         * post json格式，body 里提交数据
         * @param api 对象
         * @param params 参数，统一json格式
         * @param callback 请求回调，据需求是否传入
         */
        doPostBody: function (api, params, callback) {
            var tparam = JSON.stringify(params);
            api.method = "POST";
            api.contentType = MediType_JSON;
            this.doSend(api, tparam, callback);
        },
        doSend: function (api, params, callback) {
            this.doLogBefore(api, params)
            var _this = this;
            $.ajax({
                type: api.method, //请求方式
                url: api.url, //请求地址
                dataType: "json",//client接收数据类型
                data: params,
                headers: {'Content-Type': api.contentType},
                success: function (data) {
                    _this.doAfterRequest(data, callback);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("请求失败:" + XMLHttpRequest.status)
                    console.error("请求信息失败 status:" + XMLHttpRequest.status)
                }
            });
        },
        doLogBefore: function (api, reqParams) {
            if (Api.debug) {
                if (api.url == undefined) {
                    console.error("注意:没指定请求路径");
                    alert("注意:没指定请求路径,请检查代码");
                }
                if (api.descript) {
                    console.info("请求接口:" + api.url + ":" + api.descript);
                } else {
                    console.info("请求接口:" + api.url);
                }
                console.log("接口详情:" + JSON.stringify(api));
                console.log("请求参数:" + JSON.stringify(reqParams));
                if (api.method == undefined) {
                    console.warn("注意:没指定请求方式，使用默认的GET试请求");
                }
                if (api.contentType == undefined) {
                    console.warn("注意:没指定发送数据提交格式，使用默认的数据提交格式:" + MediType_FORM_URLENCODE);
                }
            }
        },
        doAfterRequest: function (data, callback) {
            if (Api.debug) {
                console.log("服务端返回结果:" + JSON.stringify(data))
            }
            if (callback) {
                callback(data);
            }
        }
    };
}


/**
 * 标签参数元信息,用于获取指定容器内指定的参数名的属性的:值; 及参数值的属性的:值
 * @param container 各参数所在的容器
 * @param tagName 该值可以为标签名、className 或id,一般为className通用
 * @param dataName 要提交的参数名(key)
 * @param valueName 要提交的参数值(value)
 * @constructor
 */
function TagInfo(container,tagName,dataName,valueName) {
    //容器名称
    this.container = container;
    //目录标签：标签名，类名或id 都可以
    this.tagName = tagName;
    //自定属性数据参数的名称
    this.dataName = dataName;
    //自定义属数据参数的值的名称
    this.valueName = valueName;
}

/**
 * 在某容器内找出所需提交的参数信息(不支持多级参数)
 * @param tagInfo 参数元信息
 * @param paramModel
 */
function findParamByModel(tagInfo, paramModel) {
    //复制参数model
    var params = cloneObj(paramModel);
    var els = $(tagInfo.container).find(tagInfo.tagName);
    var hashGetParams = false;
    for (var i = 0; i < els.length; i++) {
        var dataName = $(els[i]).attr(tagInfo.dataName);
        if (dataName == undefined) {
            console.warn("注意！有一个["+els[i].tagName+"]元素中没有定义名称为[" + tagInfo.dataName +"]属性请，请核对是否漏掉的参数名");
            continue;
        }
        var value;
        if (tagInfo.valueName == "value" && (els[i].tagName == "INPUT" ||  els[i].tagName=="TEXTAREA" )) {
            value = $(els[i]).val();
        } else {
            value = $(els[i]).attr(tagInfo.valueName);
        }
        if (value == undefined) {
            console.warn("注意！有一个[ "+els[i].tagName+" ]元素中没有定义:" + tagInfo.valueName +"属性，请核对是参数名为["+dataName+"]的参数是否需要赋值");
            continue;
        }
        for (key in params) {
            if (key == dataName) {
                params[key] = value;
                hashGetParams = true;
            }
        }

    }
    if (!hashGetParams) {
        console.warn("一个参数值也没获取到请检查参数名是否与接口匹配:" + JSON.stringify(paramModel));
    }
    var undfinedPs = "";
    for (key in params) {
        if (params[key] == undefined) {
            undfinedPs+=key+",";
        }
    }
    if(undfinedPs){
        console.warn("注意model中定义的参数: " + undfinedPs + "没有值，请检查是否为必传参数");
    }
    console.log("获取到的参数:" + JSON.stringify(params));
    return params;
}

var cloneObj = function (obj) {

    var newObj = {};
    if (obj instanceof Array) {
        newObj = [];
    }
    for (var key in obj) {
        var val = obj[key];
        //newObj[key] = typeof val === 'object' ? arguments.callee(val) : val; //arguments.callee 在哪一个函数中运行，它就代表哪个函数, 一般用在匿名函数中。
        newObj[key] = typeof val === 'object' ? cloneObj(val) : val;
    }
    return newObj;
};








