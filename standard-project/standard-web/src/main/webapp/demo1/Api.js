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
                url: app_root_path + "/api/ajax/formAdd",
                method: "POST",
                contentType: MediType_FORM_URLENCODE,

            },
            postBody: {
                url: app_root_path + "/api/ajax/postBody",
                method: "POST",
                contentType: MediType_JSON,
                descript:"post body 提交json demo接口"
            },
            getRequest: {
                url: app_root_path + "/api/ajax/getRequest",
                contentType: MediType_FORM_URLENCODE,
                descript:"普通get方式提交数据 demo接口"
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
        request:function (api, params,callback) {
            switch (api.method){
                case "GET":
                    this.doGet(api,params,callback);
                break;
                case "POST":
                    if(api.contentType == MediType_JSON){
                        this.doPostBody(api,JSON.stringify(params),callback);
                    }else {
                        this.doPostForm(api,params,callback);
                    }
                    break;
                default:
                    //默认为get
                    this.doGet(api,params,callback);
                    break;
            }
        },
        /**
         * get方法提交数据
         * @param api 对象
         * @param params 参数，统一json格式
         * @param callback 请求回调，据需求是否传入
         */
        doGet:function (api, params,callback) {
            this.doLogBefore(api, params)
            var _this = this;
            $.ajax({
                type: "GET", //请求方式
                url: api.url, //请求地址
                dataType: "json",//client接收数据类型
                data: params,
                headers: {'Content-Type': ' application/json; charset=UTF-8'},
                success: function (data) {
                    _this.doAfterRequest(data,callback);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.error("请求信息失败 status:" + textStatus)
                }
            });
        },
        /**
         * post方法 form表单提交数据，
         * @param api 对象
         * @param params 参数，统一json格式
         * @param callback 请求回调，据需求是否传入
         */
        doPostForm:function (api, params,callback) {
            this.doLogBefore(api, params)
            var _this = this;
            $.post(api.url, params, function (data) {
                _this.doAfterRequest(data,callback);
            },"json");
        },
        /**
         * post json格式，body 里提交数据
         * @param api 对象
         * @param params 参数，统一json格式
         * @param callback 请求回调，据需求是否传入
         */
        doPostBody:function (api, params,callback) {
            this.doLogBefore(api, params)
            var _this = this;
            $.ajax({
                type: "POST", //请求方式
                url: api.url, //请求地址
                dataType: "json",//client接收数据类型
                data: params,
                headers: {'Content-Type': ' application/json; charset=UTF-8'},
                success: function (data) {
                    _this.doAfterRequest(data,callback);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.error("请求信息失败 status:" + textStatus)
                }
            });
        },
        doLogBefore: function (api, reqParams) {
            if (Api.debug) {

                if(api.url == undefined){
                    console.error("注意:没指定请求路径");
                    alert("注意:没指定请求路径,请检查代码");
                }
                if(api.descript){
                    console.info(api.descript+":"+api.url);
                }else {
                    console.info("所请求接口:"+api.url);
                }
                console.log("发送请求信息:" + JSON.stringify(api));
                console.log("请求参数:" + JSON.stringify(reqParams));
                if (api.method == undefined) {
                    console.warn("注意:没指定请求方式，使用默认的GET试请求");
                }
                if (api.contentType == undefined) {
                    console.warn("注意:没指定发送数据提交格式，使用默认的数据提交格式:"+MediType_FORM_URLENCODE);
                }
            }
        },
        doAfterRequest:function (data,callback) {
            if (Api.debug) {
                console.log("服务端结果:" + JSON.stringify(data))
            }
            if(callback){
                callback(data);
            }
        }
    };
}






